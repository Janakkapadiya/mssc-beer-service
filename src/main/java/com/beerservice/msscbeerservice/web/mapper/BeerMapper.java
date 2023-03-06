package com.beerservice.msscbeerservice.web.mapper;

import com.beerservice.msscbeerservice.domain.Beer;
import com.beerservice.msscbeerservice.web.model.BeerDto;
import org.mapstruct.Mapper;

@Mapper(uses = {DateMapper.class})
interface BeerMapper {
    BeerDto beerToBeerDto(Beer beer);

    Beer beerDtoToBeer(BeerDto beerDto);
}
