package ch.mare.trainingplanner.rest;

import ch.mare.trainingplanner.service.TrainingService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/trainings")
public class TrainingRestController {

    private final TrainingService trainingService;

    public TrainingRestController(TrainingService trainingService) {
        this.trainingService = trainingService;
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public void createNewTraining(@RequestBody TrainingDto trainingDto) {
        trainingService.create(trainingDto);

    }

    @GetMapping
    public List<TrainingDto> getAllTrainings() {
        return trainingService.findAllTrainings();
    }

    @GetMapping("/")
    public TrainingDto getTrainingByTitle(@RequestParam(name = "title") String title) {
        return trainingService.findByTitle(title);
    }
}
