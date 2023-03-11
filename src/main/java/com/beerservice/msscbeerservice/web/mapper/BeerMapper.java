package com.beerservice.msscbeerservice.web.mapper;

import com.beerservice.msscbeerservice.domain.Beer;
import com.beerservice.msscbeerservice.web.model.BeerDto;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = {DateMapper.class})
@DecoratedWith(BeerMapperDecorator.class)
public interface BeerMapper {

    @Mapping(target = "quantityOnHand", ignore = true)
    BeerDto beerToBeerDto(Beer beer);
    @Mapping(target = "quantityOnHand", ignore = true)
    BeerDto beerToBeerDtoWithInventory(Beer beer);
    @Mapping(target = "quantityToBrew", ignore = true)
    @Mapping(target = "minOnHand", ignore = true)
    Beer beerDtoToBeer(BeerDto beerDto);
}
