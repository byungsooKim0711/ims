package org.kimbs.ims.api.kakao.route.filter;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import java.util.function.BiFunction;

@Component
public class AfterRouteFilter implements BiFunction<ServerRequest, ServerResponse, ServerResponse> {

    @Override
    public ServerResponse apply(ServerRequest serverRequest, ServerResponse serverResponse) {
        return serverResponse;
    }
}
