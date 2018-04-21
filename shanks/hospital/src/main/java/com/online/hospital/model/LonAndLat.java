package com.online.hospital.model;

/**
 * Created by miaodongbiao
 * Date:2018/4/21-20:08
 * Description:
 */
public class LonAndLat {

    private double latitude;//经度
    private double longitude;//纬度

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "LonAndLat{" +
                "latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}
