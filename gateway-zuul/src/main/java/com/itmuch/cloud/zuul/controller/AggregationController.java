package com.itmuch.cloud.zuul.controller;

import com.google.common.collect.Maps;
import com.itmuch.cloud.user.entity.User;
import com.itmuch.cloud.zuul.service.AggregationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;
import rx.Observable;
import rx.Observer;

import java.util.HashMap;

/**
 * @Auth wn
 * @Date 2019/3/4
 */
@RestController
public class AggregationController {

    public static final Logger LOGGER = LoggerFactory.getLogger(AggregationController.class);

    @Autowired
    private AggregationService aggregationService;

    @GetMapping("/aggregate/{id}")
    public DeferredResult<HashMap<String, User>> aggregate(@PathVariable Long id) {
        Observable<HashMap<String, User>> result = this.aggregateObservable(id);
        return this.toDeferredResult(result);
    }


    private Observable<HashMap<String, User>> aggregateObservable(Long id) {
        return Observable.zip(
                this.aggregationService.getUserById(id),
                this.aggregationService.getMovieUserById(id),
                (user, movieUser) -> {
                    HashMap<String, User> map = Maps.newHashMap();
                    map.put("user", user);
                    map.put("movieUser", movieUser);
                    return  map;
                }
        );
    }

    private DeferredResult<HashMap<String,User>> toDeferredResult(Observable<HashMap<String, User>> details) {
        DeferredResult<HashMap<String, User>> result = new DeferredResult<>();
        details.subscribe(new Observer<HashMap<String, User>>() {

            @Override
            public void onCompleted() {
                LOGGER.info("完成。。。。");
            }

            @Override
            public void onError(Throwable throwable) {
                LOGGER.error("错误。。。", throwable);
            }

            @Override
            public void onNext(HashMap<String, User> movieDetails) {
                result.setResult(movieDetails);
            }
        });
        return  result;
    }

}
