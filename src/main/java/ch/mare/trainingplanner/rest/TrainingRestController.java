package ch.mare.trainingplanner.rest;

import ch.mare.trainingplanner.service.TrainingService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/trainings")
public class TrainingRestController {

    private final TrainingService trainingService;

    public TrainingRestController(TrainingService trainingService) {
        this.trainingService = trainingService;
    }

    @GetMapping
    public List<TrainingDto> getAllTrainings() {
        return trainingService.findAllTrainings();
    }

    @GetMapping("/hello")
    public String sayHello() {
        return "hello";
    }

}
