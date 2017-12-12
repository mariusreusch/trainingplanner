package ch.mare.trainingplanner.rest;

import org.javamoney.moneta.Money;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.json.JsonContent;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.time.ZonedDateTime;

import static java.time.ZoneId.systemDefault;
import static java.time.ZonedDateTime.of;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@JsonTest
public class TrainingDtoJsonTest {

    @Autowired
    private JacksonTester<TrainingDto> json;

    @Test
    public void serializeTrainingDto_validDto_correctlySerialized() throws IOException {
        ZonedDateTime fromJanuaryTheFirst = of(2017, 1, 1, 0, 0, 0, 0, systemDefault());
        ZonedDateTime tillJanuaryTheThird = of(2017, 1, 3, 0, 0, 0, 0, systemDefault());
        TrainingDto trainingDto = new TrainingDto("A marvelous title", "Superb course", Money.of(300, "CHF"), fromJanuaryTheFirst, tillJanuaryTheThird);

        JsonContent<TrainingDto> traingDtoAsJson = json.write(trainingDto);

        assertThat(traingDtoAsJson).isStrictlyEqualToJson("{\n" +
                "  \"title\": \"A marvelous title\",\n" +
                "  \"description\": \"Superb course\",\n" +
                "  \"cost\": {\n" +
                "    \"amount\": 300.0,\n" +
                "    \"currency\": \"CHF\"\n" +
                "  },\n" +
                "  \"start\": \"2017-01-01T00:00:00+01:00\",\n" +
                "  \"end\": \"2017-01-03T00:00:00+01:00\"\n" +
                "}");
    }
}