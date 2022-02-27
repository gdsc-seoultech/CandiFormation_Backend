package we_won.hackerton.emailAuthentication;

public interface EmailService {
  String sendSimpleMessage(String to)throws Exception;
  boolean checkUserEmail(String email,String code);
  void deleteByEmail(String email);
}

