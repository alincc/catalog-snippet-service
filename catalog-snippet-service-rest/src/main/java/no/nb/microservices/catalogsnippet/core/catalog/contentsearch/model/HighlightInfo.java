package no.nb.microservices.catalogsnippet.core.catalog.contentsearch.model;

import no.nb.microservices.catalogsnippet.model.Dimension;

import java.util.List;

public class HighlightInfo {
    private String sentence;
    private List<Dimension> dimensions;

    public String getSentence() {
        return sentence;
    }

    public void setSentence(String sentence) {
        this.sentence = sentence;
    }

    public List<Dimension> getDimensions() {
        return dimensions;
    }

    public void setDimensions(List<Dimension> dimensions) {
        this.dimensions = dimensions;
    }
}
