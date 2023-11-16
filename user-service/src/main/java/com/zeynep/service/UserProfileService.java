package com.zeynep.service;

import com.zeynep.dto.request.ActivateStatusRequestDto;
import com.zeynep.dto.request.UserCreateRequestDto;
import com.zeynep.dto.request.UserUpdateRequestDto;
import com.zeynep.exception.ErrorType;
import com.zeynep.exception.UserManagerException;
import com.zeynep.mapper.UserMapper;
import com.zeynep.repository.UserRepository;
import com.zeynep.repository.entity.UserProfile;
import com.zeynep.utility.ServiceManager;
import com.zeynep.utility.enums.EStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserProfileService extends ServiceManager<UserProfile,Long> {

    private final UserRepository userRepository;
    public UserProfileService(UserRepository userRepository) {
        super(userRepository);
        this.userRepository=userRepository;
    }

    public Boolean createUser(UserCreateRequestDto dto){
        try {
            save(UserMapper.INSTANCE.fromCreateRequestToUser(dto));
            return true;
        }catch (Exception e){
            throw new UserManagerException(ErrorType.USER_NOT_CREATED);
        }
    }

    public Boolean activateStatus(Long authId) {
        Optional<UserProfile> userProfile = userRepository.findOptionalByAuthId(authId);
        if(userProfile.isEmpty()){
            throw new UserManagerException(ErrorType.USER_NOT_FOUND);
        }else {
            userProfile.get().setStatus(EStatus.ACTIVE);
            update(userProfile.get());
            return true;
        }
    }

    public Boolean activateStatus2(ActivateStatusRequestDto dto) {
        Optional<UserProfile> userProfile = userRepository.findOptionalByAuthId(dto.getAuthId());
        if(userProfile.isEmpty()){
            throw new UserManagerException(ErrorType.USER_NOT_FOUND);
        }else {
            userProfile.get().setStatus(EStatus.ACTIVE);
            update(userProfile.get());
            return true;
        }
    }

    public Boolean updateUser(UserUpdateRequestDto dto) {
        try {
            update(UserMapper.INSTANCE.fromUpdateRequestToUser(dto));
            return true;
        } catch (Exception e){
            throw new UserManagerException(ErrorType.USER_NOT_FOUND);
        }
    }
}
