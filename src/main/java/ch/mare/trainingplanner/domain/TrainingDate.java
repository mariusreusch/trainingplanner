package ch.mare.trainingplanner.domain;

import java.time.ZonedDateTime;

import static java.util.Objects.requireNonNull;

public class TrainingDate {

    private ZonedDateTime start;
    private ZonedDateTime end;

    private TrainingDate(ZonedDateTime start, ZonedDateTime end) {
        this.start = requireNonNull(start);
        this.end = requireNonNull(end);
        if(end.isBefore(start)){
            throw new IllegalArgumentException();
        }
    }

    public static TrainingDateBuilder from(ZonedDateTime start) {
        return new TrainingDateBuilder(requireNonNull(start));
    }

    public ZonedDateTime getStart() {
        return start;
    }

    public ZonedDateTime getEnd() {
        return end;
    }

    public static class TrainingDateBuilder {
        private final ZonedDateTime start;

        private TrainingDateBuilder(ZonedDateTime start) {
            this.start = start;
        }

        public TrainingDate until(ZonedDateTime end) {
            return new TrainingDate(start, end);
        }
    }
}
