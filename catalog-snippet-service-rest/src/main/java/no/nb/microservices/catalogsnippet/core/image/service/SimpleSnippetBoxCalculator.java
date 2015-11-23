package no.nb.microservices.catalogsnippet.core.image.service;

import no.nb.microservices.catalogsnippet.core.iiif.model.HighlightInfo;
import no.nb.microservices.catalogsnippet.core.iiif.model.PageInfo;
import no.nb.microservices.catalogsnippet.core.image.model.SnippetBox;
import no.nb.microservices.catalogsnippet.model.Dimension;
import no.nb.microservices.catalogsnippet.model.Highlight;
import no.nb.microservices.catalogsnippet.model.SnippetQuery;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SimpleSnippetBoxCalculator implements SnippetBoxCalculator {
    @Override
    public List<SnippetBox> findSnippetBoxes(List<PageInfo> pageInfos, SnippetQuery snippetQuery) {
        List<SnippetBox> snippetBoxes = new ArrayList<>();

        for (PageInfo pageInfo : pageInfos) {
            int addedInPage = 0;
            for (HighlightInfo highlight : pageInfo.getHighlightInfos()) {
                if (snippetBoxes.size() >= snippetQuery.getMaxSize()) {
                    return snippetBoxes;
                }

                Dimension dim = highlight.getDimensions().get(0);
                double sH = ((dim.getH()*snippetQuery.getLines())*1.2);
                double sY = ((dim.getY()+(dim.getH()/2))-(sH/2));
                Dimension sDimension = new Dimension(0, (int)sY, pageInfo.getWidth(), (int)sH);

                int lastSnippetBoxIndex = (snippetBoxes.size() - 1);
                if (snippetBoxes.size() > 0 && addedInPage != 0 && sDimension.overlapWith(snippetBoxes.get(lastSnippetBoxIndex).getDimension())) { // Highlight overlaps with previous snippet.
                    Dimension preDim = snippetBoxes.get(lastSnippetBoxIndex).getDimension();
                    Dimension dimension = new Dimension(dim.getX() - preDim.getX(), dim.getY() - preDim.getY(), dim.getW(), dim.getH());
                    Highlight snippetHighlight = new Highlight(highlight.getSentence(), dimension);
                    snippetBoxes.get(lastSnippetBoxIndex).getHighlights().add(snippetHighlight);
                }
                else {
                    if (addedInPage < snippetQuery.getMaxPerPage()) {
                        Dimension dimension = new Dimension(dim.getX() - sDimension.getX(), dim.getY() - sDimension.getY(), dim.getW(), dim.getH());
                        Highlight snippetHighlight = new Highlight(highlight.getSentence(), dimension);
                        SnippetBox snippetBox = new SnippetBox(pageInfo.getPageId(), sDimension, snippetHighlight);
                        snippetBoxes.add(snippetBox);
                        addedInPage++;
                    }
                }
            }
        }

       return snippetBoxes;
    }
}
