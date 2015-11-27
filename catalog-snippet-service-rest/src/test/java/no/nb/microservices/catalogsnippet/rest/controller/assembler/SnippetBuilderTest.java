package no.nb.microservices.catalogsnippet.rest.controller.assembler;

import no.nb.microservices.catalogsnippet.core.image.model.SnippetBox;
import no.nb.microservices.catalogsnippet.model.Dimension;
import no.nb.microservices.catalogsnippet.model.Highlight;
import no.nb.microservices.catalogsnippet.model.Snippet;
import no.nb.microservices.catalogsnippet.rest.assembler.SnippetBuilder;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SnippetBuilderTest {

    @Test
    public void buildDefaultSnippetTest() {
        Dimension highlightDimension = new Dimension(409, 100, 138, 39);
        Highlight highlight = new Highlight("donald", highlightDimension);

        Dimension snippetDimension = new Dimension(0, 1300, 1458, 256);
        SnippetBox snippetBox = new SnippetBox("http://www.nb.no/services/image/resolver/URN:NBN:no-nb_digibok_2014020626009_0003", snippetDimension, highlight);

        Snippet snippet = new SnippetBuilder(snippetBox)
                .withItemId("URN:NBN:no-nb_digibok_2014020626009")
                .build();

        Dimension snippetOutputDim = snippet.getHighlights().get(0).getDimensions().get(0);
        assertEquals("URN:NBN:no-nb_digibok_2014020626009", snippet.getItemId());
        assertEquals("http://www.nb.no/services/image/resolver/URN:NBN:no-nb_digibok_2014020626009_0003", snippet.getPageId());
        assertEquals(409, snippetOutputDim.getX());
        assertEquals(100, snippetOutputDim.getY());
        assertEquals(138, snippetOutputDim.getW());
        assertEquals(39, snippetOutputDim.getH());
        assertEquals("http://www.nb.no/services/image/resolver/URN:NBN:no-nb_digibok_2014020626009_0003/0,1300,1458,256/full/0/native.jpg", snippet.getImageUrl());
    }
}
