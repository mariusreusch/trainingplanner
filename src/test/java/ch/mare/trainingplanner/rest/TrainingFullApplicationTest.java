package ch.mare.trainingplanner.rest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static java.time.ZonedDateTime.now;
import static org.assertj.core.api.Assertions.assertThat;
import static org.javamoney.moneta.Money.of;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class TrainingFullApplicationTest {

    @LocalServerPort
    private int serverPort;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void getAllTrainings_validRequest_statusIsOk() {
        String urlForAllTrainings = "http://localhost:" + serverPort + "/trainings";

        ResponseEntity<List> response = testRestTemplate.getForEntity(urlForAllTrainings, List.class);

        assertThat(response.getStatusCode()).isEqualTo(OK);
        assertThat(response.getBody()).isNotEmpty();
    }

    @Test
    public void createNewTraining_validInput_statusIsCreated() throws Exception {
        String baseUrlForTrainingRestService = "http://localhost:" + serverPort + "/trainings";
        String title = "An extraordinary workshop";
        TrainingDto trainingToCreate = new TrainingDto(title, "Java workshop", of(450, "CHF"), now().minusDays(10), now().plusDays(20));

        ResponseEntity<Void> response = testRestTemplate.postForEntity(baseUrlForTrainingRestService, trainingToCreate, Void.class);

        assertThat(response.getStatusCode()).isEqualTo(CREATED);
        ResponseEntity<TrainingDto> createdTrainingDto = testRestTemplate.getForEntity(baseUrlForTrainingRestService.concat("/?title=" + title), TrainingDto.class);
        assertThat(createdTrainingDto.getBody().getTitle()).isEqualTo(title);
    }
}