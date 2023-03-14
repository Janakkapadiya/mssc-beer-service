package com.beerservice.msscbeerservice.service.serviceImpl;

import com.beerservice.msscbeerservice.domain.Beer;
import com.beerservice.msscbeerservice.repositories.BeerRepository;
import com.beerservice.msscbeerservice.service.BeerService;
import com.beerservice.msscbeerservice.web.controller.NotFoundException;
import com.beerservice.msscbeerservice.web.mapper.BeerMapper;
import com.beerservice.msscbeerservice.web.model.BeerDto;
import com.beerservice.msscbeerservice.web.model.BeerPagedList;
import com.beerservice.msscbeerservice.web.model.BeerStyleEnum;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BeerServiceImpl implements BeerService {
    private final BeerRepository beerRepository;
    private final BeerMapper beerMapper;

    @SneakyThrows
    @Cacheable(cacheNames = "beerCache", key = "#beerId", condition = "#showInventoryOnHand == false ")
    @Override
    public BeerDto getBeerById(UUID beerId, Boolean showInventoryInHand) {
        System.out.println("i was called");
        if (Boolean.TRUE.equals(showInventoryInHand)) {
            return beerMapper.beerToBeerDtoWithInventory(beerRepository.findById(beerId).orElseThrow(NotFoundException::new));
        } else {
            return beerMapper.beerToBeerDto(beerRepository.findById(beerId).orElseThrow(NotFoundException::new));
        }
    }

    @SneakyThrows
    @Cacheable(cacheNames = "beerUpcCache")
    @Override
    public BeerDto getByUpc(String upc) {
        return beerMapper.beerToBeerDto(beerRepository.findByUpc(upc).orElseThrow(NotFoundException::new));
    }

    @Override
    public BeerDto saveBeer(BeerDto beerDto) {
        return beerMapper.beerToBeerDto(beerRepository.save(beerMapper.beerDtoToBeer(beerDto)));
    }

    @SneakyThrows
    @Override
    public BeerDto updateBeerById(BeerDto beerDto, UUID beerId) {
        Beer beer = beerRepository.findById(beerId).orElseThrow(NotFoundException::new);

        beer.setBeerName(beerDto.getBeerName());
        beer.setBeerStyle(beerDto.getBeerStyle().name());
        beer.setPrice(beerDto.getPrice());
        beer.setUpc(beerDto.getUpc());

        return beerMapper.beerToBeerDto(beerRepository.save(beer));
    }

    @Cacheable(cacheNames = "beerListCache", condition = "#showInventoryOnHand == false")
    @Override
    public BeerPagedList listBeers(String beerName, BeerStyleEnum beerStyle, PageRequest pageRequest, Boolean showInventoryOnHand) {
        BeerPagedList beerPagedList;
        Page<Beer> beerPage;

        System.out.println("i was called");

        if (StringUtils.hasLength(beerName) && beerStyle != null) {
            beerPage = beerRepository.findAllByBeerNameAndBeerStyle(beerName, beerStyle.name(), pageRequest);
        } else if (StringUtils.hasLength(beerName) && beerStyle == null) {
            beerPage = beerRepository.findAllByBeerName(beerName, pageRequest);
        } else if (!StringUtils.hasLength(beerName) && beerStyle != null) {
            beerPage = beerRepository.findAllByBeerStyle(beerStyle.name(), pageRequest);
        } else {
            beerPage = beerRepository.findAll(pageRequest);
        }

        beerPagedList = new BeerPagedList(
                beerPage.getContent().stream().map(beer -> showInventoryOnHand ? beerMapper.beerToBeerDtoWithInventory(beer) : beerMapper.beerToBeerDto(beer)).toList(),
                PageRequest.of(beerPage.getPageable().getPageNumber(),
                        beerPage.getPageable().getPageSize()),
                beerPage.getTotalElements());

        return beerPagedList;
    }
}