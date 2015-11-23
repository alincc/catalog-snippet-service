package no.nb.microservices.catalogsnippet.it;

import com.netflix.loadbalancer.BaseLoadBalancer;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.Server;
import com.squareup.okhttp.mockwebserver.Dispatcher;
import com.squareup.okhttp.mockwebserver.MockResponse;
import com.squareup.okhttp.mockwebserver.MockWebServer;
import com.squareup.okhttp.mockwebserver.RecordedRequest;
import no.nb.commons.web.util.UserUtils;
import no.nb.microservices.catalogsnippet.Application;
import no.nb.microservices.catalogsnippet.model.Snippet;
import org.apache.commons.io.IOUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;

import java.net.URISyntaxException;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {Application.class, RibbonClientConfiguration.class})
@WebIntegrationTest("server.port: 0")
public class SnippetControllerIT {

    @Value("${local.server.port}")
    int port;

    @Autowired
    ILoadBalancer loadBalancer;

    @Autowired
    WebApplicationContext context;

    MockWebServer mockWebServer;
    RestTemplate rest;

    @Before
    public void setup() throws Exception {
        rest = new TestRestTemplate();
        mockWebServer = new MockWebServer();

        // Read mock data
        String contentSearch1 = IOUtils.toString(new ClassPathResource("contentsearch-1.json").getInputStream());
        String presentation1 = IOUtils.toString(new ClassPathResource("presentation-1.json").getInputStream());
        String presentation2 = IOUtils.toString(new ClassPathResource("presentation-2.json").getInputStream());

        final Dispatcher dispatcher = new Dispatcher() {
            @Override
            public MockResponse dispatch(RecordedRequest request) throws InterruptedException {
                if (request.getPath().contains("/catalog/contentsearch/0b8501b8e2b822c8ec13558de82aaef9/search?q=publikum")) {
                    return new MockResponse().setBody(contentSearch1).setHeader("Content-Type", "application/hal+json; charset=utf-8");
                }
                else if (request.getPath().contains("/catalog/iiif/0b8501b8e2b822c8ec13558de82aaef9/canvas/DIV3")) {
                    return new MockResponse().setBody(presentation1).setHeader("Content-Type", "application/hal+json; charset=utf-8");
                }
                else if (request.getPath().contains("/catalog/iiif/0b8501b8e2b822c8ec13558de82aaef9/canvas/DIV17")) {
                    return new MockResponse().setBody(presentation2).setHeader("Content-Type", "application/hal+json; charset=utf-8");
                } else {
                    return new MockResponse().setResponseCode(404);
                }
            }
        };
        mockWebServer.setDispatcher(dispatcher);
        mockWebServer.start();

        BaseLoadBalancer baseLoadBalancer = (BaseLoadBalancer) loadBalancer;
        baseLoadBalancer.setServersList(Arrays.asList(new Server(mockWebServer.getHostName(), mockWebServer.getPort())));
    }

    @After
    public void tearDown() throws Exception {
        mockWebServer.shutdown();
    }

    @Test
    public void getDefaultSnippets() throws URISyntaxException {
//        URI uri = new URI("http://localhost:" + port + "/catalog/snippet?id=0b8501b8e2b822c8ec13558de82aaef9&query=publikum");
//        ResponseEntity<String> response = rest.getForEntity(uri, String.class);

        HttpHeaders headers = defaultHeaders();

        ResponseEntity<Snippet[]> entity = new TestRestTemplate().exchange(
                "http://localhost:" + port + "/catalog/snippet?id=0b8501b8e2b822c8ec13558de82aaef9&query=publikum",
                HttpMethod.GET, new HttpEntity<Void>(headers), Snippet[].class);

        assertEquals(HttpStatus.OK, entity.getStatusCode());
    }

    private HttpHeaders defaultHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.add(UserUtils.SSO_HEADER, "token");
        headers.add(UserUtils.REAL_IP_HEADER, "123.45.100.1");
        return headers;
    }
}

@Configuration
class RibbonClientConfiguration {
    @Bean
    public ILoadBalancer ribbonLoadBalancer() {
        return new BaseLoadBalancer();
    }
}