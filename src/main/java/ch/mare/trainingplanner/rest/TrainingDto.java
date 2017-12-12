package ch.mare.trainingplanner.rest;

import ch.mare.trainingplanner.domain.Training;
import ch.mare.trainingplanner.domain.TrainingDate;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.javamoney.moneta.Money;

import java.time.ZonedDateTime;
import java.util.Objects;

public class TrainingDto {

    private final String title;
    private final String description;
    private final Money cost;
    private final ZonedDateTime start;
    private final ZonedDateTime end;

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
        return new Training(title, description, cost, TrainingDate.from(start).until(end));
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TrainingDto that = (TrainingDto) o;
        return Objects.equals(title, that.title) &&
                Objects.equals(description, that.description) &&
                Objects.equals(cost, that.cost) &&
                Objects.equals(start, that.start) &&
                Objects.equals(end, that.end);
    }

    @Override
    public int hashCode() {

        return Objects.hash(title, description, cost, start, end);
    }
}
