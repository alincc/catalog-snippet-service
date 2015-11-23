package no.nb.microservices.catalogsnippet.core.image.model;

import no.nb.microservices.catalogsnippet.model.Dimension;
import no.nb.microservices.catalogsnippet.model.Highlight;

import java.util.ArrayList;
import java.util.List;

public class SnippetBox {
    private String pageId;
    private Dimension dimension;
    private List<Highlight> highlights = new ArrayList<>();

    public SnippetBox(String pageId, Dimension dimension, Highlight highlight) {
        this.pageId = pageId;
        this.dimension = dimension;
        this.highlights.add(highlight);
    }

    public SnippetBox(Dimension dimension, List<Highlight> highlights) {
        this.dimension = dimension;
        this.highlights = highlights;
    }

    public SnippetBox(String pageId, Dimension dimension, List<Highlight> highlights) {
        this.pageId = pageId;
        this.dimension = dimension;
        this.highlights = highlights;
    }

    public Dimension getDimension() {
        return dimension;
    }

    public void setDimension(Dimension dimension) {
        this.dimension = dimension;
    }

    public List<Highlight> getHighlights() {
        return highlights;
    }

    public void setHighlights(List<Highlight> highlights) {
        this.highlights = highlights;
    }

    public String getPageId() {
        return pageId;
    }

    public void setPageId(String pageId) {
        this.pageId = pageId;
    }
}
