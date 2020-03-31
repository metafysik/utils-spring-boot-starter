package me.metafysik.utils.enviroment;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author metafysik
 */
@ConfigurationProperties("me.metafysik.project")
public class ProjectEnvironment {

    public String getProjectKey() {
        return projectKey;
    }

    public void setProjectKey(String projectKey) {
        this.projectKey = projectKey;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private String projectKey;

    private String projectName;

    private String environment;

    private String version;

    private String description;
}