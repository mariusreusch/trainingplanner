package ch.mare.trainingplanner.rest;

import ch.mare.trainingplanner.common.OnlyForFramework;
import ch.mare.trainingplanner.domain.Training;
import org.javamoney.moneta.Money;

import java.time.ZonedDateTime;

public class TrainingDto {

    private String title;
    private String description;
    private Money cost;
    private ZonedDateTime start;
    private ZonedDateTime end;

    public TrainingDto(String title, String description, Money cost, ZonedDateTime start, ZonedDateTime end) {
        this.title = title;
        this.description = description;
        this.cost = cost;
        this.start = start;
        this.end = end;
    }

    @OnlyForFramework
    private TrainingDto() {
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

    public static TrainingDto from(Training training) {
        return new TrainingDto(training.getTitle(), training.getDescription(),
                training.getCost(), training.getStart(), training.getEnd());
    }

    public Training toEntity() {
        return new Training(title, description, cost, start, end);
    }

    @Override
    public String toString() {
        return "TrainingDto{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", cost=" + cost +
                ", start=" + start +
                ", end=" + end +
                '}';
    }
}
