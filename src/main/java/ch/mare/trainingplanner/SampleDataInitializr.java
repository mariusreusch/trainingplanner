package ch.mare.trainingplanner;

import ch.mare.trainingplanner.domain.Training;
import ch.mare.trainingplanner.repository.TrainingRepository;
import org.javamoney.moneta.Money;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;

import static java.time.ZonedDateTime.now;

@Component
public class SampleDataInitializr implements ApplicationRunner {

    private final TrainingRepository trainingRepository;

    public SampleDataInitializr(TrainingRepository trainingRepository) {
        this.trainingRepository = trainingRepository;
    }

    @Override
    public void run(ApplicationArguments args) {
        trainingRepository.save(new Training("Devoxx", "Java Conference", Money.of(900, "CHF"), now().minusDays(30), now().minusDays(27)));
        trainingRepository.findAll().forEach(System.out::println);

    }
}
