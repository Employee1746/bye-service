package eurekabye.bye.microservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ByeController {

    final ByeService byeService;

    public ByeController(ByeService byeService) {
        this.byeService = byeService;
    }

    @GetMapping("/bye")
    public String sayBye() {
        Long helloCount = byeService.countGreetings();
        return "Всего доброго! Вы здоровались " + helloCount + " раз";
    }
}