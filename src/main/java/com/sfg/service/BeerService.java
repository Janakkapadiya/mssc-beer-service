package com.sfg.service;

import com.sfg.web.model.BeerDto;
import com.sfg.web.model.BeerPagedList;
import com.sfg.web.model.BeerStyleEnum;
import org.springframework.data.domain.PageRequest;

import java.util.UUID;

public interface BeerService {
    public BeerDto getBeerById(UUID beerId, Boolean showInventoryInHand);

    BeerDto saveBeer(BeerDto beerDto);

    BeerDto updateBeerById(BeerDto beerDto, UUID beerId);

    BeerPagedList listBeers(String beerName, BeerStyleEnum beerStyle, PageRequest pageRequest, Boolean showInventoryOnHand);

    BeerDto getByUpc(String upc);
}
