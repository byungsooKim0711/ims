package org.kimbs.ims.api.kakao.route.filter;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;

import java.util.function.Function;

@Component
public class BeforeRouteFilter implements Function<ServerRequest, ServerRequest> {

    @Override
    public ServerRequest apply(ServerRequest serverRequest) {
        return serverRequest;
    }
}