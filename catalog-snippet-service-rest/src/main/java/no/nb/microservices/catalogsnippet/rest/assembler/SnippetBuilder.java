package no.nb.microservices.catalogsnippet.rest.assembler;

import no.nb.microservices.catalogsnippet.core.image.model.SnippetBox;
import no.nb.microservices.catalogsnippet.model.Dimension;
import no.nb.microservices.catalogsnippet.model.Snippet;
import org.springframework.web.util.UriComponentsBuilder;

public class SnippetBuilder {
    private Snippet snippet;
    private String iifImageUrl;
    private Dimension sDim;

    public SnippetBuilder(SnippetBox snippetBox) {
        this.snippet = new Snippet();

        this.sDim = snippetBox.getDimension();
        this.snippet.setPageId(snippetBox.getPageId());
        this.snippet.setHighlights(snippetBox.getHighlights());
    }

    public SnippetBuilder withItemId(String itemId) {
        snippet.setItemId(itemId);
        return this;
    }

    public SnippetBuilder withIIIFImageRootUrl(String iifImageUrl) {
        this.iifImageUrl = iifImageUrl;
        return this;
    }

    public Snippet build() {
        String urlString = UriComponentsBuilder.fromUriString(iifImageUrl)
                .path(snippet.getPageId() + "/")
                .path(sDim.getX() + "," + sDim.getY() + "," + sDim.getW() + "," + sDim.getH() + "/")
                .path("full"  + "/")
                .path("0" + "/") // Rotation
                .path("native.jpg")
                .build()
                .toUriString();

        snippet.setImageUrl(urlString);
        return snippet;
    }
}
