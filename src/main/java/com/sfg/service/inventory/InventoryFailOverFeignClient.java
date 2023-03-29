package com.sfg.service.inventory;

import com.sfg.web.model.beerInventory.BeerInventoryDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "inventory-failover")
public interface InventoryFailOverFeignClient {
    @GetMapping(value = "/inventory-failover")
    ResponseEntity<List<BeerInventoryDto>> getOnHandInventory();
}
