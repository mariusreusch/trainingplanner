package ch.mare.trainingplanner.domain;

import ch.mare.trainingplanner.common.OnlyForFramework;
import org.javamoney.moneta.Money;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Employee {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private Money availableBudget;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Training> trainings;

    public Employee(String name, Money availableBudget) {
        this.name = name;
        this.availableBudget = availableBudget;
        this.trainings = new ArrayList<>();
    }

    public void addTraining(Training training){
        this.trainings.add(training);
    }

    @OnlyForFramework
    private Employee() {
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", availableBudget=" + availableBudget +
                ", trainings=" + trainings +
                '}';
    }
}
