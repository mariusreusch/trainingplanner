package ch.mare.trainingplanner.repository;

import ch.mare.trainingplanner.domain.Employee;
import org.assertj.core.api.Assertions;
import org.javamoney.moneta.Money;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    public void findEmployeeByName_knownUsername_foundEmployee() {
        employeeRepository.save(new Employee("Kalle", Money.of(5000, "CHF")));

        Optional<Employee> kalle = employeeRepository.findEmployeeByName("Kalle");

        assertThat(kalle.isPresent()).isTrue();
    }
}