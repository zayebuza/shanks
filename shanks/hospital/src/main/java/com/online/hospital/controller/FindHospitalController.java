package com.online.hospital.controller;

import com.online.hospital.dao.HospitalMapper;
import com.online.hospital.model.Hospital;
import com.online.hospital.service.FindHospitalService;
import com.online.hospital.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

/**
 * Created by miaodongbiao
 * Date:2018/4/21-20:04
 * Description:
 */
@RestController
public class FindHospitalController {
    @Autowired
    FindHospitalService findHospitalService;
    @Autowired
    public HospitalMapper hospitalMapper;
    @RequestMapping("/list")
    public String test(int id){
        return findHospitalService.findHosp(id);

    }
    @RequestMapping(value = "findHospital",method = RequestMethod.GET)
    public Set<Hospital> findHospital(String city, String address){
        //  log.debug(String.valueOf(request.getParameterMap()));
        return  findHospitalService.find(city,address);

    }
}
