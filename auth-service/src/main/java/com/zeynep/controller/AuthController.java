package com.zeynep.controller;

import com.zeynep.dto.request.ActivationRequestDto;
import com.zeynep.dto.request.LoginRequestDto;
import com.zeynep.dto.request.RegisterRequestDto;
import com.zeynep.dto.request.UserUpdateRequestDto;
import com.zeynep.dto.response.RegisterResponseDto;
import com.zeynep.repository.entity.Auth;
import com.zeynep.service.AuthService;
import com.zeynep.utility.JwtTokenManager;
import com.zeynep.utility.enums.ERole;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.zeynep.constants.RestApi.*;

/*
   Register olduktan sonra bize bir activationCode geliyor.
   Bu activation Code'u kullanarak user'ımın statusunu degistirmek istiyorum.
   Buna uygun bir metot yazalim.
 */
/*
    Auth'da activateStatus işlemini gerçekleştirdiğimizde user tarafı da bu
    güncellemeyi alsın ve statusu değişsin.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(AUTH)
public class AuthController {

    /**
     * login metodumuzu düzenleyelim.
     * bize bir token üretip bu token'ı dönsün.
     * ayrıca sadece status'u active olan kullanıcılar giriş yapabilsin.
     *
     */
    private final AuthService authService;
    private final JwtTokenManager tokenManager;
    @PostMapping(REGISTER)
    public ResponseEntity<RegisterResponseDto>  register (@RequestBody @Valid RegisterRequestDto dto){
        return ResponseEntity.ok(authService.register(dto));
    }
    @PostMapping(LOGIN)
    public ResponseEntity<String > login (@RequestBody @Valid LoginRequestDto dto){
        return ResponseEntity.ok(authService.login(dto));
    }
    @PostMapping(ACTIVATESTATUS)
    public ResponseEntity<Boolean> activateStatus(ActivationRequestDto dto){
        return ResponseEntity.ok(authService.activateStatus(dto));
    }

    @GetMapping(FINDALL)
    public ResponseEntity<List<Auth>> findAll(){
        return ResponseEntity.ok(authService.findAll());
    }

    @GetMapping("/create_token")
    public ResponseEntity<String> createToken(Long id, ERole role){
        return ResponseEntity.ok(tokenManager.createToken(id,role).get());
    }

    @GetMapping("/create_token2")
    public ResponseEntity<String> createToken(Long id){
        return ResponseEntity.ok(tokenManager.createToken(id).get());
    }

    @GetMapping("/get_id_from_token")
    public ResponseEntity<Long> getIdFromToken(String token){
        return ResponseEntity.ok(tokenManager.getIdFromToken(token).get());
    }
    @GetMapping("/get_role_from_token")
    public ResponseEntity<String> getRoleFromToken(String token){
        return ResponseEntity.ok(tokenManager.getRoleFromToken(token).get());
    }

    @PostMapping(UPDATE)
    public ResponseEntity<Boolean> updateUser(UserUpdateRequestDto dto){
        return ResponseEntity.ok(authService.updateUser(dto));
    }

}
