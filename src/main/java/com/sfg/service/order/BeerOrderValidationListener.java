package com.sfg.service.order;

import com.sfg.config.JmsConfig;
import com.sfg.web.model.events.ValidateOrderRequest;
import com.sfg.web.model.events.ValidateOrderResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class BeerOrderValidationListener {
    private final BeerOrderValidator beerOrderValidator;
    private final JmsTemplate jmsTemplate;
    @JmsListener(destination = JmsConfig.VALIDATE_ORDER_QUEUE)
    public void listen(ValidateOrderRequest validateOrderRequest)
    {
        Boolean validated = beerOrderValidator.validateOrder(validateOrderRequest.getBeerOrder());

        log.info("is validated {}" , validated);

        log.info("beerOrderId {}",validateOrderRequest.getBeerOrder().getId());

        jmsTemplate.convertAndSend(JmsConfig.VALIDATE_ORDER_RESPONSE_QUEUE,
                ValidateOrderResult.builder()
                        .isValid(validated)
                        .orderId(validateOrderRequest.getBeerOrder().getId())
                        .build());
    }
}
