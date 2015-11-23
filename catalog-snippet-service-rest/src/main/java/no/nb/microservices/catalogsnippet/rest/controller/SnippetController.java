package no.nb.microservices.catalogsnippet.rest.controller;

import no.nb.microservices.catalogsnippet.core.catalog.contentsearch.model.PageInfo;
import no.nb.microservices.catalogsnippet.core.catalog.contentsearch.service.ContentSearchService;
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

@RestController
@RequestMapping("/catalog")
public class SnippetController {

    private final ContentSearchService contentSearchService;
    private final SnippetBoxCalculator snippetBoxCalculator;

    @Autowired
    public SnippetController(ContentSearchService contentSearchService, SnippetBoxCalculator snippetBoxCalculator) {
        this.contentSearchService = contentSearchService;
        this.snippetBoxCalculator = snippetBoxCalculator;
    }

    private static final Logger LOG = LoggerFactory.getLogger(SnippetController.class);

    @RequestMapping(value = "/snippet", method = RequestMethod.GET)
    public ResponseEntity<List<Snippet>> generateSnippets(@Valid SnippetQuery snippetQuery) {
        List<PageInfo> queryOccurrences = contentSearchService.findQueryOccurrences(snippetQuery.getId(), snippetQuery.getQuery());

        List<Snippet> snippets = new ArrayList<>();
        List<SnippetBox> snippetBoxes = new ArrayList<>();

        for (PageInfo pageInfo : queryOccurrences) {
            snippetBoxes.addAll(snippetBoxCalculator.findSnippetBoxes(pageInfo, snippetQuery.getLines()));
        }

        for (SnippetBox snippetBox : snippetBoxes) {
            snippets.add(new SnippetBuilder(snippetBox)
                    .withItemId(snippetQuery.getId())
                    .build());
        }

        return new ResponseEntity<>(snippets, HttpStatus.OK);
    }
}
