package com.online.hospital.model;

/**
 * Created by miaodongbiao
 * Date:2018/4/21-20:07
 * Description:
 */
public class Hospital implements Comparable<Hospital>{

    private String name;
    private String city;
    private String lonAndLat;
    private String level;
    private int id;
    private double latitude;//经度
    private double longitude;//纬度
    private double distance;

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getLonAndLat() {
        return lonAndLat;
    }

    public void setLonAndLat(String lonAndLat) {
        this.lonAndLat = lonAndLat;
    }

    @Override
    public String toString() {
        return "Hospital{" +
                "name='" + name + '\'' +
                ", city='" + city + '\'' +
                ", lat=" + level +
                ", lonAndLat=" + lonAndLat +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Hospital hospital = (Hospital) o;

        if (Double.compare(hospital.latitude, latitude) != 0) return false;
        if (Double.compare(hospital.longitude, longitude) != 0) return false;
        if (Double.compare(hospital.distance, distance) != 0) return false;
        if (!name.equals(hospital.name)) return false;
        if (!city.equals(hospital.city)) return false;
        return level.equals(hospital.level);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = name.hashCode();
        result = 31 * result + city.hashCode();
        result = 31 * result + level.hashCode();
        temp = Double.doubleToLongBits(latitude);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(longitude);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(distance);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public int compareTo(Hospital hos) {
        if(this.getDistance() > hos.getDistance()){
            return 1;
        }else if(this.getDistance() < hos.getDistance()){
            return -1;
        }
        else{
            return 0;
        }
    }
}
