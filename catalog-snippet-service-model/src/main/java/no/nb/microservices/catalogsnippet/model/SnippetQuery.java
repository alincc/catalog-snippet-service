package no.nb.microservices.catalogsnippet.model;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

public class SnippetQuery {

    @NotEmpty
    private String id;

    @NotEmpty
    @Length(max = 128)
    private String query;

    @Max(64)
    @Min(1)
    private int maxSize = 3;

    @Max(64)
    @Min(1)
    private int maxPerPage = 1;

    @Max(64)
    @Min(1)
    private int lines = 3;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public int getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }

    public int getMaxPerPage() {
        return maxPerPage;
    }

    public void setMaxPerPage(int maxPerPage) {
        this.maxPerPage = maxPerPage;
    }

    public int getLines() {
        return lines;
    }

    public void setLines(int lines) {
        this.lines = lines;
    }
}
