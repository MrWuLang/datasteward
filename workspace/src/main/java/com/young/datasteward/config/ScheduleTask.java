package com.young.datasteward.config;

import org.springframework.scheduling.annotation.Scheduled;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @description: 定时任务配置
 * @author: WL
 * @create: 2019-02-25 11:30
 *
 * @Component 使用此注解开启定时
 * @EnableScheduling 配合此注解使用，在程序入口处执行
 **/
/*@Component*/
public class ScheduleTask {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    /**
     * 定时配置，每隔5秒运行一次
     */
    @Scheduled(fixedRate = 5000)
    public void reportCurrentTime() {
        System.out.println("现在时间：" + dateFormat.format(new Date()));
    }

}
