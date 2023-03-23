package com.beerservice.msscbeerservice.service.inventory;

import com.beerservice.msscbeerservice.service.inventory.model.BeerInventoryDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
@Slf4j
@RequiredArgsConstructor
@Profile("local-discovery")
@Service
public class InventoryServiceFeign implements BeerInventoryService{
    private final InventoryServiceFeignClient inventoryServiceFeignClient;
    @Override
    public Integer getOnHandInventory(UUID beerId) {
        log.info("Calling inventory service ...");

        ResponseEntity<List<BeerInventoryDto>> responseEntity = inventoryServiceFeignClient.getOnHandInventory(beerId);

        Integer onHand = Objects.requireNonNull(responseEntity.getBody())
                .stream()
                .mapToInt(BeerInventoryDto::getQuantityOnHand)
                .sum();

        log.info("quantityOnHand is {} for beerId {}", onHand, beerId);

        return onHand;
    }
}
