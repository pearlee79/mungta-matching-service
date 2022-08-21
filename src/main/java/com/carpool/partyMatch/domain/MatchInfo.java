package com.carpool.partyMatch.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.FetchType;
import javax.persistence.Embedded;
import javax.persistence.AttributeOverride;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import java.util.List;
//import javax.persistence.OneToMany;

@Entity
public class MatchInfo extends BaseEntity {
  @Id @GeneratedValue
  Long id;

  Long partyInfoId;

  Long userId;

  MatchStatus matchStatus;

    public Long getPartyInfoId() {
        return partyInfoId;
    }

    public void setPartyInfoId(Long partyInfoId) {
        this.partyInfoId = partyInfoId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public MatchStatus getMatchStatus() {
            return matchStatus;
    }

    public void setMatchStatus(MatchStatus matchStatus) {
        this.matchStatus = matchStatus;
    }


}
