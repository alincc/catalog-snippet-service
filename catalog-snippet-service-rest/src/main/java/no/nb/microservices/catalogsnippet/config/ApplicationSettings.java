package no.nb.microservices.catalogsnippet.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "snippet")
public class ApplicationSettings {

}
