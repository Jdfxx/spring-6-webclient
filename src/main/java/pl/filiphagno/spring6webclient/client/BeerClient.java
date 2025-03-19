package pl.filiphagno.spring6webclient.client;

import com.fasterxml.jackson.databind.JsonNode;
import pl.filiphagno.spring6webclient.model.BeerDTO;
import reactor.core.publisher.Flux;

import java.util.Map;

public interface BeerClient {
    Flux<String> listBeers();

    Flux<Map> listBeerMap();

    Flux<JsonNode> listBeerJson();

    Flux<BeerDTO> listBeerDTO();
}
