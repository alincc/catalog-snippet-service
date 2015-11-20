package no.nb.microservices.catalogsnippet.rest.assembler;

import no.nb.microservices.catalogsnippet.core.catalog.contentsearch.model.HighlightInfo;
import no.nb.microservices.catalogsnippet.core.catalog.contentsearch.model.PageInfo;
import no.nb.microservices.catalogsnippet.model.Dimension;

import java.util.Arrays;
import java.util.List;

public class PageInfoBuilder {

    private PageInfo pageInfo;
    private String sentence;

    public PageInfoBuilder() {
        pageInfo = new PageInfo();
    }

    public PageInfoBuilder withPageId(String pageId) {
        pageInfo.setPageId(pageId);
        return this;
    }

    public PageInfoBuilder withDimension(int width, int height) {
        pageInfo.setWidth(width);
        pageInfo.setHeight(height);
        return this;
    }

    public PageInfoBuilder withSentence(String sentence) {
        this.sentence = sentence;
        return this;
    }

    public PageInfoBuilder withHighlight(int x, int y, int w, int h) {
        Dimension dimension = new Dimension(x, y, w, h);
        HighlightInfo highlight = new HighlightInfo();
        highlight.setDimensions(Arrays.asList(dimension));
        highlight.setSentence(this.sentence);
        pageInfo.addHighlightInfo(highlight);

        return this;
    }

    public PageInfoBuilder withHighlight(String sentence, int x, int y, int w, int h) {
        Dimension dimension = new Dimension(x, y, w, h);
        HighlightInfo highlight = new HighlightInfo();
        highlight.setDimensions(Arrays.asList(dimension));
        highlight.setSentence(sentence);
        pageInfo.addHighlightInfo(highlight);

        return this;
    }

    public PageInfoBuilder withHighlight(String sentence, List<Dimension> dimensions) {
        HighlightInfo highlight = new HighlightInfo();
        highlight.setDimensions(dimensions);
        highlight.setSentence(sentence);
        pageInfo.addHighlightInfo(highlight);
        return this;
    }

    public PageInfo build() {
        return pageInfo;
    }
}
