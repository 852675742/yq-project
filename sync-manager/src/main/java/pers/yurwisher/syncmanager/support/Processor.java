package pers.yurwisher.syncmanager.support;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author yq
 * @date 2019/01/12 09:51
 * @description 执行器
 * @since V1.0.0
 */
public class Processor<T> {

    private static final Logger logger = LoggerFactory.getLogger(Processor.class);
    /**
     * 执行器编号
     */
    private Integer processNumber;
    /**
     * 队列
     */
    private LinkedBlockingQueue<MessageExecute<T>> queue;
    /**
     * 队列大小
     */
    private Integer queueSize;
    /**
     * 执行线程名称前缀
     */
    private String processThreadNamePrefix ;
    /**
     * 重试次数
     */
    private Integer retryNumber;
    /**
     * 获取消息执行service
     */
    private IProcessService<T> processService ;
    /**
     * 消息处理次数超过阀值后的处理
     */
    private IProcessExceptionHandle<T> processExceptionHandle;

    public Processor(Integer processNumber, Integer queueSize, String processThreadNamePrefix, Integer retryNumber, IProcessService<T> processService,IProcessExceptionHandle<T> processExceptionHandle) {
        this.processNumber = processNumber;
        this.queueSize = queueSize;
        this.processThreadNamePrefix = processThreadNamePrefix;
        this.retryNumber = retryNumber;
        this.processService = processService;
        this.processExceptionHandle = processExceptionHandle;
        this.queue = new LinkedBlockingQueue<>(queueSize);
        execute();
    }

    public boolean put(MessageExecute<T> execute){
        return queue.offer(execute);
    }

    public Integer getProcessNumber() {
        return processNumber;
    }

    public LinkedBlockingQueue<MessageExecute<T>> getQueue() {
        return queue;
    }

    public void execute() {
        //初始化一个线程去监控此队列
        ExecutorService executorService = new ThreadPoolExecutor(
                //核心线程,最大线程
                1, 1,
                //回收空闲线程时间
                0L, TimeUnit.MILLISECONDS,
                //阻塞队列
                new LinkedBlockingQueue<>(queueSize),
                (Runnable r)->{
                    Thread thread = new Thread(r);
                    thread.setName(processThreadNamePrefix + "-" + processNumber);
                    //守护线程
                    thread.setDaemon(true);
                    return thread;
                },
                new ThreadPoolExecutor.AbortPolicy());
        executorService.execute(() -> {
            while (true) {
                MessageExecute<T> record = null;
                try {
                    record = queue.take();
                } catch (InterruptedException e) {
                    logger.error("线程异常", e);
                }
                if (record != null) {
                    try {
                        //执行次数++
                        record.setInvokeCount(record.getInvokeCount() + 1) ;
                        processService.process(record);
                    } catch (Exception e) {
                        logger.error("执行异常",e);
                        //达到重试次数 退出重试
                        if(record.getInvokeCount() == retryNumber){
                            processExceptionHandle.handle(record,e);
                        }else {
                            //异常 丢回去接着处理
                            queue.offer(record);
                        }
                    }
                }
            }
        });
    }
}
