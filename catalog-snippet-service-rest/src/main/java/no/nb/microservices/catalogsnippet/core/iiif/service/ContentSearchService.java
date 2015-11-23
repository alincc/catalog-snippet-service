package no.nb.microservices.catalogsnippet.core.iiif.service;

import no.nb.microservices.catalogcontentsearch.rest.model.Annotation;
import no.nb.microservices.catalogcontentsearch.rest.model.AnnotationList;
import no.nb.microservices.catalogsnippet.core.iiif.model.PageInfo;
import no.nb.microservices.catalogsnippet.core.iiif.repository.ContentSearchRepository;
import no.nb.microservices.catalogsnippet.model.Dimension;
import no.nb.microservices.catalogsnippet.rest.assembler.PageInfoBuilder;
import no.nb.microservices.iiifpresentation.model.Canvas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ContentSearchService implements IContentSearchService {
    private final ContentSearchRepository contentSearchRepository;
    private final RestTemplate restTemplate;

    @Autowired
    public ContentSearchService(ContentSearchRepository contentSearchRepository, RestTemplate restTemplate) {
        this.contentSearchRepository = contentSearchRepository;
        this.restTemplate = new RestTemplate();
    }

    @Override
    public List<PageInfo> findQueryOccurrences(String id, String query) {
        List<PageInfo> result = new ArrayList<>();
        AnnotationList search = contentSearchRepository.search(id, query);
        if (search.getResources() == null) {
            return result;
        }
        String currentPage = "";
        for (Annotation annotation : search.getResources()) {
            PageInfo pageInfo = extractPageInfo(annotation, query);
            if (!pageInfo.getPageId().equalsIgnoreCase(currentPage)) {
                result.add(pageInfo);
                currentPage = pageInfo.getPageId();
            } else {
                PageInfo last = result.get(result.size() - 1);
                last.getHighlightInfos().addAll(pageInfo.getHighlightInfos());
            }
        }
        return result;
    }

    private PageInfo extractPageInfo(Annotation annotation, String query) {
        String[] info = last(annotation.getOn().split("/")).split("\\#");
        String[] dimensions = info[1].split(",");
        String pageId = info[0];
        Dimension dimension = new Dimension(Integer.parseInt(dimensions[0]),Integer.parseInt(dimensions[1]),
                Integer.parseInt(dimensions[2]),Integer.parseInt(dimensions[3]));
        ResponseEntity<Canvas> entity = restTemplate.getForEntity(annotation.getOn(), Canvas.class);
        int height = entity.getBody().getImages().get(0).getResource().getHeight();
        int width = entity.getBody().getImages().get(0).getResource().getWidth();

        PageInfo pageInfo = new PageInfoBuilder()
                .withPageId(pageId)
                .withDimension(width, height)
                .withHighlight(query,Arrays.asList(dimension)).build();

        return pageInfo;
    }

    private <T> T last(T[] array) {
        return array[array.length - 1];
    }
}
