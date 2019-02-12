package com.testTaskData.config;

import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class StatelessAuthenticationFilter extends GenericFilterBean {
    public StatelessAuthenticationFilter() {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;

        List<String> incomingURLs = Arrays.asList("http://localhost:4200", "*");
        // Get client's origin
        String clientOrigin = ((HttpServletRequest) servletRequest).getHeader("origin");
        String baseOrigin = "http://localhost:4200";

        if (incomingURLs.indexOf(clientOrigin) != -1) {
            baseOrigin = clientOrigin;
        }

        ((HttpServletResponse) servletResponse).setHeader("Access-Control-Allow-Origin", "*");
        ((HttpServletResponse) servletResponse).setHeader("Access-Control-Allow-Methods", "POST, GET, PATCH, OPTIONS, DELETE");
        ((HttpServletResponse) servletResponse).setHeader("Access-Control-Allow-Credentials", "true");
        ((HttpServletResponse) servletResponse).setHeader("Access-Control-Max-Age", "3600");
        ((HttpServletResponse) servletResponse).setHeader("Access-Control-Allow-Headers", "Origin, Authorization, X-Requested-With, Content-Type, Accept, Key, Cross-Origin, X-AUTH-TOKEN, CLIENT-ID");

        if ("OPTIONS".equalsIgnoreCase((req).getMethod())) {
            ((HttpServletResponse) servletResponse).setStatus(HttpServletResponse.SC_OK);
        } else {
            filterChain.doFilter(req, servletResponse);
        }
    }
}
