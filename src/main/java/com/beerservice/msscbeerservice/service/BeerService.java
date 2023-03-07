package com.beerservice.msscbeerservice.service;

import com.beerservice.msscbeerservice.web.model.BeerDto;

import java.util.UUID;

public interface BeerService {
    public BeerDto getBeerById(UUID beerId);

    BeerDto saveBeer(BeerDto beerDto);

    BeerDto updateBeerById(BeerDto beerDto, UUID beerId);
}
