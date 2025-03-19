package pl.filiphagno.spring6webclient.client;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import pl.filiphagno.spring6webclient.model.BeerDTO;
import reactor.core.publisher.Flux;

import java.util.Map;

@Component
public class BeerClientImpl implements BeerClient {

    public static final String BEER_PATH = "/api/v3/beer";
    private final WebClient webClient;


    public BeerClientImpl(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder
                .build();
    }

    @Override
    public Flux<String> listBeers() {
        return webClient.get()
                .uri(BEER_PATH)
                .retrieve()
                .bodyToFlux(String.class);
    }

    @Override
    public Flux<Map> listBeerMap() {
        return webClient.get()
                .uri(BEER_PATH)
                .retrieve()
                .bodyToFlux(Map.class);
    }

    @Override
    public Flux<JsonNode> listBeerJson() {
        return webClient.get()
                .uri(BEER_PATH)
                .retrieve()
                .bodyToFlux(JsonNode.class);
    }

    @Override
    public Flux<BeerDTO> listBeerDTO() {
        return webClient.get().uri(BEER_PATH)
                .retrieve().bodyToFlux(BeerDTO.class);
    }

}
