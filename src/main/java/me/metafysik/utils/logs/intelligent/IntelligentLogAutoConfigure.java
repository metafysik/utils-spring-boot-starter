package me.metafysik.utils.logs.intelligent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * @author metafysik
 */
@Configuration
@ConditionalOnProperty(prefix = "me.metafysik.intelligent-log", name = "enabled", havingValue = "true", matchIfMissing = true)
public class IntelligentLogAutoConfigure {

    private static final Logger LOGGER = LoggerFactory.getLogger(IntelligentLogAutoConfigure.class);

    @PostConstruct
    public void initialize() {
        LOGGER.info("Intelligent log initialized.");
    }

    @Bean
    public IntelligentLogComponent intelligentLogComponent() {
        return new IntelligentLogComponent();
    }
}