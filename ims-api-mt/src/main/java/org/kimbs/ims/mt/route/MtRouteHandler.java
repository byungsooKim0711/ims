package org.kimbs.ims.mt.route;

import lombok.extern.slf4j.Slf4j;
import org.kimbs.ims.protocol.v1.mt.ImsMtReq;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class MtRouteHandler {

    public Mono<ServerResponse> sendMessage(ServerRequest request) {
        return request.bodyToMono(ImsMtReq.class)
                .map(req -> req)
                .flatMap(req -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue("kbstest"))
                .onErrorResume(this::errorTest);
    }

    public Mono<ServerResponse> errorTest(Throwable e) {

        if (e instanceof RuntimeException) {
            return ServerResponse.ok().bodyValue("asdfasdf");
        }

        return ServerResponse.ok().bodyValue("123123123");
    }
}
