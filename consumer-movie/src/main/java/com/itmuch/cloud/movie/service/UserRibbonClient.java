package com.itmuch.cloud.movie.service;

import com.itmuch.cloud.movie.config.RibbonConfiguration;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Configuration;

/**
 * @Auth wn
 * @Date 2019/2/25
 */
@Configuration
@RibbonClient(name = "provider-user", configuration = RibbonConfiguration.class)
public class UserRibbonClient {


}
