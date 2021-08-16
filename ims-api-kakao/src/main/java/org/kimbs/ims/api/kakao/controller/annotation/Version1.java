package org.kimbs.ims.api.kakao.controller.annotation;

import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.annotation.*;

/**
 * IMS Api Version-1
 */
@Documented
@Target(value = { ElementType.TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@RequestMapping(value = "/kakao")
public @interface Version1 {
}
