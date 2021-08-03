package eurekabye.bye.microservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean(name = "bye")
	public Bye getHello() {
		Bye hello = new Bye();
		hello.setMessage("Всего доброго!");
		return hello;
	}

}
