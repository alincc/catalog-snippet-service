package no.nb.microservices.catalogsnippet.core.image.service;

import no.nb.microservices.catalogsnippet.core.catalog.contentsearch.model.PageInfo;
import no.nb.microservices.catalogsnippet.core.image.model.SnippetBox;

import java.util.List;

public interface SnippetBoxCalculator {
    List<SnippetBox> findSnippetBoxes(PageInfo pageInfo);
}