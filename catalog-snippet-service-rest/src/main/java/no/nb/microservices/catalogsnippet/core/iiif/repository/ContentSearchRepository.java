package no.nb.microservices.catalogsnippet.core.iiif.repository;

import no.nb.microservices.catalogcontentsearch.rest.model.AnnotationList;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("catalog-content-search-service")
public interface ContentSearchRepository {

    @RequestMapping(method = RequestMethod.GET, value = "/catalog/contentsearch/{id}/search")
    AnnotationList search(@PathVariable("id") String id, @RequestParam("q") String q);

}