package com.carpool.partyMatch.service;

import java.util.List;

import org.springframework.data.repository.query.Param;

import com.carpool.partyMatch.domain.MatchInfo;
import com.carpool.partyMatch.controller.dto.MatchInfoDto;
import com.carpool.partyMatch.controller.dto.MatchProcessDto;
import com.carpool.partyMatch.controller.dto.PartyProcessDto;
import com.carpool.partyMatch.controller.dto.response.PartyProcessResponse;

public interface MatchInfoService {

  public List<MatchInfo> findMatchUsers(Long partyInfoId);

  public MatchInfo registerMatchInfo(MatchInfoDto matchInfoDto);

  public MatchInfo cancelMatchInfo(MatchInfoDto matchInfoDto);

  public MatchInfo acceptMatchInfo(MatchProcessDto matchProcessDto);

  public MatchInfo denyMatchInfo(MatchProcessDto matchProcessDto);

  public PartyProcessResponse startParty(PartyProcessDto partyProcessDto);

  public PartyProcessResponse closeParty(PartyProcessDto partyProcessDto);
}
