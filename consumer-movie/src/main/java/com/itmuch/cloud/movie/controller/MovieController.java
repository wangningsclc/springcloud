package com.itmuch.cloud.movie.controller;

import com.itmuch.cloud.movie.service.UserFeignClient;
import com.itmuch.cloud.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @Auth wn
 * @Date 2019/2/25
 */
@RestController
public class MovieController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private LoadBalancerClient loanBalanceClient;

    @Autowired
    private UserFeignClient userFeignClient;

    @GetMapping("/user/{id}")
    public User findById(@PathVariable Long id) {
        return this.restTemplate.getForObject("http://provider-user/" + id, User.class);
    }

    @GetMapping("/choose-instance")
    public ServiceInstance logUserInstance(){
        return this.loanBalanceClient.choose("provider-user");
    }

    @GetMapping("/user/feign/{id}")
    /*
    未用FeignClientFallbackFactory情况下可以使用以下注解做断路器
    @HystrixCommand(fallbackMethod = "findByIdFallback", commandProperties = {

            @HystrixProperty(name= "execution.isolation.thread.timeoutInMilliseconds", value = "5000"),
            @HystrixProperty(name= "metrics.rollingState.timeInMilliseconds", value = "10000")
    }, threadPoolProperties = {
            @HystrixProperty(name= "coreSize", value = "1"),
            @HystrixProperty(name= "maxQueueSize", value = "10")
    })
    */
    public User findByIdViaFeign(@PathVariable Long id) {
        return this.userFeignClient.findById(id);
    }

    public User findByIdFallback(Long id) {
        User user = new User();
        user.setId(-1L);
        user.setName("tony");
        return user;
    }
}
