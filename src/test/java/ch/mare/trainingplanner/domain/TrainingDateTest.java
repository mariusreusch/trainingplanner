package ch.mare.trainingplanner.domain;

import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.Test;

import java.time.ZonedDateTime;

import static java.time.ZoneId.systemDefault;
import static java.time.ZonedDateTime.now;
import static java.time.ZonedDateTime.of;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class TrainingDateTest {

    @Test
    public void constructTrainingDate_validInput_constructedTrainingDate() {
        TrainingDate trainingDate = TrainingDate.from(now().minusDays(1)).until(now());

        assertThat(trainingDate).isNotNull();
    }

    @Test
    public void constructTrainingDate_endDateIsBeforeStartDate_throwsExcpetion() {
        ZonedDateTime januaryTheFirst = of(2017, 1, 1, 0, 0, 0, 0, systemDefault());
        ZonedDateTime januaryTheThird = of(2017, 1, 3, 0, 0, 0, 0, systemDefault());

        ThrowingCallable tryToConstructTrainingDate = () -> TrainingDate.from(januaryTheThird).until(januaryTheFirst);

        assertThatThrownBy(tryToConstructTrainingDate).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void constructTrainingDate_endDateIsNotSet_throwsException() {
        ThrowingCallable tryToConstructTrainingDate = () -> TrainingDate.from(now()).until(null);

        assertThatThrownBy(tryToConstructTrainingDate).isInstanceOf(NullPointerException.class);
    }

    @Test
    public void constructTrainingDate_startDateIsNotSet_throwsException() {
        ThrowingCallable tryToConstructTrainingDate = () -> TrainingDate.from(null).until(now());

        assertThatThrownBy(tryToConstructTrainingDate).isInstanceOf(NullPointerException.class);
    }
}