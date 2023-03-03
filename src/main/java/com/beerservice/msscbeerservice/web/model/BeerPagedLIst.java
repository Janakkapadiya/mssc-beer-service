package com.beerservice.msscbeerservice.web.model;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class BeerPagedLIst extends PageImpl<BeerDto> {
    public BeerPagedLIst(List<BeerDto> content, Pageable pageable, long total) {
        super(content, pageable, total);
    }

    public BeerPagedLIst(List<BeerDto> content) {
        super(content);
    }
}
