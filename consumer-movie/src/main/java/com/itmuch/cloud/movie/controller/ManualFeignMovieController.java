package com.itmuch.cloud.movie.controller;

import com.itmuch.cloud.movie.service.ManualFeignClient;
import com.itmuch.cloud.user.entity.User;
import feign.Client;
import feign.Contract;
import feign.Feign;
import feign.auth.BasicAuthRequestInterceptor;
import feign.codec.Decoder;
import feign.codec.Encoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.feign.FeignClientsConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auth wn
 * @Date 2019/2/26
 */
@Import(FeignClientsConfiguration.class)
@RestController
public class ManualFeignMovieController {

    private ManualFeignClient adminFeignClient;

    @Autowired
    public ManualFeignMovieController(Decoder decoder, Encoder encoder, Client client, Contract contract) {
        this.adminFeignClient = Feign.builder().client(client).encoder(encoder).decoder(decoder).contract(contract)
        .requestInterceptor(new BasicAuthRequestInterceptor("admin","admin"))
                .target(ManualFeignClient.class, "http://provider-user/");
    }

    @GetMapping("/user/manualFeign/{id}")
    public User findById(@PathVariable Long id) {
        return this.adminFeignClient.findById(id);
    }
}
