package com.carpool.partyMatch.controller.dto;

import lombok.Data;

@Data
public class MatchProcessDto {

  private Long partyInfoId;
  private Long driverId;
  private String driverName;
  private Long userId;

}
