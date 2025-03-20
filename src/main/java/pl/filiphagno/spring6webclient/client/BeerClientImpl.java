package pl.filiphagno.spring6webclient.client;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import pl.filiphagno.spring6webclient.model.BeerDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

@Component
public class BeerClientImpl implements BeerClient {

    public static final String BEER_PATH = "/api/v3/beer";
    public static final String BEER_PATH_ID = BEER_PATH + "/{beerId}";
    private final WebClient webClient;

    @Override
    public Mono<BeerDTO> updateBeer(BeerDTO beerDTO) {
        return webClient.put()
                .uri(uriBuilder -> uriBuilder.path(BEER_PATH_ID).build(beerDTO.getId()))
                .body(Mono.just(beerDTO), BeerDTO.class)
                .retrieve()
                .toBodilessEntity()
                .flatMap(voidResponseEntity -> getBeerById(beerDTO.getId()));
    }

    @Override
    public Mono<BeerDTO> createBeer(BeerDTO newDto) {
        return webClient.post()
                .uri(BEER_PATH)
                .body(Mono.just(newDto), BeerDTO.class)
                .retrieve()
                .toBodilessEntity()
                .flatMap(voidResponseEntity ->  Mono.just(voidResponseEntity
                        .getHeaders().get("Location").getFirst()))
                .map(path -> path.split("/")[path.split("/").length-1])
                .flatMap(this::getBeerById);
    }

    @Override
    public Flux<BeerDTO> getBeerByBeerStyle(String beerStyle) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path(BEER_PATH)
                        .queryParam("beerStyle", beerStyle)
                        .build())
                .retrieve()
                .bodyToFlux(BeerDTO.class);
    }

    @Override
    public Mono<BeerDTO> getBeerById(String id) {
        return webClient.get()
                .uri( builder -> builder.path(BEER_PATH_ID).build(id))
                .retrieve()
                .bodyToMono(BeerDTO.class);
    }

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
