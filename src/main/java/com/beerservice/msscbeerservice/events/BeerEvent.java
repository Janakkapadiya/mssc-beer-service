package com.beerservice.msscbeerservice.events;

import com.beerservice.msscbeerservice.web.model.BeerDto;
import lombok.*;

import java.io.Serializable;

@Data
@RequiredArgsConstructor
@Builder
public class BeerEvent implements Serializable{
    static final long serialVersionUID = 3819979646887464924L;
    private final BeerDto beerDto;
}
