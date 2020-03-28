package me.std.db.tables;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * Description:
 * Author: lixiao
 * Create on: 2020/3/8.
 */
@Entity
public class SportLocation {
    @Id(autoincrement = true)//设置自增长
    private Long id;
    private String dateTime;
    public double longitude;//经度
    public double latitude;//纬度
    public String address;//地址
    public String poiName;//兴趣点

    @Generated(hash = 92191092)
    public SportLocation(Long id, String dateTime, double longitude,
            double latitude, String address, String poiName) {
        this.id = id;
        this.dateTime = dateTime;
        this.longitude = longitude;
        this.latitude = latitude;
        this.address = address;
        this.poiName = poiName;
    }

    @Generated(hash = 1212988100)
    public SportLocation() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDateTime() {
        return this.dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public double getLongitude() {
        return this.longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return this.latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPoiName() {
        return this.poiName;
    }

    public void setPoiName(String poiName) {
        this.poiName = poiName;
    }


}
