package pl.filiphagno.spring6webclient.client;

import com.fasterxml.jackson.databind.JsonNode;
import pl.filiphagno.spring6webclient.model.BeerDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.concurrent.Flow;

public interface BeerClient {
    Flux<String> listBeers();

    Flux<Map> listBeerMap();

    Flux<JsonNode> listBeerJson();

    Flux<BeerDTO> listBeerDTO();

    Mono<BeerDTO> getBeerById(String id);

    Flux<BeerDTO> getBeerByBeerStyle(String style);

    Mono<BeerDTO> createBeer(BeerDTO newDto);

    Mono<BeerDTO> updateBeer(BeerDTO dto);
}
