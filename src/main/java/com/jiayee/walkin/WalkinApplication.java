package com.jiayee.walkin;

import java.util.TimeZone;
import javax.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

@SpringBootApplication
@EntityScan(basePackageClasses = {
    WalkinApplication.class,
    Jsr310JpaConverters.class
})
public class WalkinApplication {

  @PostConstruct
  void init() {
    TimeZone.setDefault(TimeZone.getTimeZone("Asia/Singapore"));
  }

  public static void main(String[] args) {
    SpringApplication.run(WalkinApplication.class, args);
  }

}
