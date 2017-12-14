package ch.mare.trainingplanner.infrastructure;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.ThrowableAssert;
import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@RunWith(SpringRunner.class)
@RestClientTest(VertecService.class)
public class VertecServiceTest {

    private static final String VERTEC_BASE_URL = "http://vertec.zuehlke.com/employees?name=";

    @Autowired
    private VertecService vertecService;

    @Autowired
    private MockRestServiceServer server;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void findWorkloadFor_knownEmployeeName_validResponse() throws Exception {
        String employeeName = "Kuni";
        String responseBody = objectMapper.writeValueAsString(new EmployeeWorkLoadDto(employeeName, 80.0));
        this.server.expect(requestTo("http://vertec.zuehlke.com/employees?name=" + employeeName))
                .andRespond(withSuccess(responseBody, APPLICATION_JSON));

        EmployeeWorkLoadDto workLoadDto = vertecService.findWorkloadFor(employeeName);

        assertThat(workLoadDto.getEmployeeName()).isEqualTo(employeeName);
    }

    @Test
    public void findWorkloadFor_unknownEmployeeName_illegalArgumentException() {
        String unknownEmployeeName = "Bert";
        this.server.expect(requestTo(VERTEC_BASE_URL + unknownEmployeeName))
                .andRespond(withStatus(NOT_FOUND));

        ThrowingCallable tryToFindWorkload = () -> vertecService.findWorkloadFor(unknownEmployeeName);

        assertThatThrownBy(tryToFindWorkload).isInstanceOf(IllegalArgumentException.class);
    }
}