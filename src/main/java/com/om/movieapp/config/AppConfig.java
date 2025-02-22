package com.om.movieapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.support.HttpRequestWrapper;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class AppConfig {

    public static final String TOKEN = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJlMzBiZDM4ZjhlZmUzNDVlMGI0MDI3YmIyYTljODk3NSIsIm5iZiI6MTUyOTEzNzIyOC4xNzYsInN1YiI6IjViMjRjODRjMGUwYTI2MWUxODAwMmU4NyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.WgqUgSEpaaMnJFoEJALZYK7gYmTUB_dSki66YVwscj4";

    @Bean(name = "tmDbRestTemplate")
    public RestTemplate tmDbRestTemplate() {
        RestTemplate restTemplate = new RestTemplate();

        // Create an interceptor to add headers
        ClientHttpRequestInterceptor interceptor = (request, body, execution) -> {
            HttpRequestWrapper requestWrapper = new HttpRequestWrapper(request);
            requestWrapper.getHeaders().set("Authorization", "Bearer " + TOKEN);
            requestWrapper.getHeaders().set("Accept", "application/json");
            return execution.execute(requestWrapper, body);
        };

        // Add the interceptor to the RestTemplate
        List<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
        interceptors.add(interceptor);
        restTemplate.setInterceptors(interceptors);

        return restTemplate;
    }

}
