package gdsc.candiformation.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import gdsc.candiformation.dto.UserFormDTO;
import gdsc.candiformation.emailAuthentication.EmailServiceImpl;
import gdsc.candiformation.response.SuccessAndFailureResponse;
import gdsc.candiformation.service.UserServiceImpl;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserServiceImpl userService;
    private final EmailServiceImpl emailService;

    @PostMapping("") //회원가입할 때
    public ResponseEntity<?> insertUser(
      @Valid @RequestBody UserFormDTO dto,
      BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else if (userService.duplicateUsernameAndNickname(dto.toEntity()).getStatusCode().is4xxClientError()) {
            ResponseEntity<?> responseEntity = userService.duplicateUsernameAndNickname(dto.toEntity());
            SuccessAndFailureResponse successAndFailureResponse = null;
            if (responseEntity.getBody() == "해당 닉네임이 이미 존재합니다.") {
                successAndFailureResponse = new SuccessAndFailureResponse(responseEntity.getBody(), 400);
            } else if (responseEntity.getBody() == "해당 이메일이 이미 존재합니다.") {
                successAndFailureResponse = new SuccessAndFailureResponse(responseEntity.getBody(), 400);
            }
            return new ResponseEntity<>(successAndFailureResponse, HttpStatus.BAD_REQUEST);

        }

        userService.saveOrUpdateAccount(dto.toEntity());
        SuccessAndFailureResponse successAndFailureResponse = new SuccessAndFailureResponse("회원가입에 성공했습니다.", 201);
        return new ResponseEntity<>(successAndFailureResponse, HttpStatus.CREATED);
    }

    @GetMapping("")
    public ResponseEntity<?> viewAccount() {

        return new ResponseEntity<>("Success!", HttpStatus.OK);
    }

    @PostMapping("/duplication/{email}")
    public boolean emailDuplicationCheck(@PathVariable String email) throws Exception {
        boolean isEmailExist = userService.emailDuplicationCheck(email);
        if (isEmailExist) {
            return false;
        } else {
            // emailService.sendSimpleMessage(email);
            return true;
        }
    }

    @PostMapping("/duplication/{email}/{password}/{nickname}")
    public boolean nicknameDuplicationCheck(@PathVariable String email, @PathVariable String password, @PathVariable String nickname) {
        return userService.nicknameDuplicationCheck(email, password, nickname);
    }
}

