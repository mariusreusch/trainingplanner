package ch.mare.trainingplanner.domain;

import ch.mare.trainingplanner.common.OnlyForFramework;
import org.javamoney.moneta.Money;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.List;

@Entity
public class Employee {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private Money availableBudget;
    @ManyToMany
    private List<Training> trainings;

    public Employee(String name, Money availableBudget, List<Training> trainings) {
        this.name = name;
        this.availableBudget = availableBudget;
        this.trainings = trainings;
    }

    @OnlyForFramework
    private Employee() {
    }
}
