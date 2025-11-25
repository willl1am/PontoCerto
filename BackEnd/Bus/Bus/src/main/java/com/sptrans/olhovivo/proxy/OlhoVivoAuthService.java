
package com.sptrans.olhovivo.proxy;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class OlhoVivoAuthService {

    @Value("${olhovivo.api.url}")
    private String baseUrl;

    @Value("${olhovivo.api.token}")
    private String apiToken;

    private String authCookie = null;
    private final RestTemplate restTemplate = new RestTemplate();


    public String getAuthCookie() {
        if (authCookie != null) {
            return authCookie;
        }

        String authUrl = baseUrl + "/Login/Autenticar?token=" + apiToken;
        System.out.println("Iniciando autenticação em: " + authUrl);

        try {
            // A API retorna 'true' em texto puro se a autenticação for bem-sucedida
            ResponseEntity<String> response = restTemplate.exchange(
                    authUrl,
                    HttpMethod.POST,
                    HttpEntity.EMPTY,
                    String.class
            );

            if (response.getStatusCode().is2xxSuccessful() && "true".equalsIgnoreCase(response.getBody())) {
                List<String> cookies = response.getHeaders().get(HttpHeaders.SET_COOKIE);
                if (cookies != null && !cookies.isEmpty()) {
                    // O cookie de autenticação é o primeiro da lista
                    authCookie = cookies.get(0).split(";")[0];
                    System.out.println("✅ Autenticação bem-sucedida. Cookie: " + authCookie);
                    return authCookie;
                }
            }

            System.err.println("❌ Falha na autenticação. Status: " + response.getStatusCode() + ", Resposta: " + response.getBody());
            return null;

        } catch (Exception e) {
            System.err.println("❌ Erro na requisição de autenticação: " + e.getMessage());
            return null;
        }
    }


    public void forceReauthenticate() {
        this.authCookie = null;
        getAuthCookie();
    }
}