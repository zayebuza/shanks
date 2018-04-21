package com.online.hospital;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//mapper的注解可以有2种方式:1、启动类上加@MapperScan（“mapper接口所在包”）2、每个mapper接口上加上@Mapper注解的方式
//@MapperScan(basePackages = {"com.mdb.insurance.dao.HospitalMapper" })
public class HospitalApplication {

	public static void main(String[] args) {
		SpringApplication.run(HospitalApplication.class, args);
	}
}
