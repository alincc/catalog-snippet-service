package no.nb.microservices.catalogsnippet.core.image.service;

import no.nb.microservices.catalogsnippet.core.catalog.contentsearch.model.PageInfo;
import no.nb.microservices.catalogsnippet.core.image.model.SnippetBox;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.List;

public class SimpleSnippetBoxCalculator implements SnippetBoxCalculator {
    @Override
    public List<SnippetBox> findSnippetBoxes(PageInfo pageInfo) {

        // Gå igjennom alle highlights på en side
        // Ta en highlight og generer en snippet boks
        // Finn andre highlights inne i boksen og fjern dem fra loop listen

//        for (HighlightInfo highlight : pageInfo.getHighlightInfos()) {
//            highlight.
//        }

        throw new NotImplementedException();
    }
}
