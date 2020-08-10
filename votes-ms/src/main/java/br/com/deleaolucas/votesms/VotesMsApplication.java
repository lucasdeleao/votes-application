package br.com.deleaolucas.votesms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
@EnableAutoConfiguration
public class VotesMsApplication {

	public static void main(String[] args) {
		SpringApplication.run(VotesMsApplication.class, args);
	}

}
