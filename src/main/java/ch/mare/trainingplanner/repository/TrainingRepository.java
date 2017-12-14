package ch.mare.trainingplanner.repository;


import ch.mare.trainingplanner.domain.Training;
import ch.mare.trainingplanner.rest.TrainingDto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainingRepository extends JpaRepository<Training, Long> {

    TrainingDto findTrainingByTitle(String title);
}
