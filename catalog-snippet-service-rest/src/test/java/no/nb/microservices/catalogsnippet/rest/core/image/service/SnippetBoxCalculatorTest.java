package no.nb.microservices.catalogsnippet.rest.core.image.service;

import no.nb.microservices.catalogsnippet.core.catalog.contentsearch.model.PageInfo;
import no.nb.microservices.catalogsnippet.core.image.model.SnippetBox;
import no.nb.microservices.catalogsnippet.core.image.service.SimpleSnippetBoxCalculator;
import no.nb.microservices.catalogsnippet.core.image.service.SnippetBoxCalculator;
import no.nb.microservices.catalogsnippet.rest.assembler.PageInfoBuilder;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class SnippetBoxCalculatorTest {

    @Test
    public void findSnippetBoxDefaultTest() {
        SnippetBoxCalculator calculator = new SimpleSnippetBoxCalculator();

        PageInfo pageInfo = new PageInfoBuilder()
                .withPageId("URN:NBN:no-nb_digibok_2014020626009_0003")
                .withDimension(1458, 1969)
                .withSentence("donald")
                .withHighlight(409, 1608, 138, 39)
                .withHighlight(1147, 1670, 148, 37)
                .build();

        List<SnippetBox> snippetBoxes = calculator.findSnippetBoxes(pageInfo);

        assertEquals(1, snippetBoxes.size());
        SnippetBox snippetBox = snippetBoxes.get(0);

        assertEquals(2, snippetBox.getHighlights());
    }
}
