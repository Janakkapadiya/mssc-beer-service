package com.sfg.web.controller;

import com.sfg.service.BeerService;
import com.sfg.web.model.BeerDto;
import com.sfg.web.model.BeerStyleEnum;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BeerController.class)
class BeerControllerTest {

    public static final String BEER_1_UPC = "0631234200036";
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    BeerService beerService;

    @Test
    void getBearById() throws Exception {

        given(beerService.getBeerById(any(),anyBoolean())).willReturn(getValidBeerDto());

        mockMvc.perform(get("/api/v1/beer/" + UUID.randomUUID()).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }

    @Test
    void saveNewBeer() throws Exception {
        BeerDto beerDto = getValidBeerDto();
        String beerDtoJson = objectMapper.writeValueAsString(beerDto);

        given(beerService.saveBeer(any())).willReturn(getValidBeerDto());

        mockMvc.perform(post("/api/v1/beer/").
                contentType(MediaType.APPLICATION_JSON).
                content(beerDtoJson)).andExpect(status().
                isCreated());
    }

    @Test
    void updateBeerById() throws Exception {

        given(beerService.updateBeerById(any(), any())).willReturn(getValidBeerDto());

        BeerDto beerDto = getValidBeerDto();
        String beerDtoJson = objectMapper.writeValueAsString(beerDto);
        mockMvc.perform(put("/api/v1/beer/" + UUID.randomUUID()).
                        contentType(MediaType.APPLICATION_JSON).
                        content(beerDtoJson)).
                andExpect(status().
                        isNoContent());
    }

    BeerDto getValidBeerDto() {
        return BeerDto.builder()
                .beerName("My Beer")
                .beerStyle(BeerStyleEnum.IPA)
                .upc(BEER_1_UPC)
                .price(new BigDecimal("2.99"))
                .build();
    }
}