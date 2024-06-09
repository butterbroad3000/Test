package com.example.testovoe;

import com.example.testovoe.request.PatientRequestCreate;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Map;


@FeignClient(name = "patientClient", url ="${app.url}")
public interface PatientClient {

  @PostMapping("/patient/create")
  void createPatient(@RequestBody PatientRequestCreate patientRequestCreate);

  @Configuration
  class FeignConfiguration {

    @Value("${keycloak.credentials.secret}")
    private String clientSecret;

    @Value("${keycloak.resource}")
    private String clientId;

    @Value("${keycloak.auth-server-url}")
    private String authServerUrl;

    @Value("${keycloak.realm}")
    private String realm;

    @Value("${keycloak.username}")
    private String username;

    @Value("${keycloak.password}")
    private String password;

    @Bean
    public RequestInterceptor requestInterceptor() {
      return new RequestInterceptor() {
        @Override
        public void apply(RequestTemplate template) {
          RestTemplate restTemplate = new RestTemplate();
          String authUrl = authServerUrl + "/realms/" + realm + "/protocol/openid-connect/token";

          MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
          map.add("client_id", clientId);
          map.add("client_secret", clientSecret);
          map.add("username", username);
          map.add("password", password);
          map.add("grant_type", "password");

          HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, new HttpHeaders());
          ResponseEntity<Map> response = restTemplate.postForEntity(authUrl, request, Map.class);
          String accessToken = (String) response.getBody().get("access_token");

          template.header("Authorization", "Bearer " + accessToken);
        }
      };
    }
  }
}
