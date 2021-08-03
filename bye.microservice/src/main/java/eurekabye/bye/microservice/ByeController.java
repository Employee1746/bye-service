package eurekabye.bye.microservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ByeController {

    final Bye bye;

    public ByeController(Bye bye) {
        this.bye = bye;
    }

    @GetMapping("/bye")
    public Bye sayHello() {
        return bye;
    }


}
