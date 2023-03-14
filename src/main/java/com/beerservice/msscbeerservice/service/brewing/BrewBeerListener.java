package com.beerservice.msscbeerservice.service.brewing;

import com.beerservice.msscbeerservice.config.JmsConfig;
import com.beerservice.msscbeerservice.domain.Beer;
import com.beerservice.msscbeerservice.events.BrewBeerEvent;
import com.beerservice.msscbeerservice.events.NewInventoryEvent;
import com.beerservice.msscbeerservice.repositories.BeerRepository;
import com.beerservice.msscbeerservice.web.model.BeerDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class BrewBeerListener {
    private final BeerRepository beerRepository;
    private final JmsTemplate jmsTemplate;
    @JmsListener(destination = JmsConfig.BREWING_REQUEST_QUEUE)
    public void listen(BrewBeerEvent beerEvent) {
        BeerDto beerDto = beerEvent.getBeerDto();
        Beer beer = beerRepository.getReferenceById(beerDto.getId());
        beerDto.setQuantityOnHand(beer.getQuantityToBrew());

        NewInventoryEvent event = new NewInventoryEvent(beerDto);

        log.info("brewed beer" + beer.getMinOnHand() + "QOH" + beerDto.getQuantityOnHand());

        jmsTemplate.convertAndSend(JmsConfig.NEW_INVENTORY_QUEUE,event);
    }
}
