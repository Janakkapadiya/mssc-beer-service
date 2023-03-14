package com.beerservice.msscbeerservice.events;

import com.beerservice.msscbeerservice.web.model.BeerDto;

public class NewInventoryEvent extends BeerEvent{
    public NewInventoryEvent(BeerDto beerDto) {
        super(beerDto);
    }
}
