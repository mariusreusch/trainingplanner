package ch.mare.trainingplanner.rest;

import ch.mare.trainingplanner.service.TrainingService;
import org.javamoney.moneta.Money;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.time.ZonedDateTime;
import java.util.Arrays;

import static java.time.ZoneId.systemDefault;
import static java.time.ZonedDateTime.of;
import static org.mockito.BDDMockito.given;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TrainingRestController.class)
public class TrainingRestControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private TrainingService trainingService;

    @Test
    public void getAllTrainings_validRequest_statusIsOk() throws Exception {
        mvc.perform(get("/trainings"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());
    }

    @Test
    public void getAllTrainings_validRequest_statusIsOkAndContentMatches() throws Exception {
        ZonedDateTime fromJanuaryTheFirst = of(2017, 1, 1, 0, 0, 0, 0, systemDefault());
        ZonedDateTime tillJanuaryTheSecond = of(2017, 1, 3, 0, 0, 0, 0, systemDefault());
        given(trainingService.findAllTrainings()).willReturn(Arrays.asList(
                new TrainingDto("Test training", "A fantastic workshop", Money.of(100, "CHF"),
                        fromJanuaryTheFirst, tillJanuaryTheSecond)));

        ResultActions result = mvc.perform(get("/trainings"));

        result.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().json("[\n" +
                        "  {\n" +
                        "    \"title\": \"Test training\",\n" +
                        "    \"description\": \"A fantastic workshop\",\n" +
                        "    \"cost\": {\n" +
                        "      \"amount\": 100.0,\n" +
                        "      \"currency\": \"CHF\"\n" +
                        "    },\n" +
                        "    \"start\": \"2017-01-01T00:00:00+01:00\",\n" +
                        "    \"end\": \"2017-01-03T00:00:00+01:00\"\n" +
                        "  }\n" +
                        "]"))
                .andExpect(status().isOk());
    }

    @Test
    public void createNewTraining_validInput_statusIsCreated() throws Exception {
        ResultActions result = mvc.perform(post("/trainings")
                .contentType(APPLICATION_JSON)
                .content("{\n" +
                        "  \"title\": \"A new training\",\n" +
                        "  \"description\": \"A gorgeous training.\",\n" +
                        "  \"cost\": {\n" +
                        "    \"amount\": 400.0,\n" +
                        "    \"currency\": \"CHF\"\n" +
                        "  },\n" +
                        "  \"start\": \"2017-02-01T00:00:00+01:00\",\n" +
                        "  \"end\": \"2017-02-03T00:00:00+01:00\"\n" +
                        "}"));

        result.andExpect(status().isCreated());
    }
}