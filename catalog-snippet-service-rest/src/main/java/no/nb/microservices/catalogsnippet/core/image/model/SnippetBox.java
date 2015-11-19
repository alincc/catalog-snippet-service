package no.nb.microservices.catalogsnippet.core.image.model;

import no.nb.microservices.catalogsnippet.model.Dimension;
import no.nb.microservices.catalogsnippet.model.Highlight;

import java.util.List;

public class SnippetBox {
    private Dimension dimension;
    private List<Highlight> highlights;

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
}
