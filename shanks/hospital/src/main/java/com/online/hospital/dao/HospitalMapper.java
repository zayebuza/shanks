package com.online.hospital.dao;

import com.online.hospital.model.Hospital;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by miaodongbiao
 * Date:2018/4/21-20:06
 * Description:
 */
@Repository
@Mapper
public interface HospitalMapper {

    @Select("select name from hospital where id = #{id}")
    public String getById(int id);

    @Select("select * from hospital where city = #{city}")
    public List<Hospital> getHos(String city);

    @Insert("insert into hospital(name,latitude,longitude,city) values(#{name},#{lat},#{lng},#{city})")
    public int insert(@Param("name") String name, @Param("lat") double lat, @Param("lng") double lng, @Param("city") int city);

//    public List selectHospitalForCity(String city);//根据城市名称获取所有的医院
//    public List selectHospitalForCityAndLevel(String city,String level);//根据城市名和等级获取所有医院
//    public List selectHospitalForName(String name);
//    //public double calcuHomeLonAndLat(LonAndLat home,LonAndLat hospital);//查询某个医院距离用户住址的距离
//    public void insertHospital(String name,String city,String level,String lonAndLat);

}
