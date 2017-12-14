package ch.mare.trainingplanner.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingRestController {

    @GetMapping("/hello")
    public String sayHello(){
        return "hello again";
    }

    @GetMapping("/greet")
    public GreetingDto greetPerson(@RequestParam("name") String name, @RequestParam("greeting") String greeting) {
        return new GreetingDto(name, greeting);
    }

}
