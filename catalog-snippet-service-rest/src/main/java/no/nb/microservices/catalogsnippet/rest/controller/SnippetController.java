package no.nb.microservices.catalogsnippet.rest.controller;

import no.nb.microservices.catalogsnippet.core.iiif.model.PageInfo;
import no.nb.microservices.catalogsnippet.core.iiif.service.IContentSearchService;
import no.nb.microservices.catalogsnippet.core.image.model.SnippetBox;
import no.nb.microservices.catalogsnippet.core.image.service.SnippetBoxCalculator;
import no.nb.microservices.catalogsnippet.model.Snippet;
import no.nb.microservices.catalogsnippet.model.SnippetQuery;
import no.nb.microservices.catalogsnippet.rest.assembler.SnippetBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class SnippetController {

    private final IContentSearchService contentSearchService;
    private final SnippetBoxCalculator snippetBoxCalculator;
    private final ApplicationSettings settings;

    @Autowired
    public SnippetController(IContentSearchService contentSearchService, SnippetBoxCalculator snippetBoxCalculator) {
        this.contentSearchService = contentSearchService;
        this.snippetBoxCalculator = snippetBoxCalculator;
        this.settings = settings;
    }

    @RequestMapping(value = "/snippet", method = RequestMethod.GET)
    public ResponseEntity<List<Snippet>> generateSnippets(@Valid SnippetQuery snippetQuery) {
        List<Snippet> snippets = new ArrayList<>();
        List<PageInfo> queryOccurrences = contentSearchService.findQueryOccurrences(snippetQuery.getId(), snippetQuery.getQuery());

        snippets.addAll(snippetBoxCalculator.findSnippetBoxes(queryOccurrences, snippetQuery).stream()
                .map(snippetBox -> new SnippetBuilder(snippetBox)
                .withItemId(snippetQuery.getId())
                .withIIIFImageRootUrl(settings.getIiifImageRootUrl())
                .build()).collect(Collectors.toList()));

        return new ResponseEntity<>(snippets, HttpStatus.OK);
    }
}
