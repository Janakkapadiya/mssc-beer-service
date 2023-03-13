package com.beerservice.msscbeerservice.service.inventory;

import com.beerservice.msscbeerservice.bootstrep.BeerLoader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Disabled //utility for manual testing
@SpringBootTest
class BeerInventoryServiceRestTemplateTest {

    @Autowired
    private BeerInventoryService beerInventoryService;

    @BeforeEach
    void setUp() {

    }
    @Test
    void getOnHandInventory() {
        // todo evolve to use UPC
    }

}
