package com.carpool.partyMatch.service.serviceImpl;

import java.util.List;
import java.lang.RuntimeException;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.BeanUtils;

import com.carpool.partyMatch.controller.dto.MatchInfoDto;
import com.carpool.partyMatch.controller.dto.MatchProcessDto;
import com.carpool.partyMatch.controller.dto.PartyProcessDto;
import com.carpool.partyMatch.controller.dto.response.PartyProcessResponse;
import com.carpool.partyMatch.domain.MatchInfo;
import com.carpool.partyMatch.domain.Party;
import com.carpool.partyMatch.domain.Carpooler;
import com.carpool.partyMatch.domain.Driver;
import com.carpool.partyMatch.domain.MatchStatus;
import com.carpool.partyMatch.domain.PartyStatus;
import com.carpool.partyMatch.domain.kafka.MatchAccepted;
import com.carpool.partyMatch.domain.kafka.MatchCancelled;
import com.carpool.partyMatch.domain.kafka.PartyStarted;
import com.carpool.partyMatch.domain.kafka.PartyClosed;
import com.carpool.partyMatch.repository.MatchInfoRepository;
import com.carpool.partyMatch.repository.PartyRepository;
import com.carpool.partyMatch.service.MatchInfoService;
import com.carpool.partyMatch.exception.ApiException;
import com.carpool.partyMatch.exception.ErrorCode;

import lombok.extern.slf4j.Slf4j;
import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
@Slf4j
public class MatchInfoServiceImpl implements MatchInfoService {

  private final MatchInfoRepository matchInfoRepository;
  private final PartyRepository partyRepository;

    @Override
    public List<MatchInfo> findMatchUsers(Long partyInfoId){
        log.info("********* findMatchUsers Service *********");

        List<MatchInfo> matchInfoList = matchInfoRepository.findByPartyInfoIdAndMatchStatus(partyInfoId, MatchStatus.WATING)
                .orElseThrow(() -> new ApiException(ApiStatus.NOT_EXIST_MATCH));

        return matchInfoList;
    }

    @Override
    public MatchInfo registerMatchInfo(MatchInfoDto matchInfoDto){
        log.info("********* registerMatchInfo *********");
        log.debug(String.valueOf(matchInfoDto));

        //파티 상태 확인 (시작 또는 종료이면 신청 불가)
        MatchInfo matchInfo = new MatchInfo();
        matchInfo.setPartyInfoId(matchInfoDto.getPartyInfoId());
        matchInfo.setUserId(matchInfoDto.getUserId());
        matchInfo.setMatchStatus(MatchStatus.WATING);

        matchInfoRepository.save(matchInfo);

        return matchInfo;
    }

    @Override
    public MatchInfo cancelMatchInfo(MatchInfoDto matchInfoDto){
        log.info("********* cancelMatchInfo *********");
        log.debug(String.valueOf(matchInfoDto));

        //파티 상태 확인, 신청자 확인 (시작 또는 종료이면 취소 불가)
        MatchInfo matchInfo = matchInfoRepository.findByPartyInfoIdAndUserId(matchInfoDto.getPartyInfoId(), matchInfoDto.getUserId())
        .orElseThrow(() -> new ApiException(ApiStatus.NOT_EXIST_QUESTION));

        //매칭 수락 정보 확인
        boolean isAccepted = false;
        if(matchInfo.getMatchStatus() == MatchStatus.ACCEPT) isAccepted = true;

        matchInfo.setMatchStatus(MatchStatus.CANCEL);
        matchInfoRepository.save(matchInfo);

        //매칭 취소 이벤트 발행
        if(isAccepted){
             MatchCancelled matchCancelled = new MatchCancelled();
             BeanUtils.copyProperties(matchInfo, matchCancelled);
             matchCancelled.publish();
        }

        return matchInfo;
    }

    @Override
    public MatchInfo acceptMatchInfo(MatchProcessDto matchProcessDto){
        log.info("********* acceptMatchInfo *********");
        log.debug(String.valueOf(matchProcessDto));

        Party party = partyRepository.findByPartyInfoId(matchProcessDto.getPartyInfoId());
        MatchInfo matchInfo =  matchInfoRepository.findByPartyInfoIdAndUserId(matchProcessDto.getPartyInfoId(), matchProcessDto.getUserId());

        //운전자 확인 또는 세션 확인
        Driver driver = party.getDriver();

        if(matchProcessDto.getDriverId() == driver.getDriverId()){
            matchInfo.setMatchStatus(MatchStatus.ACCEPT);
            matchInfoRepository.save(matchInfo);

            //매칭 수락 이벤트 발행
            MatchAccepted matchAccepted = new MatchAccepted();
            BeanUtils.copyProperties(matchInfo, matchAccepted);
            matchAccepted.publish();

        }

        return matchInfo;
    }

    //파티관리 서비스에서 신청 가능 인원 확인 후 신청 불가할 경우에도 아래 실행
    @Override
    public MatchInfo denyMatchInfo(MatchProcessDto matchProcessDto){
        log.info("********* denyMatchInfo *********");
        log.debug(String.valueOf(matchProcessDto));

        Party party = partyRepository.findByPartyInfoId(matchProcessDto.getPartyInfoId());
        MatchInfo matchInfo = matchInfoRepository.findByPartyInfoIdAndUserId(matchProcessDto.getPartyInfoId(), matchProcessDto.getUserId());

        //운전자 확인 또는 세션 확인
        Driver driver = party.getDriver();

        if(matchProcessDto.getDriverId() == driver.getDriverId()){
            matchInfo.setMatchStatus(MatchStatus.DENY);
        }

        matchInfoRepository.save(matchInfo);

        return matchInfo;
    }


    @Override
    public PartyProcessResponse startParty(PartyProcessDto partyProcessDto){
        log.info("********* startParty *********");
        log.debug(String.valueOf(partyProcessDto));

        Party party = partyRepository.findByPartyInfoId(partyProcessDto.getPartyInfoId());

        //운전자 확인 또는 세션 확인
        Driver driver = party.getDriver();

        if(partyProcessDto.getDriverId() == driver.getDriverId()){
            party.setPartyStatus(PartyStatus.FORMED);
            partyRepository.save(party);

            //파티 시작 이벤트 발행
            PartyStarted partyStarted = new PartyStarted();
            BeanUtils.copyProperties(party, partyStarted);
            partyStarted.publish();
        }

        PartyProcessResponse response = new PartyProcessResponse(party.getPartyInfoId(), party.getPartyStatus());

        return response;
    }


    @Override
    public PartyProcessResponse closeParty(PartyProcessDto partyProcessDto){
        log.info("********* closeParty *********");
        log.debug(String.valueOf(partyProcessDto));

        Party party = partyRepository.findByPartyInfoId(partyProcessDto.getPartyInfoId());

        //운전자 확인 또는 세션 확인
        Driver driver = party.getDriver();

        if(partyProcessDto.getDriverId() == driver.getDriverId()){
            party.setPartyStatus(PartyStatus.CLOSED);
            partyRepository.save(party);

            //파티 종료 이벤트 발행
            PartyClosed partyClosed = new PartyClosed();
            BeanUtils.copyProperties(party, partyClosed);
            partyClosed.publish();
        }

        PartyProcessResponse response = new PartyProcessResponse(party.getPartyInfoId(), party.getPartyStatus());

        return response;
    }

}
