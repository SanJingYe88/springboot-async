package it.com.service;

import it.com.entity.History;
import it.com.entity.Ticket;
import it.com.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 *  * 异步方法的服务
 *  
 */
@Slf4j
@Service
public class UserAsyncService {

    /**
     * 无返回值异步
     */
    @Async("myTaskExecutor")  // 使用指定线程池
    public void getUserInfo() {
        String name = Thread.currentThread().getName();
        try {
            TimeUnit.SECONDS.sleep(5L);
        } catch (InterruptedException e) {
            log.info("线程: {}, 时间：{}，异常：{}", name, new Date().toString(), e.getMessage());
        }
        log.info("线程: {}, 时间：{}", name, new Date().toString());
    }

    /**
     * 有返回值异步
     *
     * @return Future
     */
    @Async("myTaskExecutor")  // 使用指定线程池
    public Future<User> getUserInfo2() {
        int id = (int) Thread.currentThread().getId();
        String name = Thread.currentThread().getName();
        try {
            TimeUnit.SECONDS.sleep(50L);
        } catch (InterruptedException e) {
            log.info("线程: {}, 时间：{}，异常：{}", name, new Date().toString(), e.getMessage());
        }
        log.info("线程: {}, 时间：{}", name, new Date().toString());
        User user = new User();
        user.setId(id).setName(name);
        return new AsyncResult<>(user);
    }

    /**
     * 有返回值异步
     *
     * @return CompletableFuture
     */
    @Async("myTaskExecutor")  // 使用指定线程池
    public CompletableFuture<User> getUserInfo3() {
        int id = (int) Thread.currentThread().getId();
        String name = Thread.currentThread().getName();
        try {
            TimeUnit.SECONDS.sleep(2L);
        } catch (InterruptedException e) {
            log.info("线程: {}, 时间：{}，异常：{}", name, new Date().toString(), e.getMessage());
        }
        User user = new User();
        user.setId(id).setName(name);
        log.info("getUserInfo3执行完毕, 线程: {}, 时间：{}", name, new Date().toString());
        return CompletableFuture.completedFuture(user);
    }

    /**
     * 有返回值异步
     *
     * @return CompletableFuture
     */
    @Async("myTaskExecutor")  // 使用指定线程池
    public CompletableFuture<List<History>> getHistory() {
        String name = Thread.currentThread().getName();
        try {
            TimeUnit.SECONDS.sleep(3L);
        } catch (InterruptedException e) {
            log.info("线程: {}, 时间：{}，异常：{}", name, new Date().toString(), e.getMessage());
        }
        List<History> list = new ArrayList<>();
        History history1 = new History().setTitle("xxxx1").setDate(new Date());
        History history2 = new History().setTitle("xxxx2").setDate(new Date());
        History history3 = new History().setTitle("xxxx3").setDate(new Date());
        list.add(history1);
        list.add(history2);
        list.add(history3);
        log.info("getHistory执行完毕, 线程: {}, 时间：{}", name, new Date().toString());
        return CompletableFuture.completedFuture(list);
    }

    /**
     * 有返回值异步
     *
     * @return CompletableFuture
     */
    @Async("myTaskExecutor")  // 使用指定线程池
    public CompletableFuture<List<Ticket>> getUserTicket() {
        String name = Thread.currentThread().getName();
        try {
            TimeUnit.SECONDS.sleep(5L);
        } catch (InterruptedException e) {
            log.info("线程: {}, 时间：{}，异常：{}", name, new Date().toString(), e.getMessage());
        }
        Ticket ticket1 = new Ticket().setId(10001).setDescription("这是卡券xxx1");
        Ticket ticket2 = new Ticket().setId(10002).setDescription("这是卡券xxx2");
        Ticket ticket3 = new Ticket().setId(10003).setDescription("这是卡券xxx3");
        List<Ticket> list = new ArrayList<>();
        list.add(ticket1);
        list.add(ticket2);
        list.add(ticket3);
        log.info("getUserTicket执行完毕, 线程: {}, 时间：{}", name, new Date().toString());
        return CompletableFuture.completedFuture(list);
    }
}
