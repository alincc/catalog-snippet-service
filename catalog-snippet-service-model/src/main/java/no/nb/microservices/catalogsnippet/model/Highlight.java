package no.nb.microservices.catalogsnippet.model;

import java.util.List;

public class Highlight {
    private String sentence;
    private List<Dimension> dimension;

    public Highlight(String sentence, List<Dimension> dimension) {
        this.sentence = sentence;
        this.dimension = dimension;
    }

    public String getSentence() {
        return sentence;
    }

    public void setSentence(String sentence) {
        this.sentence = sentence;
    }

    public List<Dimension> getDimension() {
        return dimension;
    }

    public void setDimension(List<Dimension> dimension) {
        this.dimension = dimension;
    }
}
