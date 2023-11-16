package com.zeynep.manager;

import com.zeynep.dto.request.ActivateStatusRequestDto;
import com.zeynep.dto.request.ActivationRequestDto;
import com.zeynep.dto.request.UserCreateRequestDto;
import com.zeynep.dto.request.UserUpdateRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import static com.zeynep.constants.RestApi.ACTIVATESTATUS;
import static com.zeynep.constants.RestApi.UPDATE;


@FeignClient(url = "http://localhost:7071/api/v1/user", name = "auth-userprofile")
public interface UserManager {

    @PostMapping("/create")
    public ResponseEntity<Boolean> createUser(@RequestBody UserCreateRequestDto dto);

    @PostMapping("/activateStatus")
    public ResponseEntity<Boolean> activateStatus(@RequestBody ActivationRequestDto dto);

    @PostMapping(ACTIVATESTATUS+"2")
    public ResponseEntity<Boolean> activateStatus2(@RequestBody ActivateStatusRequestDto dto);

    @PostMapping(UPDATE)
    public Boolean updateUser(@RequestBody UserUpdateRequestDto dto);
}
