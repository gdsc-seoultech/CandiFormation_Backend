package we_won.hackerton.emailAuthentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import we_won.hackerton.response.EmailResponse;

@RestController
//@RequiredArgsConstructor
@RequestMapping("/api/authenticate")
public class EmailController {


  private final EmailServiceImpl emailService;

  @Autowired
  public EmailController(EmailServiceImpl emailService) {
    this.emailService = emailService;
  }

  @PostMapping("/mail/{email}")
  public void emailConfirm(@PathVariable String email) throws Exception {
    System.out.println("emailcheck = " + email);
    emailService.sendSimpleMessage(email);
  }


  @PostMapping("/verifyCode/{userEmail}/{code}")
  public EmailResponse verifyCode(@PathVariable String code, @PathVariable(value = "userEmail") String userEmail) {
    boolean codeResult = false;
    boolean emailResult = false;
    EmailResponse emailResponse;

    if (EmailServiceImpl.ePw.equals(code)) {
      codeResult = true;
    }

    if(emailService.checkUserEmail(userEmail,code)){
      emailResult = true;
    }

    if(codeResult && emailResult){
      emailResponse = new EmailResponse("회원가입에 성공했습니다.",true);
      emailService.deleteByEmail(userEmail);
    }else{
      emailResponse = new EmailResponse("코드가 맞지 않습니다.",false);
    }


    return emailResponse;
  }


}
