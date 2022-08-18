package it.com.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
  * 异步方法的服务
  */
@Slf4j
@Service
public class UserAsyncService {

    @Async("myTaskExecutor")  // 使用指定线程池
    public void getUserInfo() {
        String name = Thread.currentThread().getName();
        try {
            TimeUnit.SECONDS.sleep(5L);
        } catch (InterruptedException e) {
            log.info("线程: {}, 时间：{}，异常：{}", name, new Date().toString(),e.getMessage());
        }
        log.info("线程: {}, 时间：{}", name, new Date().toString());
    }
}
