package com.carpool.partyMatch.client;

import com.carpool.partyMatch.client.dto.UserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "user", url = "http://localhost:8082/api/user")
public interface UserServiceClient {

    @GetMapping(path = "")
    List<UserResponse> getUserList(@RequestParam List<Long> userIds);

}
