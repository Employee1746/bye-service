package eurekabye.bye.microservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;

@SpringBootApplication
@EnableKafka
public class Application {
	final
	ByeService byeService;

	public Application(ByeService byeService) {
		this.byeService = byeService;
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@KafkaListener(topics="hello")
	public void msgListener(String msg){
		System.out.println(msg);
	}
}
