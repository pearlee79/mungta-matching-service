package com.carpool.partyMatch.controller.dto.response;

import lombok.Data;
import java.util.List;

import com.carpool.partyMatch.domain.PartyStatus;

@Data
public class PartyProcessResponse {

  private Long partyInfoId;
  private PartyStatus partyStatus;

  public PartyProcessResponse(Long partyInfoId, PartyStatus partyStatus) {

    this.partyInfoId = partyInfoId;
    this.partyStatus = partyStatus;

  }

}
