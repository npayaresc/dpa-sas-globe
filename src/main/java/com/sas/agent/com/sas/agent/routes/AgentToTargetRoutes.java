package com.sas.agent.com.sas.agent.routes;


import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class AgentToTargetRoutes extends RouteBuilder {


    @Override
    public void configure() {

        //localhost/A?routingKey=B"

        log.info("About to start route: Kafka Server -> Log ");

        from("kafka:{{consumer.topic}}?brokers={{kafka.host}}:{{kafka.port}}"
                + "&maxPollRecords={{consumer.maxPollRecords}}"
                + "&consumersCount={{consumer.consumersCount}}"
                + "&seekTo={{consumer.seekTo}}"
                + "&groupId={{consumer.group}}")
                .routeId("FromKafka")
                .log("${body}");
        from("rabbitmq://localhost:5672/nico_exchange?routingKey=test&username=guest&password=guest&autoDelete=false&durable=true").transform(method("transformer", "transformJson(${body})")).log("CI Map: ${body}").to("dfESP://localhost:55555/Telekom2/cq1/json?producerDefaultOpcode=eo_INSERT&bodyType=event").routeId("RABBITMQ_to_ESP");


        //from("direct:route1").log("CI Event message: ${body}").transform(method("transformer", "transformJson(${body})")).log("CI Map: ${body}").to("dfESP://192.168.99.100:55555/CaptureDigitalBehavior4/digital_stream/digital_event_src?producerDefaultOpcode=eo_INSERT&bodyType=event").routeId("Converting CI360 Events");

    }


}
