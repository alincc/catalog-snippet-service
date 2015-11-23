package no.nb.microservices.catalogsnippet.rest.core.iiif.service;

import no.nb.microservices.catalogcontentsearch.rest.model.Annotation;
import no.nb.microservices.catalogcontentsearch.rest.model.AnnotationList;
import no.nb.microservices.catalogsnippet.core.iiif.model.PageInfo;
import no.nb.microservices.catalogsnippet.core.iiif.repository.ContentSearchRepository;
import no.nb.microservices.catalogsnippet.core.iiif.service.ContentSearchService;
import no.nb.microservices.iiifpresentation.model.Canvas;
import no.nb.microservices.iiifpresentation.model.NullContext;
import no.nb.microservices.iiifpresentation.model.Resource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ContentSearchServiceTest {

    @InjectMocks
    private ContentSearchService contentSearchService;

    @Mock
    private ContentSearchRepository contentSearchRepository;

    @Mock
    private RestTemplate restTemplate;


    @Test
    public void whenTextFoundResultShouldNotBeEmpty() {
        Annotation a1 = new Annotation();
        a1.setOn("http://snoppen.nb.no:8765/catalog/iiif/0b8501b8e2b822c8ec13558de82aaef9/canvas/DIV3#909,493,1346,592");
        Annotation a2 = new Annotation();
        a2.setOn("http://snoppen.nb.no:8765/catalog/iiif/0b8501b8e2b822c8ec13558de82aaef9/canvas/DIV3#400,1657,558,1717");
        Annotation a3 = new Annotation();
        a3.setOn("http://snoppen.nb.no:8765/catalog/iiif/0b8501b8e2b822c8ec13558de82aaef9/canvas/DIV17#2198,187,2378,247");
        AnnotationList al = new AnnotationList();
        al.setResources(Arrays.asList(a1,a2,a3));

        no.nb.microservices.iiifpresentation.model.Annotation a4 = new no.nb.microservices.iiifpresentation.model.Annotation(new NullContext(),"1234","http://hej",new Resource("1",1234,321,null));
        Canvas canvas = new Canvas(new NullContext(),"1234","label",1,2,Arrays.asList(a4));
        ResponseEntity<Canvas> entity = new ResponseEntity<>(canvas, HttpStatus.OK);
        when(contentSearchRepository.search("1234","kaffe")).thenReturn(al);
        when(restTemplate.getForEntity(anyString(),eq(Canvas.class),anyString(), anyString())).thenReturn(entity);

        List<PageInfo> infos = contentSearchService.findQueryOccurrences("1234", "kaffe");
        assertEquals("Should contain 2 pageinfos", 2, infos.size());
    }

    @Test
    public void whenTextIsNotFoundResultShouldBeEmpty() {
        when(contentSearchRepository.search("1234","lego")).thenReturn(new AnnotationList());
        List<PageInfo> infos = contentSearchService.findQueryOccurrences("1234", "lego");
        assertTrue("Result should be empty", infos.isEmpty());
    }
}
