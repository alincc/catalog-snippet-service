package no.nb.microservices.catalogsnippet.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "snippet")
public class ApplicationSettings {

    private String iiifImageRootUrl;

    public String getIiifImageRootUrl() {
        return iiifImageRootUrl;
    }

    public void setIiifImageRootUrl(String iiifImageRootUrl) {
        this.iiifImageRootUrl = iiifImageRootUrl;
    }
}
