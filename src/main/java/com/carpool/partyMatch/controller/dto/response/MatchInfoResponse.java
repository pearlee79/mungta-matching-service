package com.carpool.partyMatch.controller.dto.response;

import lombok.Data;
import java.util.List;

import com.carpool.partyMatch.domain.MatchStatus;

@Data
public class MatchInfoResponse {
  private Long partyInfoId;
  private Long userId;

  public MatchInfoResponse(Long partyInfoId, Long userId) {

    this.partyInfoId = partyInfoId;
    this.userId = userId;

  }
}
