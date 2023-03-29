package com.sfg.service.inventory;

import com.sfg.web.model.beerInventory.BeerInventoryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class InventoryServiceFeignClientFailOver implements InventoryServiceFeignClient{
    private final InventoryFailOverFeignClient inventoryFailOverFeignClient;
    @Override
    public ResponseEntity<List<BeerInventoryDto>> getOnHandInventory(UUID beerId) {
        return inventoryFailOverFeignClient.getOnHandInventory();
    }
}
