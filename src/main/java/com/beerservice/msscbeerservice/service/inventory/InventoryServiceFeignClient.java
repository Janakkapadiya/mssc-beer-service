package com.beerservice.msscbeerservice.service.inventory;

import com.beerservice.msscbeerservice.service.inventory.inventoryImpl.BeerInventoryRestTemplateImpl;
import com.beerservice.msscbeerservice.service.inventory.model.BeerInventoryDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.UUID;

@FeignClient("beer-inventory-service")
public interface InventoryServiceFeignClient{
    @GetMapping(value = BeerInventoryRestTemplateImpl.INVENTORY_PATH)
    ResponseEntity<List<BeerInventoryDto>> getOnHandInventory(@PathVariable UUID beerId);
}
