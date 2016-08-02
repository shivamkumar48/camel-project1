package com.rmg.practice;

import org.apache.camel.Exchange;
import org.apache.camel.processor.aggregate.AggregationStrategy;

/**
 * Created by skumar76 on 7/8/2016.
 */
public class Enricher implements AggregationStrategy {


    public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {

        String body1 = oldExchange.getIn().getBody(String.class);
        String newBody = newExchange.getIn().getBody(String.class);

        String enrichd = body1 + "\n" + newBody;
         oldExchange.getIn().setBody(enrichd);

        return oldExchange ;
    }
}
