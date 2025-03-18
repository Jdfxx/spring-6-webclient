package pl.filiphagno.spring6webclient.client;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.atomic.AtomicBoolean;

import static org.awaitility.Awaitility.await;

@SpringBootTest
class BeerClientImplTest {

    @Autowired
    BeerClient beerClient;

    @Test
    void listBeers() {
        AtomicBoolean atomicBoolean = new AtomicBoolean(false);
        beerClient.listBeers().subscribe(response -> {
                    System.out.println(response);
                    atomicBoolean.set(true);
                }
        );

        await().untilTrue(atomicBoolean);
    }

    @Test
    void listBeersMap() {
        AtomicBoolean atomicBoolean = new AtomicBoolean(false);

        beerClient.listBeerMap().subscribe(response -> {
            System.out.println(response);
            atomicBoolean.set(true);
        });

        await().untilTrue(atomicBoolean);

    }
}