package ch.mare.trainingplanner.rest;

import ch.mare.trainingplanner.domain.Training;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.javamoney.moneta.Money;

import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;

public class TrainingDto {

    @NotNull
    private String title;
    private String description;
    private Money cost;
    private ZonedDateTime start;
    private ZonedDateTime end;

    @JsonCreator
    public TrainingDto(@JsonProperty(value = "title", required = true) String title,
                       @JsonProperty(value = "description", required = true) String description,
                       @JsonProperty(value = "cost", required = true)  Money cost,
                       @JsonProperty(value = "start", required = true) ZonedDateTime start,
                       @JsonProperty(value = "end", required = true) ZonedDateTime end) {
        this.title = title;
        this.description = description;
        this.cost = cost;
        this.start = start;
        this.end = end;
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
