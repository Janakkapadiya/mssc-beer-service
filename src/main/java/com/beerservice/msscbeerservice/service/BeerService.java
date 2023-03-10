package com.beerservice.msscbeerservice.service;

import com.beerservice.msscbeerservice.web.model.BeerDto;
import com.beerservice.msscbeerservice.web.model.BeerPagedList;
import com.beerservice.msscbeerservice.web.model.BeerStyleEnum;
import org.springframework.data.domain.PageRequest;

import java.util.UUID;

public interface BeerService {
    public BeerDto getBeerById(UUID beerId,Boolean showInventoryInHand);

    BeerDto saveBeer(BeerDto beerDto);

    BeerDto updateBeerById(BeerDto beerDto, UUID beerId);

    BeerPagedList listBeers(String beerName, BeerStyleEnum beerStyle, PageRequest pageRequest,Boolean showInventoryOnHand);

    BeerDto getByUpc(String upc);
}
