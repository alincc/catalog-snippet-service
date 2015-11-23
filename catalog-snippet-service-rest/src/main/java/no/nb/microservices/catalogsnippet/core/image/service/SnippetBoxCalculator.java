package no.nb.microservices.catalogsnippet.core.image.service;

import no.nb.microservices.catalogsnippet.core.catalog.contentsearch.model.PageInfo;
import no.nb.microservices.catalogsnippet.core.image.model.SnippetBox;
import no.nb.microservices.catalogsnippet.model.SnippetQuery;

import java.util.List;

public interface SnippetBoxCalculator {
    List<SnippetBox> findSnippetBoxes(List<PageInfo> pageInfos, SnippetQuery snippetQuery);
}