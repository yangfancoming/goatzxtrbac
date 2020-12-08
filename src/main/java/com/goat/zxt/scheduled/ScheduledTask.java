package com.goat.zxt.scheduled;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTask {

    private Logger log = LoggerFactory.getLogger(getClass());


    @Scheduled(fixedDelay = 3000)        //fixedDelay = 5000表示当前方法执行完毕5000ms后，Spring scheduling会再次调用该方法
    public void testinfo() {
        for (int i = 0; i < 1000 * 10; i++) {
            log.info("===info===: 第{}次执行方法", i++);
        }
    }


    @Scheduled(fixedDelay = 3000)
    public void testdebug() {
        for (int i = 0; i < 1000 * 10; i++) {
            log.debug("===debug===: 第{}次执行方法", i++);
        }
    }
}