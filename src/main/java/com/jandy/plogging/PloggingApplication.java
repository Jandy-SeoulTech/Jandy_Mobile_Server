package com.jandy.plogging;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

// auditing 어노테이션 활성화
@EnableJpaAuditing
@SpringBootApplication
public class PloggingApplication {

	public static void main(String[] args) {
		SpringApplication.run(PloggingApplication.class, args);
	}

}
