package gdsc.candiformation.emailAuthentication;

public interface EmailService {
  String sendSimpleMessage(String to)throws Exception;
  boolean checkUserEmail(String email,String code);
  void deleteByEmail(String email);
  boolean isMatch(String email, String code);
}

