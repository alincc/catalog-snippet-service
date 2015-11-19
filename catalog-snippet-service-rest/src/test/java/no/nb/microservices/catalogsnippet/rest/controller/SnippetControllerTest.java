package no.nb.microservices.catalogsnippet.rest.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class SnippetControllerTest {

    private MockMvc mockMvc;

    private SnippetController homeController;

    @Before
    public void setup() {
        homeController = new SnippetController();
        mockMvc = MockMvcBuilders.standaloneSetup(homeController).build();
    }

    @Test
    public void helloWorldTest() throws Exception{
        mockMvc.perform(get("/"))
                .andExpect(status().isOk());
    }

}
