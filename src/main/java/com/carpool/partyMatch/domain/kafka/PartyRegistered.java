package com.carpool.partyMatch.domain.kafka;

import java.util.StringJoiner;

import com.carpool.partyMatch.AbstractEvent;
import com.carpool.partyMatch.domain.PartyStatus;
import com.carpool.partyMatch.domain.Driver;

public class PartyRegistered extends AbstractEvent{

  Long id;
  Long driverId;
  String driverName;


      public Long getId() {
          return id;
      }

      public void setId(Long id) {
          this.id = id;
      }

      public Long getDriverId() {
          return driverId;
      }

      public void setDriverId(Long driverId) {
          this.driverId = driverId;
      }

      public String getDriverName() {
        return driverName;
      }

      public void setDriverName(String driverName) {
        this.driverName = driverName;
      }

}
