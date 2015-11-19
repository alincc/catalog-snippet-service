package no.nb.microservices.catalogsnippet.core.catalog.contentsearch.model;

import java.util.List;

public class PageInfo {
    private String pageId;
    private List<HighlightInfo> hightlights;

    public String getPageId() {
        return pageId;
    }

    public void setPageId(String pageId) {
        this.pageId = pageId;
    }

    public List<HighlightInfo> getHightlights() {
        return hightlights;
    }

    public void setHightlights(List<HighlightInfo> hightlights) {
        this.hightlights = hightlights;
    }
}
