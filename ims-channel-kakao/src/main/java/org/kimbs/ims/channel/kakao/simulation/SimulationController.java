package org.kimbs.ims.channel.kakao.simulation;

import org.kimbs.ims.model.kakao.*;
import org.kimbs.ims.protocol.code.BizReportCode;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ThreadLocalRandom;

@ConditionalOnExpression(value = "${ims.channel.kakao.simulation}")
@RestController
public class SimulationController {

    private static final BizReportCode[] randomReportCode = {
            BizReportCode.SUCCESS,
            BizReportCode.SUCCESS,
            BizReportCode.SUCCESS,
            BizReportCode.SUCCESS,
            BizReportCode.SUCCESS,
            BizReportCode.KAKAO_ERROR2,
            BizReportCode.NO_SEND_AVAILABLE_EXCEPTION,
            BizReportCode.NO_SEND_AVAILABLE_EXCEPTION,
            BizReportCode.NO_MATCHED_TEMPLATE_BUTTON_EXCEPTION,
            BizReportCode.NO_MATCHED_TEMPLATE_BUTTON_EXCEPTION,
    };

    @PostMapping("/at/sendMessage")
    public AtMessageRes atSimulation(AtMessageReq req) {
        BizReportCode report = randomReport();
        AtMessageRes res = new AtMessageRes();

        switch (report) {
            case KAKAO_ERROR1:
            case KAKAO_ERROR2:
                throw new RuntimeException();
            default:
                res.setCode(report.getCode());
                break;
        }

        return res;
    }

    @PostMapping("/ft/sendMessage")
    public FtMessageRes ftSimulation(FtMessageReq req) {
        BizReportCode report = randomReport();
        FtMessageRes res = new FtMessageRes();

        switch (report) {
            // throw SERVER_ERROR
            case KAKAO_ERROR1:
            case KAKAO_ERROR2:
                throw new RuntimeException();
            default:
                res.setCode(report.getCode());
                break;
        }

        return res;
    }

    @PostMapping("/bt/sendMessage")
    public BtMessageRes btSimulation(BtMessageReq req) {
        BizReportCode report = randomReport();
        BtMessageRes res = new BtMessageRes();

        switch (report) {
            // SERVER_ERROR
            case KAKAO_ERROR1:
            case KAKAO_ERROR2:
                throw new RuntimeException();
            default:
                res.setCode(report.getCode());
                break;
        }

        return res;
    }

    private BizReportCode randomReport() {
        return randomReportCode[ThreadLocalRandom.current().nextInt(randomReportCode.length)];
    }
}
