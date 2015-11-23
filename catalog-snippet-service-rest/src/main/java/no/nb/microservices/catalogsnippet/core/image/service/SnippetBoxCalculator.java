package no.nb.microservices.catalogsnippet.core.image.service;

import no.nb.microservices.catalogsnippet.core.iiif.model.PageInfo;
import no.nb.microservices.catalogsnippet.core.image.model.SnippetBox;

import java.util.List;

public interface SnippetBoxCalculator {
    List<SnippetBox> findSnippetBoxes(PageInfo pageInfo, int snippetLines);
}