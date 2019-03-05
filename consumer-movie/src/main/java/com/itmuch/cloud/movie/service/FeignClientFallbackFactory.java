package com.itmuch.cloud.movie.service;

import com.itmuch.cloud.user.entity.User;
import feign.Param;
import feign.hystrix.FallbackFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @Auth wn
 * @Date 2019/2/28
 */
@Component
public class FeignClientFallbackFactory implements FallbackFactory<UserFeignClient> {

    private static final Logger LOGGER = LoggerFactory.getLogger(FeignClientFallbackFactory.class);
    @Override
    public UserFeignClient create(Throwable throwable) {
        return new UserFeignClient() {
            @Override
            public User findById(@Param("id") Long id) {
                LOGGER.error("fallback, reason was:", throwable);
                User user = new User();
                user.setId(-2L);
                user.setName("jimmy");
                return user;
            }
        };
    }
}
