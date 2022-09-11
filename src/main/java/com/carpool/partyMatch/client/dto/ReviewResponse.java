package com.carpool.partyMatch.client.dto;

import lombok.Data;

@Data
public class ReviewResponse {

  Long userId;
  String content;
  String reviewScore;

  public Long getUserId() {
    return userId;
  }

}
