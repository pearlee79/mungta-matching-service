package com.carpool.partyMatch.domain;

import java.io.Serializable;

import javax.persistence.Embeddable;


@Embeddable
public class Driver {

    Long driverId;
    String driverName;

    protected Driver(){}
    public Driver(Long driverId, String driverName){
        this.driverId = driverId;
        this.driverName = driverName;
    }

    public String getDriverName() {
        return driverName;
    }

    public Long getDriverId() {
        return driverId;
    }


}
