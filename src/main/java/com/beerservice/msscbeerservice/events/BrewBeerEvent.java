package com.beerservice.msscbeerservice.events;

import com.beerservice.msscbeerservice.web.model.BeerDto;

public class BrewBeerEvent extends BeerEvent {
    public BrewBeerEvent(BeerDto beerDto) {
        super(beerDto);
    }
}
