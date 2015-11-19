package no.nb.microservices.catalogsnippet.core.catalog.contentsearch.repository;

import no.nb.microservices.catalogcontentsearch.rest.model.AnnotationList;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("catalog-iiif-content-search")
public interface ContentSearchRepository {

    @RequestMapping(value = "/catalog/contentsearch/{id}/search")
    AnnotationList search(@PathVariable String id, @RequestParam String q);

}
