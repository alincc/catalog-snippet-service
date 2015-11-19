package no.nb.microservices.catalogsnippet.core.catalog.contentsearch.service;

import no.nb.microservices.catalogsnippet.core.catalog.contentsearch.model.PageInfo;

import java.util.List;

public interface ContentSearchService {
    List<PageInfo> findQueryOccurrences(String id, String query);
}
