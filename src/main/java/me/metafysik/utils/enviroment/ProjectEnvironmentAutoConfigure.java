package me.metafysik.utils.enviroment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * @author metafysik
 */
@Configuration
@EnableConfigurationProperties(ProjectEnvironment.class)
public class ProjectEnvironmentAutoConfigure {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProjectEnvironmentAutoConfigure.class);

    @Resource
    private ProjectEnvironment properties;

    @PostConstruct
    private void initialized() {
        LOGGER.info("Current project version is: {}.", properties.getVersion());
        LOGGER.info("Current project key is: {}.", properties.getProjectKey());
        LOGGER.info("Current project environment is: {}.", properties.getEnvironment());
    }
}