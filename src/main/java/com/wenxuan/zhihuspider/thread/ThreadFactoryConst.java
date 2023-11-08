package com.wenxuan.zhihuspider.thread;

import java.util.concurrent.ThreadFactory;

/**
 * 线程工厂常量
 * @author 文轩
 */
public class ThreadFactoryConst {

    /**
     * IO密集型线程池
     */
    public static final ThreadFactory IO_THREAD_FACTORY = Thread.ofVirtual()
            .name("ioThreadPool",0)
            .factory();
}
