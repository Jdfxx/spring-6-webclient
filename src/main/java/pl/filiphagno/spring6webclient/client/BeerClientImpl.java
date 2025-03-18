package pl.filiphagno.spring6webclient.client;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Component
public class BeerClientImpl implements BeerClient {

    private final WebClient webClient;


    public BeerClientImpl(WebClient.Builder webClientBuilder) {
        this.webClient = WebClient.builder()
                .baseUrl("http://localhost:8080")
                .build();
    }

    @Override
    public Flux<String> listBeers() {
        return webClient.get()
                .uri("/api/v3/beer", String.class)
                .retrieve()
                .bodyToFlux(String.class);
    }
}
