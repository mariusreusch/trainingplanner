package ch.mare.trainingplanner.rest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
@AutoConfigureMockMvc
public class TrainingFullApplicationTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void getAllTrainings_validRequest_statusIsOk() throws Exception {
        mvc.perform(get("/trainings"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());
    }


    @Test
    public void createNewTraining_validInput_statusIsCreated() throws Exception {
        ResultActions result = mvc.perform(post("/trainings")
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