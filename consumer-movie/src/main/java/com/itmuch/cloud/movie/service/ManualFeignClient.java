package com.itmuch.cloud.movie.service;

import com.itmuch.cloud.user.entity.User;
import feign.Param;
import feign.RequestLine;

/**
 * @Auth wn
 * @Date 2019/2/26
 */
public interface ManualFeignClient {

    @RequestLine("GET /{id}")
    public User findById(@Param("id") Long id);
}
