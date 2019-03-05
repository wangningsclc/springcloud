package com.itmuch.cloud.zuul.service;

import com.itmuch.cloud.user.entity.User;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import rx.Observable;


/**
 * @Auth wn
 * @Date 2019/3/4
 */

@Service
public class AggregationService {

    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "fallback")
    public Observable<User> getUserById(Long id) {
        return Observable.create(observer -> {
            User user = restTemplate.getForObject("http://provider-user/" + id, User.class);
            observer.onNext(user);
            observer.onCompleted();
        });
    }

    @HystrixCommand(fallbackMethod = "fallback")
    public Observable<User> getMovieUserById(Long id) {
        return Observable.create(observer -> {
            User user = restTemplate.getForObject("http://consumer-movie/user/feign/" + id, User.class);
            observer.onNext(user);
            observer.onCompleted();
        });
    }

    public User fallback(Long id) {
        User user = new User();
        user.setId(-1L);
        return user;
    }
}
