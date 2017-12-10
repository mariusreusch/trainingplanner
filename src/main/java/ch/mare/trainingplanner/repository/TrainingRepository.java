package ch.mare.trainingplanner.repository;


import ch.mare.trainingplanner.domain.Training;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainingRepository extends JpaRepository<Training, Long> {
}
