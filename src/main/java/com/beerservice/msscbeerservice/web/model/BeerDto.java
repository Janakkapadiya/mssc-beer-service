package com.beerservice.msscbeerservice.web.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BeerDto {
    @Null
    private UUID id;
    @Null
    private Integer version;
    @Null
    private OffsetDateTime createdDate;
    @Null
    private OffsetDateTime lastModifiedDate;
    @NotBlank(message = "beerName should not be blank")
    private String beerName;
    @NotNull(message = "beerStyle should not be null")
    private BeerStyleEnum beerStyle;
    @NotNull(message = "upc should not be null")
    @Positive
    private Long upc;
    @Positive
    @NotNull(message = "prince should not be null")
    private BigDecimal price;
    private Integer quantityOnHand;
}
