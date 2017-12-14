package ch.mare.trainingplanner.infrastructure;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class VertecService {

    private final RestTemplate restTemplate;

    public VertecService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public EmployeeWorkLoadDto findWorkloadFor(String employeeName) {
        try {
            return this.restTemplate.getForObject("http://vertec.zuehlke.com/employees?name={employeeName}", EmployeeWorkLoadDto.class, employeeName);
        } catch (HttpStatusCodeException ex) {
            if (NOT_FOUND.equals(ex.getStatusCode())) {
                throw new IllegalArgumentException("Employee with name " + employeeName + " does not exist");
            }
            throw ex;
        }
    }
}
