package ch.mare.trainingplanner.repository;


import ch.mare.trainingplanner.domain.Employee;
import ch.mare.trainingplanner.domain.Training;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Optional<Employee> findEmployeeByName(String name);
}
