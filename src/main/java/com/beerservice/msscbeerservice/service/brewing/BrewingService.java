package com.beerservice.msscbeerservice.service.brewing;

import com.beerservice.msscbeerservice.config.JmsConfig;
import com.beerservice.msscbeerservice.domain.Beer;
import com.beerservice.msscbeerservice.events.BrewBeerEvent;
import com.beerservice.msscbeerservice.repositories.BeerRepository;
import com.beerservice.msscbeerservice.service.inventory.BeerInventoryService;
import com.beerservice.msscbeerservice.web.mapper.BeerMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class BrewingService {
    private final BeerRepository beerRepository;
    private final JmsTemplate jmsTemplate;
    private final BeerInventoryService beerInventoryService;
    private final BeerMapper beerMapper;

    @Scheduled(fixedRate = 5000)
    public void checkForLowRepository() {
        List<Beer> beerList = beerRepository.findAll();
        beerList.forEach(beer -> {
            Integer onHand = beerInventoryService.getOnHandInventory(beer.getId());

            log.info("min on hand is {}", beer.getMinOnHand());
            log.info("Inventory is {}", onHand);

            if (beer.getMinOnHand() >= onHand) {
                jmsTemplate.convertAndSend(JmsConfig.BREWING_REQUEST_QUEUE, new BrewBeerEvent(beerMapper.beerToBeerDto(beer)));
            }
        });
    }
}
