package com.hubianluanma.spiral_ai_manager.utils;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

import java.time.Duration;
import java.util.Map;

/**
 * @author huhailong
 * @version 1.0
 * @description: TODO
 * @date 2025/11/5 13:52
 */
public class WebClientUtils {
    private final WebClient webClient;

    private WebClientUtils(WebClient webClient) {
        this.webClient = webClient;
    }

    public static WebClientUtils create(String baseUrl) {
        WebClient client = WebClient.builder()
                .baseUrl(baseUrl)
                .defaultHeader("User-Agent", "WebClientUtils/1.0")
                .build();
        return new WebClientUtils(client);
    }

    /**
     * GET 请求，返回 Mono<T>
     **/
    public <T> Mono<T> get(String uri, Map<String, String> headers, Class<T> responseType) {
        return webClient.get()
                .uri(uri)
                .headers(h -> {
                    if (headers != null) headers.forEach(h::add);
                })
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(responseType)
                .timeout(Duration.ofSeconds(10))
                .retryWhen(Retry.backoff(1, Duration.ofSeconds(2)))
                .onErrorResume(WebClientResponseException.class, e ->
                        Mono.error(new RuntimeException("GET请求失败: " + e.getMessage(), e))
                );
    }

    /**
     * POST 请求，发送 JSON
     **/
    public <T> Mono<T> post(String uri, Object body, Class<T> responseType) {
        return webClient.post()
                .uri(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(body))
                .retrieve()
                .bodyToMono(responseType)
                .timeout(Duration.ofSeconds(15))
                .retryWhen(Retry.backoff(2, Duration.ofSeconds(3)))
                .onErrorResume(WebClientResponseException.class, e ->
                        Mono.error(new RuntimeException("POST请求失败: " + e.getMessage(), e))
                );
    }

    /**
     * PUT 请求
     **/
    public <T> Mono<T> put(String uri, Object body, Class<T> responseType) {
        return webClient.put()
                .uri(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(body))
                .retrieve()
                .bodyToMono(responseType);
    }

    /**
     * DELETE 请求
     **/
    public <T> Mono<T> delete(String uri, Class<T> responseType) {
        return webClient.delete()
                .uri(uri)
                .retrieve()
                .bodyToMono(responseType);
    }

    /**
     * SSE 流式请求
     **/
    public Flux<String> sse(String uri, Object requestBody, String key) {
        return webClient.post()
                .uri(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + key)
                .accept(MediaType.TEXT_EVENT_STREAM)
                .body(BodyInserters.fromValue(requestBody))
                .retrieve()
                .bodyToFlux(String.class)
                .timeout(Duration.ofMinutes(5))
                .doOnError(e -> System.err.println("SSE流异常：" + e.getMessage()));
    }
}
