package no.nb.microservices.catalogsnippet.core.iiif.service;

import no.nb.microservices.catalogsnippet.core.iiif.model.PageInfo;

import java.util.List;

public interface IContentSearchService {
    List<PageInfo> findQueryOccurrences(String id, String query);
}
