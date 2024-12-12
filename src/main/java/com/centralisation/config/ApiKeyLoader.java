package com.centralisation.config;

import org.springframework.beans.factory.annotation.Value;

import java.util.List;

public class ApiKeyLoader {
    @Value("${api.groupe1}")
    private String apiKeyGroupe1;
    @Value("${api.groupe2}")
    private String apiKeyGroupe2;
    @Value("${api.groupe3}")
    private String apiKeyGroupe3;
    @Value("${api.groupe4}")
    private String apiKeyGroupe4;

    public String getApiKeyFromString(String apiKey) {
        List<String> apikeys = List.of(apiKeyGroupe1, apiKeyGroupe2, apiKeyGroupe3, apiKeyGroupe4);
        if (apikeys.contains(apiKey)) {
            return apiKey;
        } else {
            return null;
        }
    }
}