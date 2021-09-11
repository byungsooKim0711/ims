package org.kimbs.ims.api.kakao.route;

import lombok.RequiredArgsConstructor;
import org.kimbs.ims.api.kakao.route.filter.AfterRouteFilter;
import org.kimbs.ims.api.kakao.route.filter.BeforeRouteFilter;
import org.kimbs.ims.api.kakao.route.filter.RouteFilter;
import org.kimbs.ims.protocol.v1.kakao.at.ImsBizAtReq;
import org.kimbs.ims.protocol.v1.kakao.bt.ImsBizBtReq;
import org.kimbs.ims.protocol.v1.kakao.ft.ImsBizFtReq;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@RequiredArgsConstructor
@Configuration
public class MessageRoute {

    /**
     * filter 순서:
     * WebFilter
     * HandlerFilterFunction(filter)
     * Function<ServerRequest, ServerRequest>(before)
     * BiFunction<ServerRequest, ServerResponse, ServerResponse>(after)
     */
    private final RouteFilter routeHandlerFilter;
    private final BeforeRouteFilter beforeRouteFilter;
    private final AfterRouteFilter afterRouteFilter;

    /** 알림톡 */
    @Bean
    public RouterFunction<ServerResponse> atMessageRoute(AtRouteHandler handler) {
        return RouterFunctions.route()
                .filter(routeHandlerFilter)
                .before(beforeRouteFilter)
                .POST("/at/sendMessage", request -> handler.sendMessage(request.bodyToMono(ImsBizAtReq.class)))
                .after(afterRouteFilter)
                .build();
    }

    /** 브랜드톡 */
    @Bean
    public RouterFunction<ServerResponse> btMessageRoute(BtRouteHandler handler) {
        return RouterFunctions.route()
                .filter(routeHandlerFilter)
                .before(beforeRouteFilter)
                .POST("/bt/sendMessage", request -> handler.sendMessage(request.bodyToMono(ImsBizBtReq.class)))
                .after(afterRouteFilter)
                .build();
    }

    /** 친구톡 */
    @Bean
    public RouterFunction<ServerResponse> ftMessageRoute(FtRouteHandler handler) {
        return RouterFunctions.route()
                .filter(routeHandlerFilter)
                .before(beforeRouteFilter)
                .POST("/ft/sendMessage", request -> handler.sendMessage(request.bodyToMono(ImsBizFtReq.class)))
                .after(afterRouteFilter)
                .build();
    }

}
