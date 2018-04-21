package com.online.hospital.controller;

import com.online.hospital.dao.HospitalMapper;
import com.online.hospital.model.Hospital;
import com.online.hospital.utils.ExcelUtil;
import com.online.hospital.utils.HttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Row;
/**
 * Created by miaodongbiao
 * Date:2018/4/21-20:06
 * Description:
 */
@Slf4j
@RestController
public class ImportHosController {

    @Autowired
    public HospitalMapper hospitalMapper;
    @RequestMapping("/import")
    public void test(){

        Hospital hos = new Hospital();

        ExcelUtil eu = new ExcelUtil();
        eu.setPrintMsg(true);
        eu.setStartReadPos(1);// 从第一行开始读取
        eu.setOnlyReadOneSheet(true);
        String src_xlspath = "D://3.xlsx";
        List<Row> rowList = null;
        try {
            rowList = eu.readExcel(src_xlspath);
            System.out.println( rowList.get(0).getCell(0));

        } catch (IOException e) {
            e.printStackTrace();
        }
        Iterator<Row> it = rowList.iterator();
        while (it.hasNext()) {
            Row row = it.next();
            String  hosName = row.getCell(1).toString();//获取第二列
            JSONObject result = HttpClient.of().get4Json("http://api.map.baidu.com/geocoder/v2/?"+"address="+hosName+"&output=json&ak=RsTDKeVvkdl0Lm4YLHGiSbq5qpqzvn93");
            JSONObject oc = result.getJSONObject("result").getJSONObject("location");
            hos.setName(hosName);
            double lng = oc.getDouble("lng");
            double lat = oc.getDouble("lat");
            log.info("lng:"+lng);
            log.info("lat:"+lat);
            log.info("hosName:"+hosName);
            int city = 1;
            int a = hospitalMapper.insert(hosName,lat,lng,city);
            if (a>0){
                log.info("插入成功"+hosName);
            }else{
                log.info("插入失败"+hosName);
            }
        }
    }

}
