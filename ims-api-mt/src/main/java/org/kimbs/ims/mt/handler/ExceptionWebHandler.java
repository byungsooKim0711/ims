package org.kimbs.ims.mt.handler;

import org.springframework.boot.autoconfigure.web.reactive.error.AbstractErrorWebExceptionHandler;
import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

//@Component
//@Order(-2)
//public class ExceptionWebHandler extends AbstractErrorWebExceptionHandler {
//
//    public ExceptionWebHandler(@Autowired ErrorAttributes errorAttributes,
//                               ApplicationContext applicationContext,
//                               ServerCodecConfigurer serverCodecConfigurer) {
//        super(errorAttributes, new ResourceProperties(), applicationContext);
//        super.setMessageWriters(serverCodecConfigurer.getWriters());
//        super.setMessageReaders(serverCodecConfigurer.getReaders());
//    }
//
//    @Override
//    protected RouterFunction<ServerResponse> getRoutingFunction(ErrorAttributes errorAttributes) {
//        return RouterFunctions.route(RequestPredicates.all(), this::renderErrorResponse);
//    }
//
//    private Mono<ServerResponse> renderErrorResponse(ServerRequest serverRequest) {
//        Map<String,Object> errorAttributes = getErrorAttributes(serverRequest, false);
//        return ServerResponse.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                .contentType(MediaType.APPLICATION_JSON)
//                .body(BodyInserters.fromObject(errorAttributes.get("message")));
//    }
//}