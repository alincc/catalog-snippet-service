package no.nb.microservices.catalogsnippet.rest.controller;

import no.nb.microservices.catalogsnippet.core.iiif.service.IContentSearchService;
import no.nb.microservices.catalogsnippet.core.image.service.SnippetBoxCalculator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class SnippetControllerTest {

    private MockMvc mockMvc;

    private SnippetController homeController;

    @Mock
    private IContentSearchService contentSearchService;

    @Mock
    private SnippetBoxCalculator snippetBoxCalculator;

    @Before
    public void setup() {
        homeController = new SnippetController(contentSearchService, snippetBoxCalculator);
        mockMvc = MockMvcBuilders.standaloneSetup(homeController).build();
    }

    @Test
    public void helloWorldTest() throws Exception{
        mockMvc.perform(get("/v1/catalog/snippet"))
                .andExpect(status().is4xxClientError());
    }

}
