package we_won.hackerton.emailAuthentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import we_won.hackerton.Interface.EmailRepository;
import we_won.hackerton.entity.Email;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Optional;
import java.util.Random;

@Service
public class EmailServiceImpl implements EmailService{

  JavaMailSender emailSender;
  private final EmailRepository emailRepository;
  public static String ePw = null;

  @Autowired
  public EmailServiceImpl(JavaMailSender emailSender, EmailRepository emailRepository) {
    this.emailSender = emailSender;
    this.emailRepository = emailRepository;
  }




  private MimeMessage createMessage(String to) throws Exception {
    //System.out.println("createMessage 실행되냐");
    System.out.println("보내는 대상 : " + to);
    System.out.println("인증 번호 : "+ ePw);

    MimeMessage message = emailSender.createMimeMessage();

    message.addRecipients(Message.RecipientType.TO, to);
    message.setSubject("CandiFormation 이메일 본인인증 코드 메일입니다.");

    String contentMessage = "";
    contentMessage += "<div style='margin:100px;'>";
    contentMessage += "<h1> 안녕하세요! CandiFormation 입니다. </h1>";
    contentMessage += "<br>";
    contentMessage += "<p>아래 코드를 회원가입 창으로 돌아가 입력해주세요.</p>";
    contentMessage += "<br>";
    contentMessage += "<p></p>";
    contentMessage += "<br>";
    contentMessage += "<div align='center' style='border:1px solid black; font-family:verdana';>";
    contentMessage += "<h3 style='color:blue;'>회원가입 인증 코드입니다.</h3>";
    contentMessage += "<div style='font-size:130%'>";
    contentMessage += "CODE : <strong>";
    contentMessage += ePw + "</strong><div><br/> ";
    contentMessage += "</div>";
    message.setText(contentMessage, "utf-8", "html"); // 내용
    message.setFrom(new InternetAddress("candiformation@gmail.com","CandiFormation", "utf-8")); // 보내는 사람

    return message;
  }

  public static String createKey() {
    StringBuffer stringBuffer = new StringBuffer();
    Random random = new Random();

    for (int i = 0; i < 9; i++) {
      int index = random.nextInt(3); // 0~1

      switch (index) {
        case 0:
          stringBuffer.append((char) ((int) (random.nextInt(26)) + 97)); // a~z
          break;
        case 1:
          stringBuffer.append((random.nextInt(10))); // 0~9
          break;
        case 2:
          stringBuffer.append(((char) ((int) (random.nextInt(26)) + 65))); // A-Z
          break;
      }
    }
    System.out.println("run을 눌렀을 때의 코드 : " + stringBuffer.toString());
    return stringBuffer.toString();
  }

  @Override
  public String sendSimpleMessage(String to) throws Exception {
    //System.out.println("sendSimple 실행되냐");
    EmailServiceImpl.ePw = createKey();

    MimeMessage message = createMessage(to);
    try {
      emailSender.send(message);
    } catch (MailException e) {
      e.printStackTrace();
      throw new IllegalArgumentException();
    }
    //DB 관련 코드
    //ePw 인증 번호 to가 이메일
    System.out.println("여기가 이메일을 보내는 곳입니다~");
    Optional<Email> email = emailRepository.findByEmail(to);

    if(email.isPresent()){
      //있으면 코드를 초기화 시켜줘야됨
      System.out.println("email 존재 : " + email.get().getEmail());
      email.get().setCode(ePw);
      emailRepository.save(email.get());

    }else{
      System.out.println("email 없음");
      Email saveEmail = new Email(to,ePw);
      emailRepository.save(saveEmail);
    }


    //DB 관련 코드


    return ePw;
  }

  @Override
  public boolean checkUserEmail(String emailByUser,String code) {
    Optional<Email> emailByCode = emailRepository.findByCode(code);

    if(emailByCode.isEmpty()){
      return false;
    }else{
      if(emailByUser.equals(emailByCode.get().getEmail())){
        return true;
      }
      return false;
    }
  }

  @Override
  public void deleteByEmail(String email) {
    emailRepository.deleteByEmail(email);
  }
}
