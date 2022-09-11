package com.carpool.partyMatch.client.dto;

import lombok.Data;

@Data
public class UserResponse {

  Long userId;
  String name;
  String gender;
  String curPhoto;
  String partyRole;

  public Long getUserId() {
    return userId;
  }

}
