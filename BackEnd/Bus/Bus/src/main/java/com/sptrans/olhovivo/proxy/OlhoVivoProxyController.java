
package com.sptrans.olhovivo.proxy;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.HandlerMapping;

import jakarta.servlet.http.HttpServletRequest;
import java.net.URI;
import java.util.Enumeration;

@RestController
@CrossOrigin(origins = "*") // Habilita CORS para todas as origens
public class OlhoVivoProxyController {

    @Value("${olhovivo.api.url}")
    private String baseUrl;

    private final OlhoVivoAuthService authService;
    private final RestTemplate restTemplate = new RestTemplate();

    public OlhoVivoProxyController(OlhoVivoAuthService authService) {
        this.authService = authService;
    }


    @RequestMapping("/proxy/**")
    public ResponseEntity<String> proxyRequest(HttpServletRequest request) {

        // Obter o método HTTP da requisição
        HttpMethod method = HttpMethod.valueOf(request.getMethod());


        String authCookie = authService.getAuthCookie();
        if (authCookie == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Falha na autenticação com a API Olho Vivo.");
        }


        String path = (String) request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);

        String apiPath = path.substring("/proxy".length());


        String queryString = request.getQueryString();
        String fullUrl = baseUrl + apiPath + (queryString != null ? "?" + queryString : "");


        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.COOKIE, authCookie);


        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();

            if (!headerName.equalsIgnoreCase(HttpHeaders.HOST) &&
                    !headerName.equalsIgnoreCase(HttpHeaders.COOKIE) &&
                    !headerName.equalsIgnoreCase(HttpHeaders.CONNECTION) &&
                    !headerName.equalsIgnoreCase(HttpHeaders.ACCEPT_ENCODING)) {
                headers.add(headerName, request.getHeader(headerName));
            }
        }


        HttpEntity<String> httpEntity;
        try {

            String requestBody = request.getReader().lines().reduce("", (accumulator, actual) -> accumulator + actual);
            httpEntity = new HttpEntity<>(requestBody, headers);
        } catch (Exception e) {
            httpEntity = new HttpEntity<>(headers);
        }


        try {
            ResponseEntity<String> response = restTemplate.exchange(
                    new URI(fullUrl),
                    method,
                    httpEntity,
                    String.class
            );


            return ResponseEntity.status(response.getStatusCode())
                    .headers(response.getHeaders())
                    .body(response.getBody());

        } catch (HttpClientErrorException e) {

            if (e.getStatusCode() == HttpStatus.FORBIDDEN) {

                authService.forceReauthenticate();

            }
            return ResponseEntity.status(e.getStatusCode()).body(e.getResponseBodyAsString());
        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno do proxy: " + e.getMessage());
        }
    }
}