package ch.mare.trainingplanner.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(GreetingRestController.class)
public class GreetingRestControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void sayHello_validPath_correctResponse() throws Exception {
        ResultActions perform = mvc.perform(get("/hello"))
                .andExpect(status().isOk())
                .andExpect(content().string("hello again"));

        assertThat(perform.andReturn().getResponse().getStatus()).isEqualTo(200);

    }

    @Test
    public void greetPerson_nameAndGreeting_json() throws Exception {
        mvc.perform(get("/greet?name=kuni&greeting=hi"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"name\":\"kuni\", \"greeting\":\"hi\"}"));
    }

    @Test
    public void greetPerson_nameAndGreetingAsObject_json() throws Exception {
        mvc.perform(get("/greet?name=kuni&greeting=hi"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(new GreetingDto("kuni", "hi"))));
    }
}