package com.rmg.practice;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

/**
 * Created by skumar76 on 6/17/2016.
 */




public class OrderToCsvProcessor implements Processor {
    public void process(Exchange exchange) throws Exception {
        String custom = exchange.getIn().getBody(String.class);

        String id = custom.substring(0,10);
        String customerId = custom.substring(10, 19);
        String date = custom.substring(20, 29);
        String items = custom.substring(29);
       // String[] itemIds = items.split("@");

        StringBuilder csv = new StringBuilder();
        csv.append(id.trim());
        csv.append(",").append(customerId.trim());
        csv.append(",").append(date.trim());
        csv.append(",").append(items.trim());
/*
        for(String item : itemIds){

            csv.append(",").append(item.trim());

        }*/
        exchange.getIn().setBody(csv.toString());
    }



}
