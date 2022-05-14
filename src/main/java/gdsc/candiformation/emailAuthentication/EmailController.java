package gdsc.candiformation.emailAuthentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


//  @PostMapping("/verifyCode/{userEmail}/{code}")
//  public EmailResponse verifyCode(@PathVariable String code, @PathVariable(value = "userEmail") String userEmail) {
////    boolean codeResult = false;
////    boolean emailResult = false;
//    EmailResponse emailResponse;
//
////    if (EmailServiceImpl.ePw.equals(code)) {
////      codeResult = true;
////    }
////
////    if(emailService.checkUserEmail(userEmail,code)){
////      emailResult = true;
////    }
//
//    if(emailService.isMatch(userEmail, code)){
//      emailResponse = new EmailResponse("올바른 코드입니다.",true);
//    }else{
//      emailResponse = new EmailResponse("코드가 맞지 않습니다.",false);
//    }
//    return emailResponse;
//  }

  @PostMapping("/verifyCode/{userEmail}/{code}")
  public boolean verifyCode(@PathVariable String code, @PathVariable(value = "userEmail") String userEmail) {
    return emailService.isMatch(userEmail, code);
  }

}
