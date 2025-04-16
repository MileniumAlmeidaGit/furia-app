package com.seuprojeto.service;

import java.util.Collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

@Service
public class SocialMentionService {

    private static final Logger logger = LoggerFactory.getLogger(SocialMentionService.class);

    private final WebClient twitterClient;
    private final WebClient instagramClient;
    private final String twitterBearerToken;
    private final String instagramAccessToken;
    private final String instagramUserId;

    public SocialMentionService(
            @Value("${twitter.api.url}") String twitterApiUrl,
            @Value("${twitter.bearer.token:}") String twitterBearerToken,
            @Value("${instagram.api.url}") String instagramApiUrl,
            @Value("${instagram.access.token:}") String instagramAccessToken,
            @Value("${instagram.user.id:}") String instagramUserId) {

        this.twitterBearerToken = twitterBearerToken;
        this.instagramAccessToken = instagramAccessToken;
        this.instagramUserId = instagramUserId;

        // Se o token do Twitter estiver ausente, não configura o client real
        if (StringUtils.hasText(twitterBearerToken)) {
            this.twitterClient = WebClient.builder()
                    .baseUrl(twitterApiUrl)
                    .defaultHeader("Authorization", "Bearer " + twitterBearerToken)
                    .build();
        } else {
            logger.warn("Twitter Bearer Token não fornecido, usando resposta dummy.");
            this.twitterClient = null;
        }

        // Configura o cliente do Instagram se o token estiver preenchido
        if (StringUtils.hasText(instagramAccessToken) && StringUtils.hasText(instagramUserId)) {
            this.instagramClient = WebClient.builder()
                    .baseUrl(instagramApiUrl)
                    .defaultUriVariables(Collections.singletonMap("access_token", instagramAccessToken))
                    .build();
        } else {
            logger.warn("Instagram Access Token ou User ID não fornecidos, usando resposta dummy.");
            this.instagramClient = null;
        }
    }

    /**
     * Busca menções recentes no Twitter usando a API v2,
     * se as credenciais estiverem ausentes, retorna resposta dummy.
     *
     * @param query a string de busca, ex.: "FURIA"
     * @return Mono<String> com a resposta JSON ou mensagem dummy.
     */
    public Mono<String> fetchTwitterMentions(String query) {
        if (this.twitterClient == null) {
            // Simulação dummy
            String dummyResponse = "{\"data\": [{\"id\":\"1\", \"text\": \"Dummy tweet mentioning " + query + "\"}]}";
            return Mono.just(dummyResponse);
        }
        return twitterClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/2/tweets/search/recent")
                        .queryParam("query", query)
                        .queryParam("tweet.fields", "created_at,author_id")
                        .build())
                .retrieve()
                .onStatus(status -> status.is4xxClientError(), response -> {
                    logger.error("Erro 4xx na API Twitter: {}", response.statusCode());
                    return response.bodyToMono(String.class)
                            .flatMap(body -> Mono.error(new RuntimeException("API Twitter 4xx: " + body)));
                })
                .onStatus(status -> status.is5xxServerError(), response -> {
                    logger.error("Erro 5xx na API Twitter: {}", response.statusCode());
                    return response.bodyToMono(String.class)
                            .flatMap(body -> Mono.error(new RuntimeException("API Twitter 5xx: " + body)));
                })
                .bodyToMono(String.class)
                .onErrorResume(ex -> {
                    logger.error("Erro na chamada da API Twitter: {}", ex.getMessage());
                    return Mono.just("Erro ao recuperar menções do Twitter: " + ex.getMessage());
                });
    }

    /**
     * Busca posts com determinada hashtag no Instagram usando a Instagram Graph API,
     * se as credenciais estiverem ausentes, retorna resposta dummy.
     *
     * @param hashtag a hashtag sem o símbolo #, ex.: "FURIA"
     * @return Mono<String> com a resposta JSON ou mensagem dummy.
     */
    public Mono<String> fetchInstagramMentions(String hashtag) {
        if (this.instagramClient == null) {
            // Simulação dummy para Instagram
            String dummyResponse = "{\"data\": [{\"id\":\"101\", \"caption\": \"Dummy Instagram post with hashtag " + hashtag + "\"}]}";
            return Mono.just(dummyResponse);
        }
        return instagramClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/ig_hashtag_search")
                        .queryParam("user_id", instagramUserId)
                        .queryParam("q", hashtag)
                        .queryParam("access_token", instagramAccessToken)
                        .build())
                .retrieve()
                .onStatus(status -> status.is4xxClientError(), response -> {
                    logger.error("Erro 4xx na API Instagram: {}", response.statusCode());
                    return response.bodyToMono(String.class)
                            .flatMap(body -> Mono.error(new RuntimeException("API Instagram 4xx: " + body)));
                })
                .onStatus(status -> status.is5xxServerError(), response -> {
                    logger.error("Erro 5xx na API Instagram: {}", response.statusCode());
                    return response.bodyToMono(String.class)
                            .flatMap(body -> Mono.error(new RuntimeException("API Instagram 5xx: " + body)));
                })
                .bodyToMono(String.class)
                .onErrorResume(ex -> {
                    logger.error("Erro na chamada da API Instagram: {}", ex.getMessage());
                    return Mono.just("Erro ao recuperar menções do Instagram: " + ex.getMessage());
                });
    }
}
