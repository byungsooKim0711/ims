package org.kimbs.ims.channel.kakao.service;

public abstract class AbstractMessageService<M> {

    public void sendMessage(M message) {

        request(message);
        report(message);
        log(message);
    }

    protected abstract void request(M message);
    protected abstract void report(M message);
    protected abstract void log(M message);
}
