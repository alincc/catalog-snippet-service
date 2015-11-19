package no.nb.microservices.catalogsnippet.core.catalog.contentsearch.service;

import no.nb.microservices.catalogsnippet.core.catalog.contentsearch.model.PageInfo;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.List;

public class SimpleContentSearchService implements ContentSearchService {
    @Override
    public List<PageInfo> findQueryOccurrences(String id, String query) {
        throw new NotImplementedException();
    }
}
