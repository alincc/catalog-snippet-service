package no.nb.microservices.catalogsnippet.core.image.service;

import no.nb.microservices.catalogsnippet.core.catalog.contentsearch.model.HighlightInfo;
import no.nb.microservices.catalogsnippet.core.catalog.contentsearch.model.PageInfo;
import no.nb.microservices.catalogsnippet.core.image.model.SnippetBox;
import no.nb.microservices.catalogsnippet.model.Dimension;
import no.nb.microservices.catalogsnippet.model.Highlight;

import java.util.ArrayList;
import java.util.List;

public class SimpleSnippetBoxCalculator implements SnippetBoxCalculator {
    @Override
    public List<SnippetBox> findSnippetBoxes(PageInfo pageInfo, int snippetLines) {
        List<SnippetBox> snippetBoxes = new ArrayList<>();

        for (HighlightInfo highlight : pageInfo.getHighlightInfos()) {
            Dimension dim = highlight.getDimensions().get(0);
            double sH = ((dim.getH()*snippetLines)*1.2);
            double sY = ((dim.getY()+(dim.getH()/2))-(sH/2));
            Dimension sDimension = new Dimension(0, (int)sY, pageInfo.getWidth(), (int)sH);

            int lastSnippetBoxIndex = (snippetBoxes.size() - 1);
            if (snippetBoxes.size() > 0 && sDimension.overlapWith(snippetBoxes.get(lastSnippetBoxIndex).getDimension())) { // Highlight overlaps with previous snippet.
                Dimension preDim = snippetBoxes.get(lastSnippetBoxIndex).getDimension();
                Dimension dimension = new Dimension(dim.getX() - preDim.getX(), dim.getY() - preDim.getY(), dim.getW(), dim.getH());
                Highlight snippetHighlight = new Highlight(highlight.getSentence(), dimension);
                snippetBoxes.get(lastSnippetBoxIndex).getHighlights().add(snippetHighlight);
            }
            else {
                Dimension dimension = new Dimension(dim.getX() - sDimension.getX(), dim.getY() - sDimension.getY(), dim.getW(), dim.getH());
                Highlight snippetHighlight = new Highlight(highlight.getSentence(), dimension);
                SnippetBox snippetBox = new SnippetBox(pageInfo.getPageId(), sDimension, snippetHighlight);
                snippetBoxes.add(snippetBox);
            }
        }

       return snippetBoxes;
    }
}
