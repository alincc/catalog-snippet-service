package no.nb.microservices.catalogsnippet.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Highlight {
    private String sentence;
    private List<Dimension> dimensions = new ArrayList<>();


    public Highlight(String sentence, Dimension dimension) {
        this.sentence = sentence;
        this.dimensions.add(dimension);
    }

    public Highlight(String sentence, List<Dimension> dimensions) {
        this.sentence = sentence;
        this.dimensions = dimensions;
    }

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
