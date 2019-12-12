package com.zmx.okhttp.controller;

import org.mockito.internal.matchers.Or;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : 钟鸣星
 * @date : 2019年12月12日
 */
@RestController
public class OkHttpController {

    private Logger log = LoggerFactory.getLogger(OkHttpController.class);

    @RequestMapping("/getUserInfo")
    public String getUserInfo(String userId, String name){
        log.info("userId={},name={}",userId ,name);
        return userId + name;
    }

    @RequestMapping("/getOrderInfo")
    public Order getOrderInfo(@RequestBody Order order){
        log.info("order:{}" + order);
        return order;
    }
}
