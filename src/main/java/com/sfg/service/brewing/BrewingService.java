package com.sfg.service.brewing;

import com.sfg.config.JmsConfig;
import com.sfg.domain.Beer;
import com.sfg.common.events.BrewBeerEvent;
import com.sfg.repositories.BeerRepository;
import com.sfg.service.inventory.BeerInventoryService;
import com.sfg.web.mapper.BeerMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class BrewingService {
    private final BeerRepository beerRepository;
    private final BeerInventoryService beerInventoryService;
    private final JmsTemplate jmsTemplate;
    private final BeerMapper beerMapper;

    @Scheduled(fixedRate = 5000) //every 5 seconds
    public void checkForLowInventory(){
        List<Beer> beers = beerRepository.findAll();

        log.info("checkForLowInventory is called");

        log.info("beers {}" , beers.size());

        beers.forEach(beer -> {
            Integer invQOH = beerInventoryService.getOnHandInventory(beer.getId());

            log.info("Checking Inventory for: " + beer.getBeerName() + " / " + beer.getId());
            log.info("Min Onhand is: " + beer.getMinOnHand());
            log.info("Inventory is: "  + invQOH);

            if(beer.getMinOnHand() >= invQOH){
                jmsTemplate.convertAndSend(JmsConfig.BREWING_REQUEST_QUEUE, new BrewBeerEvent(beerMapper.beerToBeerDto(beer)));
            }
        });

    }
}
