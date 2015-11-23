package no.nb.microservices.catalogsnippet.rest.core.image.service;

import no.nb.microservices.catalogsnippet.core.iiif.model.PageInfo;
import no.nb.microservices.catalogsnippet.core.image.model.SnippetBox;
import no.nb.microservices.catalogsnippet.core.image.service.SimpleSnippetBoxCalculator;
import no.nb.microservices.catalogsnippet.core.image.service.SnippetBoxCalculator;
import no.nb.microservices.catalogsnippet.model.Highlight;
import no.nb.microservices.catalogsnippet.model.SnippetQuery;
import no.nb.microservices.catalogsnippet.rest.assembler.PageInfoBuilder;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class SnippetBoxCalculatorTest {

    @Test
    public void snippetBoxDefaultTest() {
        SnippetQuery snippetQuery = new SnippetQuery("URN:NBN:no-nb_digibok_2014020626009", "donald", 10, 10, 3);

        PageInfo pageInfo = new PageInfoBuilder()
                .withPageId("URN:NBN:no-nb_digibok_2014020626009_0003")
                .withDimension(1458, 1969)
                .withSentence("donald")
                .withHighlight(409, 1608, 138, 39)
                .withHighlight(1147, 1670, 148, 37)
                .build();

        int expectedX = 0;
        int expectedY = 1556; // ((1608+(39/2))-(((39*snippetQuery.getLines())*1.2)/2))
        int expectedWidth = 1458;
        int expectedHeight = 140; // (39*snippetQuery.getLines())*1.2

        SnippetBoxCalculator calculator = new SimpleSnippetBoxCalculator();
        List<SnippetBox> snippetBoxes = calculator.findSnippetBoxes(Arrays.asList(pageInfo), snippetQuery);

        assertEquals(1, snippetBoxes.size());
        SnippetBox snippetBox = snippetBoxes.get(0);

        assertEquals(expectedX, snippetBox.getDimension().getX());
        assertEquals(expectedY, snippetBox.getDimension().getY());
        assertEquals(expectedWidth, snippetBox.getDimension().getW());
        assertEquals(expectedHeight, snippetBox.getDimension().getH());
    }

    @Test
    public void multipleSnippetBoxesTest() {
        SnippetQuery snippetQuery = new SnippetQuery("URN:NBN:no-nb_digibok_2014020626009", "donald", 10, 10, 3);

        SnippetBoxCalculator calculator = new SimpleSnippetBoxCalculator();

        PageInfo pageInfo1 = new PageInfoBuilder()
                .withPageId("URN:NBN:no-nb_digibok_2014020626009_0003")
                .withDimension(1458, 1969)
                .withSentence("donald")
                .withHighlight(409, 1608, 138, 39)
                .withHighlight(1147, 1670, 148, 37)
                .build();

        PageInfo pageInfo2 = new PageInfoBuilder()
                .withPageId("URN:NBN:no-nb_digibok_2014020626009_0004")
                .withDimension(1458, 1969)
                .withSentence("donald")
                .withHighlight(309, 1508, 138, 39)
                .build();

        List<SnippetBox> snippetBoxes = calculator.findSnippetBoxes(Arrays.asList(pageInfo1, pageInfo2), snippetQuery);

        assertEquals(2, snippetBoxes.size());
    }

    @Test
    public void highlightDefaultTest() {
        SnippetQuery snippetQuery = new SnippetQuery("URN:NBN:no-nb_digibok_2014020626009", "donald", 10, 10, 3);

        SnippetBoxCalculator calculator = new SimpleSnippetBoxCalculator();

        PageInfo pageInfo = new PageInfoBuilder()
                .withPageId("URN:NBN:no-nb_digibok_2014020626009_0003")
                .withDimension(1458, 1969)
                .withSentence("donald")
                .withHighlight(409, 1608, 138, 39)
                .withHighlight(1147, 1670, 148, 37)
                .build();

        List<SnippetBox> snippetBoxes = calculator.findSnippetBoxes(Arrays.asList(pageInfo), snippetQuery);

        SnippetBox snippetBox = snippetBoxes.get(0);

        assertEquals(2, snippetBox.getHighlights().size());
        Highlight highlight = snippetBox.getHighlights().get(0);
        assertEquals(138, highlight.getDimensions().get(0).getW());
        assertEquals(39, highlight.getDimensions().get(0).getH());
        assertEquals(409, highlight.getDimensions().get(0).getX());
        assertEquals((1608-(int)((1608+(39/2))-(((39*snippetQuery.getLines())*1.2)/2))), highlight.getDimensions().get(0).getY());
    }
}
