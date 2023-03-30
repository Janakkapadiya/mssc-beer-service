package com.sfg.service.inventory;

import com.sfg.config.FeignClientConfig;
import com.sfg.service.inventory.inventoryImpl.BeerInventoryRestTemplateImpl;
import com.sfg.web.model.beerInventory.BeerInventoryDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.UUID;

@FeignClient(value = "beer-inventory-service",
        fallback = InventoryServiceFeignClientFailOver.class,
        configuration = FeignClientConfig.class
)
public interface InventoryServiceFeignClient{
    @GetMapping(value = BeerInventoryRestTemplateImpl.INVENTORY_PATH)
    ResponseEntity<List<BeerInventoryDto>> getOnHandInventory(@PathVariable UUID beerId);
}
