package com.zjjzfy.common.annotation;

import java.lang.annotation.*;

/**
 * @author zyx
 * @date 2020/3/3 下午11:34
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface PagehelpService {

    String pageNoName() default "pageNo";
    int pageNo() default 1;
    String pageSizeName() default "pageSize";
    int pageSize() default 10;

}
