package it.com.controller;

import it.com.service.UserAsyncService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@Slf4j
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserAsyncService userAsyncService;

    @RequestMapping("start")
    public void start() {
        for (int i = 0; i < 5; i++) {
            log.info("i：{},时间：{}", i, new Date().toString());
            userAsyncService.getUserInfo();
        }
    }
}
