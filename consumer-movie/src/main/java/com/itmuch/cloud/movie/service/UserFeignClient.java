package com.itmuch.cloud.movie.service;

import com.itmuch.cloud.movie.config.FeignLogConfiguration;
import com.itmuch.cloud.user.entity.User;
import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * @Auth wn
 * @Date 2019/2/26
 */
@FeignClient(name = "provider-user", configuration = FeignLogConfiguration.class,
fallbackFactory = FeignClientFallbackFactory.class)
public interface UserFeignClient {

//    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
//    public User findById(@PathVariable("id") Long id);

    @RequestLine("GET /{id}")
    public User findById(@Param("id") Long id);

}
