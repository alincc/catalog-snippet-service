package no.nb.microservices.catalogsnippet.rest.controller;

import no.nb.microservices.catalogsnippet.model.Snippet;
import no.nb.microservices.catalogsnippet.model.SnippetQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/catalog")
public class SnippetController {

    private static final Logger LOG = LoggerFactory.getLogger(SnippetController.class);

    @RequestMapping(value = "/snippet", method = RequestMethod.GET)
    public ResponseEntity<List<Snippet>> generateSnippets(@Valid SnippetQuery snippetQuery) {
        return new ResponseEntity<List<Snippet>>(HttpStatus.OK);
    }
}
