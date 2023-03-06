package com.beerservice.msscbeerservice.bootstrep;

import com.beerservice.msscbeerservice.domain.Beer;
import com.beerservice.msscbeerservice.repositories.BeerRepository;
import org.springframework.boot.CommandLineRunner;

import java.math.BigDecimal;

public class BeerLoader implements CommandLineRunner{
    public final BeerRepository beerRepository;
    public BeerLoader(BeerRepository beerRepository) {
        this.beerRepository = beerRepository;
    }

    @Override
    public void run(String... args) throws Exception {

    }

    public void loadBeerObjects()
    {
        if(beerRepository.count() == 0)
        {
            beerRepository.save(Beer.builder().
                    beerName("Mango Bobs").
                    beerStyle("IPA").
                    quantityToBrew(200).
                    minOnHand(12).
                    upc(337872138L).
                    price(new BigDecimal("12.95")).
                    build());

            beerRepository.save(Beer.builder().
                    beerName("Galaxy Cat").
                    beerStyle("PALE_ALE").
                    quantityToBrew(200).
                    minOnHand(12).
                    upc(337872140L).
                    price(new BigDecimal("11.95")).
                    build());
        }
    }
}
