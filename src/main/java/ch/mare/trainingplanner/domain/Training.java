package ch.mare.trainingplanner.domain;

import ch.mare.trainingplanner.common.OnlyForFramework;
import org.javamoney.moneta.Money;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.ZonedDateTime;

import static java.util.Objects.requireNonNull;

@Entity
public class Training {

    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private String description;
    private Money cost;
    private ZonedDateTime start;
    private ZonedDateTime end;

    public Training(String title, String description, Money cost, ZonedDateTime start, ZonedDateTime end) {
        this.title = requireNonNull(title);
        this.description = requireNonNull(description);
        this.cost = requireNonNull(cost);
        this.start = requireNonNull(start);
        this.end = requireNonNull(end);
    }

    @OnlyForFramework
    private Training() {
        //leider keine final fields
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Money getCost() {
        return cost;
    }

    public ZonedDateTime getStart() {
        return start;
    }

    public ZonedDateTime getEnd() {
        return end;
    }

    @Override
    public String toString() {
        return "Training{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", cost=" + cost +
                ", start=" + start +
                ", end=" + end +
                '}';
    }
}
