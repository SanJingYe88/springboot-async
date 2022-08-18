package it.com.controller;

import it.com.entity.History;
import it.com.entity.Ticket;
import it.com.entity.User;
import it.com.entity.UserVo;
import it.com.service.UserAsyncService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

@Slf4j
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserAsyncService userAsyncService;

    /**
     * 异步，无结果
     */
    @RequestMapping("start")
    public void start() {
        for (int i = 0; i < 5; i++) {
            log.info("i：{},时间：{}", i, new Date().toString());
            userAsyncService.getUserInfo();
        }
    }

    /**
     * 异步结果返回
     *
     * @throws ExecutionException ExecutionException
     * @throws InterruptedException InterruptedException
     * @return User
     */
    @RequestMapping("start2")
    public User start2() throws ExecutionException, InterruptedException {
        log.info("时间：{}", new Date().toString());
        Future<User> userFuture = userAsyncService.getUserInfo2();
        User user;
        while (true) {
            if (userFuture.isDone()) {
                user = userFuture.get();
                log.info("时间：{}, user：{}", new Date().toString(), user);
                break;
            }
            TimeUnit.SECONDS.sleep(1L);
        }
        return user;
    }

    /**
     * 多个异步结果合并返回
     *
     * @throws ExecutionException ExecutionException
     * @throws InterruptedException InterruptedException
     * @return UserVo
     */
    @RequestMapping("start3")
    public UserVo start3() throws ExecutionException, InterruptedException {
        log.info("开始执行,时间：{}", new Date().toString());
        CompletableFuture<User> userFuture = userAsyncService.getUserInfo3();
        CompletableFuture<List<History>> historyFuture = userAsyncService.getHistory();
        CompletableFuture<List<Ticket>> userTicketFuture = userAsyncService.getUserTicket();
        // 多个任务的耗时，按照最久任务计算
        CompletableFuture.allOf(userFuture, historyFuture, userTicketFuture).join();
        UserVo userVo = new UserVo().setUser(userFuture.get()).setHistoryList(historyFuture.get()).setTicketList(userTicketFuture.get());
        log.info("执行完毕,时间:{}", new Date());
        return userVo;
    }
}
