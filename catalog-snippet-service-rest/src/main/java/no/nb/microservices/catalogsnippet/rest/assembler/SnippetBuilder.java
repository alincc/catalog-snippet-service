package no.nb.microservices.catalogsnippet.rest.assembler;

import no.nb.microservices.catalogsnippet.core.image.model.SnippetBox;
import no.nb.microservices.catalogsnippet.model.Snippet;

public class SnippetBuilder {
    private Snippet snippet;

    public SnippetBuilder(SnippetBox snippetBox) {
        this.snippet = new Snippet();
        this.snippet.setPageId(snippetBox.getPageId());
        this.snippet.setHighlights(snippetBox.getHighlights());
    }

    public SnippetBuilder withItemId(String itemId) {
        snippet.setItemId(itemId);
        return this;
    }

    public Snippet build() {
        // TODO: Set image url
        return snippet;
    }
}
