package ch.mare.trainingplanner.service;

import ch.mare.trainingplanner.repository.TrainingRepository;
import ch.mare.trainingplanner.rest.TrainingDto;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class TrainingService {

    private final TrainingRepository trainingRepository;

    public TrainingService(TrainingRepository trainingRepository) {
        this.trainingRepository = trainingRepository;
    }

    public List<TrainingDto> findAllTrainings() {
        return trainingRepository.findAll().stream()
                .map(TrainingDto::from)
                .collect(toList());
    }

    public void create(TrainingDto trainingDto) {
        this.trainingRepository.save(trainingDto.toEntity());
    }

    public TrainingDto findByTitle(String title) {
        return this.trainingRepository.findTrainingByTitle(title);
    }
}
