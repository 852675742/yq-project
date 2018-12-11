package pers.yurwisher.wechat.mp.api.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pers.yurwisher.wechat.mp.api.WxMessageDuplicateChecker;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author yq
 * @date 2018/07/17 15:20
 * @description 微信消息重复验证(内存) 自动清理,置于内存当中
 * @since V1.0.0
 */
public class DefaultWxMessageDuplicateChecker implements WxMessageDuplicateChecker {

    private static final Logger logger = LoggerFactory.getLogger(DefaultWxMessageDuplicateChecker.class);

    /**
     * 一个消息ID在内存的过期时间
     */
    private final Long timeToLive;

    /**
     * 每隔多少周期检查消息ID是否过期
     */
    private final Long clearPeriod;

    /**
     * 后台清理线程是否已经开启.
     */
    private final AtomicBoolean backgroundProcessStarted = new AtomicBoolean(false);

    /**
     * 消息id->消息时间戳的map.
     */
    private final ConcurrentHashMap<String, Long> msgId2Timestamp = new ConcurrentHashMap<>();


    /**
     * 无参构造方法.
     * <pre>
     * 一个消息ID在内存的过期时间：15秒
     * 每隔多少周期检查消息ID是否过期：5秒
     * </pre>
     */
    public DefaultWxMessageDuplicateChecker() {
        this.timeToLive = 15 * 1000L;
        this.clearPeriod = 5 * 1000L;
    }

    /**
     * 构造方法.
     *
     * @param timeToLive  一个消息ID在内存的过期时间：毫秒
     * @param clearPeriod 每隔多少周期检查消息ID是否过期：毫秒
     */
    public DefaultWxMessageDuplicateChecker(Long timeToLive, Long clearPeriod) {
        this.timeToLive = timeToLive;
        this.clearPeriod = clearPeriod;
    }

    /**
     * 开启线程定时清理过期消息
     */
    protected void checkBackgroundProcessStarted() {
        //多线程情况下保证只初始化一个线程
        if (this.backgroundProcessStarted.getAndSet(true)) {
            return;
        }
//        ExecutorService singleThreadPool = createSingleClearThreadPool();
//        singleThreadPool.execute(() -> {
//            while (true) {
//                try {
//                    Thread.sleep(DefaultWxMessageDuplicateChecker.this.clearPeriod);
//                    Long now = System.currentTimeMillis();
//                    msgId2Timestamp.entrySet().stream().filter(
//                            //已经超过过期时间的数据
//                            entry -> {
//                                boolean b = now - entry.getValue() > DefaultWxMessageDuplicateChecker.this.timeToLive;
//                                if (b) {
//                                    logger.info(entry.getKey() + "已过期");
//                                }
//                                return b;
//                            }
//                    ).forEach(entry -> msgId2Timestamp.remove(entry.getKey()));
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
        //定时任务
        ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor(
                //自定义线程
                ((Runnable r) ->{
                    Thread thread = new Thread(r);
                    thread.setName("wx-message-clear");
                    //守护线程
                    thread.setDaemon(true);
                    return thread;
                }));
        service.scheduleAtFixedRate(()->{
                Long now = System.currentTimeMillis();
                msgId2Timestamp.entrySet().stream().filter(
                        //已经超过过期时间的数据
                        entry -> {
                            boolean b = now - entry.getValue() > DefaultWxMessageDuplicateChecker.this.timeToLive;
                            if (b) {
                                logger.info(entry.getKey() + "已过期");
                            }
                            return b;
                        }
                ).forEach(entry -> msgId2Timestamp.remove(entry.getKey()));
        }, 0 , clearPeriod, TimeUnit.MILLISECONDS);

    }

    @Override
    public boolean isDuplicate(String messageId) {
        if(messageId == null){
            return false ;
        }
        checkBackgroundProcessStarted();
        Long timestamp = this.msgId2Timestamp.putIfAbsent(messageId, System.currentTimeMillis());
        return timestamp != null;
    }

    /**
     * 初始化一个清理消息线程池
     */
    public ExecutorService createSingleClearThreadPool() {
        //创建一个单线程池
        ExecutorService singleThreadPool = new ThreadPoolExecutor(
                //核心线程,最大线程
                1, 1,
                //回收空闲线程事件
                0L, TimeUnit.MILLISECONDS,
                //阻塞队列
                new LinkedBlockingQueue<>(1024),
                //自定义线程
                ((Runnable r) ->{
                    Thread thread = new Thread(r);
                    thread.setName("wx-message-clear");
                    //守护线程
                    thread.setDaemon(true);
                    return thread;
                }),
                /**ThreadPoolExecutor.AbortPolicy()：被拒绝后抛出RejectedExecutionException异常
                 ThreadPoolExecutor.CallerRunsPolicy()：被拒绝后给调用线程池的线程处理
                 ThreadPoolExecutor.DiscardOldestPolicy()：被拒绝后放弃队列中最旧的未处理的任务
                 ThreadPoolExecutor.DiscardPolicy()：被拒绝后放弃被拒绝的任务(当前新添加的任务)
                 */
                new ThreadPoolExecutor.AbortPolicy());
        logger.info("初始化清理消息线程");
        return singleThreadPool;
    }
}
