package com.hand.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.service.Tag;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * Swagger Api 描述配置
 */
@Configuration
public class SwaggerTags {

    public static final String EXAMPLE = "Example";
    public static final String COMPANY = "COMPANY";
    public static final String CUSTOMER = "CUSTOMER";
    public static final String ITEM = "ITEM";
    public static final String SOHEADER = "SOHEADER";
    public static final String SOLINE = "SOLINE";
    public static final String INTERFACE_QUERY = "INTERFACE_QUERY";
    public static final String HAND_MESSAGE = "HAND_MESSAGE";

    @Autowired
    public SwaggerTags(Docket docket) {
        docket.tags(
                new Tag(EXAMPLE, "EXAMPLE 案例"),
                new Tag(COMPANY, "COMPANY 案例"),
                new Tag(CUSTOMER, "CUSTOMER 案例"),
                new Tag(ITEM, "ITEM 案例"),
                new Tag(SOHEADER, "SOHEADER 案例"),
                new Tag(SOLINE, "SOLINE 案例"),
                new Tag(INTERFACE_QUERY, "INTERFACE_QUERY 案例"),
                new Tag(HAND_MESSAGE, "HAND_MESSAGE 案例")

        );
    }
}
