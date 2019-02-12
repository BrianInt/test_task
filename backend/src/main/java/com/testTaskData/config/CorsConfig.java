package com.testTaskData.config;

import org.springframework.stereotype.Component;

@Component
class CorsConfig {
    private boolean allowedCredentials;

    private String[] allowedOrigin;

    private String[] allowedHeaders;

    private String[] allowedMethods;

    public boolean isAllowedCredentials() {
        return allowedCredentials;
    }

    public void setAllowedCredentials(boolean allowedCredentials) {
        this.allowedCredentials = allowedCredentials;
    }

    public String[] getAllowedOrigin() {
        return allowedOrigin;
    }

    public void setAllowedOrigin(String[] allowedOrigin) {
        this.allowedOrigin = allowedOrigin;
    }

    public String[] getAllowedHeaders() {
        return allowedHeaders;
    }

    public void setAllowedHeaders(String[] allowedHeaders) {
        this.allowedHeaders = allowedHeaders;
    }

    public String[] getAllowedMethods() {
        return allowedMethods;
    }

    public void setAllowedMethods(String[] allowedMethods) {
        this.allowedMethods = allowedMethods;
    }
}
