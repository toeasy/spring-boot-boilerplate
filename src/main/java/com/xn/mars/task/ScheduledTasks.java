package com.xn.mars.task;

import org.springframework.scheduling.annotation.Scheduled;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 定时任务，实际使用时单独部署，避免并发影响到正常业务的性能
 * author Liang.qinjie
 * on 2017-02-18 22:46
 */

//@Component
public class ScheduledTasks {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    /**
     * 任务
     *
     * @Scheduled(fixedRate = 5000)指定执行间隔,
     * 支持cron表达式，详见http://blog.csdn.net/sd4000784/article/details/7745947
     */
    @Scheduled(fixedRate = 5000)
    public void reportCurrentTime() {
        System.out.println("当前时间：" + dateFormat.format(new Date()));
    }

}
