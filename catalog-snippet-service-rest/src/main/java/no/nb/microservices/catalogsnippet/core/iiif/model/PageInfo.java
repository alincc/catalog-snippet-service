package no.nb.microservices.catalogsnippet.core.iiif.model;

import java.util.ArrayList;
import java.util.List;

public class PageInfo {
    private String pageId;
    private int width;
    private int height;
    private List<HighlightInfo> highlightInfos = new ArrayList<>();

    public String getPageId() {
        return pageId;
    }

    public void setPageId(String pageId) {
        this.pageId = pageId;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public List<HighlightInfo> getHighlightInfos() {
        return highlightInfos;
    }

    public void setHighlightInfos(List<HighlightInfo> highlightInfos) {
        this.highlightInfos = highlightInfos;
    }

    public void addHighlightInfo(HighlightInfo highlightInfo) {
        highlightInfos.add(highlightInfo);
    }
}
