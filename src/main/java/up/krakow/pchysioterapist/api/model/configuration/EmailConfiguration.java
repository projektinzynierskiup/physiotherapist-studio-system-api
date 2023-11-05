package up.krakow.pchysioterapist.api.model.configuration;

import com.fasterxml.jackson.databind.annotation.JsonValueInstantiator;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Getter
@Configuration
@PropertySource("classpath:application-email.properties")
public class EmailConfiguration {
    @Value("${email.acceptation}")
    private String acceptation;
    @Value("${email.change}")
    private String change;
    @Value("${email.rejection}")
    private String rejection;
    @Value("${email.rejection}")
    private String ban;
    @Value("${email.default.description}")
    private String defaultDescription;
    @Value("${email.default.reject.description}")
    private String defaultRejectDescription;
    @Value("${email.default.accept.description}")
    private String defaultAcceptDescription;

}
