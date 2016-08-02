package com.rmg.practice;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.model.dataformat.BindyType;
import scala.testing.SUnit;
import scala.testing.SUnit.TestCase;

import java.math.BigDecimal;

/**
 * Created by skumar76 on 7/20/2016.
 */
public class PurchaseOrderBindyTest {


    public void testBindy() throws Exception {
        CamelContext context = new DefaultCamelContext();
        context.addRoutes(createRoute());
        context.start();
        MockEndpoint mock = context.getEndpoint("mock:result",
                MockEndpoint.class);
        mock.expectedBodiesReceived("Camel in Action,50,1\n");
        Purchase order = new Purchase();
        order.setAmount(1);
        order.setPrice("50");
        order.setName("Camel in Action");
        ProducerTemplate template = context.createProducerTemplate();
        template.sendBody("direct:toCsv", order);
        mock.assertIsSatisfied();
    }

    public RouteBuilder createRoute() {
        return new RouteBuilder() {
            public void configure() throws Exception {
                from("direct:order")
                        .marshal().bindy(BindyType.Csv,
                        "camelinaction.bindy").process(new Processor() {
                    public void process(Exchange exchange) throws Exception {
                        System.out.println("output is   ......" +exchange.getIn().getBody().toString());
                    }
                })
                        .to("mock:result");
            }
        };
    }
}