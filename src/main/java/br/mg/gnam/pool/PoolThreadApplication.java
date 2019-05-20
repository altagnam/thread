package br.mg.gnam.pool;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class PoolThreadApplication {

	public static void main(String[] args) {
		SpringApplication.run(PoolThreadApplication.class, args);
	}

}
