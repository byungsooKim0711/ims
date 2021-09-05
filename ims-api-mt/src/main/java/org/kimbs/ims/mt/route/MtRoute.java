package org.kimbs.ims.mt.route;

import org.kimbs.ims.protocol.v1.mt.ImsMtReq;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class MtRoute {

    @Bean
    public RouterFunction<ServerResponse> mtMessageRoute(MtRouteHandler handler) {
        return RouterFunctions.route()
                .POST("/mt/sendMessage", request -> handler.sendMessage(request.bodyToMono(ImsMtReq.class)))
                .build();
    }
}
