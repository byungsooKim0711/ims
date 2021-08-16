package org.kimbs.ims.router.consumer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.kimbs.ims.router.service.MtMessageRouter;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class MtMessageConsumer {

    private final MtMessageRouter router;

}
