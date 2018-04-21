package com.online.hospital.service;

import com.alibaba.fastjson.JSONObject;
import com.online.hospital.dao.HospitalMapper;
import com.online.hospital.model.Hospital;
import com.online.hospital.model.LonAndLat;
import com.online.hospital.utils.HttpClient;
import com.online.hospital.utils.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
/**
 * Created by miaodongbiao
 * Date:2018/4/21-20:08
 * Description:
 */
@Slf4j
@Service
public class FindHospitalService {

    private static final double EARTH_RADIUS = 6378.137;

    @Autowired
    public HospitalMapper hospitalMapper;
    List<Hospital> hosList;
    TreeSet<Hospital> retHosTreeSet;
    LonAndLat homeLonAndLat;
    public String findHosp(int id) {
        return hospitalMapper.getById(id);
    }

    public Set<Hospital> find(String city, String address) {
        hosList = hospitalMapper.getHos(city);
        homeLonAndLat = calcuHomeLonAndLat(address);
        //遍历list
        if (retHosTreeSet == null){
            retHosTreeSet = new TreeSet<>();
        }
        Iterator<Hospital> it = hosList.iterator();
        while (it.hasNext()) {
            Hospital hos = it.next();
            log.info("住址的经度："+homeLonAndLat.getLongitude()+";住址的纬度："+homeLonAndLat.getLatitude());
            double s = getDistance(homeLonAndLat.getLatitude(), homeLonAndLat.getLongitude(),hos.getLatitude(),hos.getLongitude());
            log.info(hos.getName()+"距离"+s);
            hos.setDistance(s);
            retHosTreeSet.add(hos);
        }

        return retHosTreeSet;
    }

    private static double rad(double d) {
        return d * Math.PI / 180.0;
    }
    /**
     * 通过经纬度获取距离(单位：米)
     * @param lat1
     * @param lng1
     * @param lat2
     * @param lng2
     * @return
     */
    public static double getDistance(double lat1, double lng1, double lat2, double lng2) {
        double radLat1 = rad(lat1);
        double radLat2 = rad(lat2);
        double a = radLat1 - radLat2;
        double b = rad(lng1) - rad(lng2);
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2)
                + Math.cos(radLat1) * Math.cos(radLat2)
                * Math.pow(Math.sin(b / 2), 2)));
        s = s * EARTH_RADIUS;
        s = Math.round(s * 10000d) / 10000d;
        s = s*1000;
        return s;
    }


    /**
     * 计算住址的经纬度
     * @param address
     * @return
     */
    public static LonAndLat calcuHomeLonAndLat(String address){
        LonAndLat homeLonAndLat = new LonAndLat();
        JSONObject result = HttpClient.of().get4Json("http://api.map.baidu.com/geocoder/v2/?"+"address="+address+"&output=json&ak=RsTDKeVvkdl0Lm4YLHGiSbq5qpqzvn93");
        JSONObject oc = result.getJSONObject("result").getJSONObject("location");
        homeLonAndLat.setLatitude(oc.getDouble("lat"));
        homeLonAndLat.setLongitude(oc.getDouble("lng"));
        log.info("住址的经纬度是"+homeLonAndLat.toString());
        return homeLonAndLat;
    }
}
