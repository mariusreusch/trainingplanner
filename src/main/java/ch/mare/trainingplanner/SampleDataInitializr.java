package ch.mare.trainingplanner;

import ch.mare.trainingplanner.domain.Employee;
import ch.mare.trainingplanner.domain.Training;
import ch.mare.trainingplanner.repository.EmployeeRepository;
import ch.mare.trainingplanner.repository.TrainingRepository;
import org.javamoney.moneta.Money;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

import static java.time.ZonedDateTime.now;

@Component
public class SampleDataInitializr implements ApplicationRunner {

    private final TrainingRepository trainingRepository;
    private final EmployeeRepository employeeRepository;

    public SampleDataInitializr(TrainingRepository trainingRepository, EmployeeRepository employeeRepository) {
        this.trainingRepository = trainingRepository;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public void run(ApplicationArguments args) {
        Training devoxx = trainingRepository.save(new Training("Devoxx", "Java Conference", Money.of(900, "CHF"), now().minusDays(30), now().minusDays(27)));
        trainingRepository.findAll().forEach(System.out::println);

        Employee entity = new Employee("Peterchen", Money.of(5000, "CHF"));
        entity.addTraining(devoxx);
        employeeRepository.save(entity);

        employeeRepository.findAll().forEach(System.out::println);

    }
}
