package com.rmg.practice;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.file.FileComponent;
import org.apache.camel.converter.jaxb.JaxbDataFormat;
import org.apache.camel.impl.DefaultCamelContext;

/**
 * Created by skumar76 on 6/21/2016.
 */
public class Application {

    public static void main(String[] args) throws Exception {

    CamelContext context = new DefaultCamelContext();
              final Enricher enrich = new Enricher();


      //  final com.rmg.practice.Purchase purc = new com.rmg.practice.Purchase();


//        final DataFormat jaxb = new JaxbDataFormat();
//        final JAXBContext jaxb = JAXBContext.newInstance(com.rmg.practice.Purchase.class);
        //final JaxbDataFormat jaxb = new JaxbDataFormat();
        //jaxb.setContextPath("com.rmg.practice");

        Purchase order = new Purchase();
        order.setAmount(1);
        order.setPrice("50");
        order.setName("Camel in Action");
        ProducerTemplate template = context.createProducerTemplate();
        template.sendBody("file:src/test/inbox/bindy", order);

        final OrderToCsvProcessor orderProcess = new OrderToCsvProcessor();
         RouteBuilder route = new RouteBuilder() {
            public void configure() throws Exception {


                /*from("quartz://report?fireNow=true&trigger.repeatCount=0")*/

                     /*   .process(new Processor() {
                    public void process(Exchange exchange) throws Exception {
                        Purchase purc = new Purchase(2 , "camelbook" , "23");
                        exchange.getIn().setBody(purc);
                        System.out.println("genearted xml ");
                    }
                })*/
                        from("file:src/test/inbox/bindy").marshal().jaxb("com.rmg.practice").process(new Processor() {
                    public void process(Exchange exchange) throws Exception {
                        System.out.println("output is   ......" +exchange.getIn().getBody(String.class));
                    }
                })
                .to("file:src/test/outbox");

               /* from("file:src/test/inbox/htmlfile?noop=true")
                        .transform(body().regexReplaceAll("\\s", "<br/>"))
                        .to("file:src/test/outbox");*/
               /* from("quartz://myGroupName/myTimerName?fireNow=true&trigger.repeatCount=0").process(new Processor() {
                    public void process(Exchange exchange) throws Exception {


                        exchange.getIn().setBody("1234567890 AB123456 23062016 2",String.class);
                        exchange.getIn().setHeader("CamelFileName" , "civilwar");

                    }
                })//.process(orderProcess).setHeader(Exchange.FILE_NAME, constant("constantine"))
                        .to("file:src/test/outbox");*/
                        /*.to("file:src/test/outbox?fileName=${date:now:yyyyMMdd}");*/
/*

                from("quartz://myTimer?trigger.repeatInterval=2000&trigger.repeatCount=-1").process(new Processor() {
                    public void process(Exchange exchange) throws Exception {
                        System.out.println("current time is " +exchange.getIn().getHeader("fireTime"));
                    }
                }).to("mock:result ");
*/

                /*from("file:src/test/inbox?noop=true").process(orderProcess).pollEnrich("file:src/test/inbox/htmlfile" , enrich).
                        to("file:src/test/outbox");*/
            }
        };

        try {
            context.addRoutes(route);
            context.start();
            Thread.sleep(5000);
            context.stop();
        } catch (Exception e) {
            e.printStackTrace();
        }


         //com.rmg.practice.Purchase prchs = new com.rmg.practice.Purchase(1 , "camelbook" , "1200");
    /*    ApplicationContext appcontext = new ClassPathXmlApplicationContext("beans.xml");
        CamelContext context = SpringCamelContext.springCamelContext(appcontext);


        context.start();
        Thread.sleep(5000);
        context.stop();*/
    }
}
