package com.covelopfit.autotrading.controller;

import com.covelopfit.autotrading.dto.CommonResponse;
import com.covelopfit.autotrading.dto.OrderApiResponse;
import com.covelopfit.autotrading.dto.OrderForm;
import com.covelopfit.autotrading.service.OrderService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@Controller
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping("order-page")
    public String getOrderPage(){
        return "order-page";
    }

    @PostMapping(value = "orders")
    @ResponseBody
    public CommonResponse postOrder(@ModelAttribute @Validated OrderForm orderForm) {
        log.debug(orderForm.toString());
        OrderApiResponse orderApiResponse = orderService.postOrder(orderForm);

        if(orderApiResponse == null){
            return new CommonResponse(HttpStatus.INTERNAL_SERVER_ERROR, "OrderService 내 에러 or API 실패");
        }

        return new CommonResponse(HttpStatus.OK, "성공", orderApiResponse);
    }


    @GetMapping(value = "order")
    @ResponseBody
    public CommonResponse getOrder() {
        OrderApiResponse orderApiResponse = orderService.getOrder();

        if(orderApiResponse == null){
            return new CommonResponse(HttpStatus.INTERNAL_SERVER_ERROR, "OrderService 내 에러 or API 실패");
        }

        return new CommonResponse(HttpStatus.OK, "성공", orderApiResponse);
    }


    @DeleteMapping(value = "order/{uuid}")
    @ResponseBody
    public CommonResponse deleteOrder(@PathVariable String uuid) {
        OrderApiResponse orderApiResponse = orderService.deleteOrder(uuid);

        if(orderApiResponse == null){
            return new CommonResponse(HttpStatus.INTERNAL_SERVER_ERROR, "OrderService 내 에러 or API 실패");
        }

        return new CommonResponse(HttpStatus.OK, "성공", orderApiResponse);
    }

}
