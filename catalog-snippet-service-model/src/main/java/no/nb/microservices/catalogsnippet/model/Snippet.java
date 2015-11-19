package no.nb.microservices.catalogsnippet.model;

import java.util.List;

public class Snippet {
    private String imageUrl;
    private List<Highlight> highlights;

    public Snippet(String imageUrl, List<Highlight> highlights) {
        this.imageUrl = imageUrl;
        this.highlights = highlights;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public List<Highlight> getHighlights() {
        return highlights;
    }

    public void setHighlights(List<Highlight> highlights) {
        this.highlights = highlights;
    }
}
