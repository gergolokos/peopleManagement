package lg.pplmanagement.api.cors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    private static final String BASE_PATH_PATTERN = "/api/v1/**";

    @Value("${cors.enabled.domain}")
    private String corsEnabledDomain;

    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping(BASE_PATH_PATTERN)
                .allowedOrigins(corsEnabledDomain)
                .allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH")
                .allowCredentials(true);
    }

}
