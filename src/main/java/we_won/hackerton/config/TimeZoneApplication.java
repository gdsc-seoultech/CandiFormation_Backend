package we_won.hackerton.config;

import org.springframework.boot.SpringApplication;
import we_won.hackerton.HackertonApplication;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

public class TimeZoneApplication {

    @PostConstruct
    void started() {
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Seoul"));
    }

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(HackertonApplication.class);
        application.setLogStartupInfo(false);
        application.run(args);
    }
}
