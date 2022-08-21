package com.carpool.partyMatch.domain.kafka;

import com.carpool.partyMatch.AbstractEvent;
import com.carpool.partyMatch.domain.PartyStatus;
import com.carpool.partyMatch.domain.Driver;

public class PartyStarted extends AbstractEvent{

  Long id;
  Long partyInfoId;

  Driver driver;

  PartyStatus partyStatus;

      public Long getId() {
          return id;
      }

      public void setId(Long id) {
          this.id = id;
      }

      public Long getPartyInfoId() {
          return partyInfoId;
      }

      public void setPartyInfoId(Long partyInfoId) {
          this.partyInfoId = partyInfoId;
      }

      public PartyStatus getPartyStatus() {
          return partyStatus;
      }

      public void setPartyStatus(PartyStatus partyStatus) {
          this.partyStatus = partyStatus;
      }

      public Driver getDriver() {
          return driver;
      }

      public void setDriver(Driver driver) {
          this.driver = driver;
      }

}
