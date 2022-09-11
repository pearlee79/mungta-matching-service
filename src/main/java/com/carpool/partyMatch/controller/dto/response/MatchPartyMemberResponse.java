package com.carpool.partyMatch.controller.dto.response;

import lombok.Data;

import com.carpool.partyMatch.domain.MatchStatus;
import com.carpool.partyMatch.client.dto.UserResponse;
import com.carpool.partyMatch.client.dto.ReviewResponse;

@Data
public class MatchPartyMemberResponse {

  UserResponse userResponse;
  ReviewResponse reviewResponse;

  public MatchPartyMemberResponse(UserResponse userResponse, ReviewResponse reviewResponse) {

    this.userResponse = userResponse;
    this.reviewResponse = reviewResponse;

  }
}
