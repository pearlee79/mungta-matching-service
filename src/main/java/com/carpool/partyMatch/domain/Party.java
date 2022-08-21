package com.carpool.partyMatch.domain;

//import java.util.List;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.FetchType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import java.util.List;

@Entity
public class Party extends BaseEntity {
    @Id @GeneratedValue
    Long id;
    Long partyInfoId;

    @Embedded
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
