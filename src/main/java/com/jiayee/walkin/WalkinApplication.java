package com.jiayee.walkin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

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
