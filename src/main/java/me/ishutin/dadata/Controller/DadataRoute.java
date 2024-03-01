package me.ishutin.dadata.Controller;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DadataRoute extends RouteBuilder {

    private final CamelContext camelContext;

    @Autowired
    public DadataRoute(CamelContext camelContext) {
        this.camelContext = camelContext;
    }

    @Override
    public void configure() throws Exception {
        from("direct:dadataRoute")
                .routeId("dadataRoute")
                .log("Received message on direct:dadataRoute")
                .to("dadata://suggestAddress?apiKey=915fd9ffd2ee0354d07be85a1bab3ca5fd060c6b&kladrId=7dfa745e-aa19-4688-b121-b655c11e482f")
                .to("log:output?showAll=true")
                .to("direct:result");
    }
}


