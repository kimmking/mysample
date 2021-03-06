package com.roytrack.mq;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.*;

/**
 * Created by roytrack on 2017-03-28.
 */
public class ThreadPoolManager {

  private final static int CORE_POOL_SIZE = 4;
  private final static int MAX_POOL_SIZE = 10;
  private final static int KEEP_ALIVE_TIME = 0;
  private final static int WORK_QUEUE_SIZE = 10;
  private static ThreadPoolManager tpm = new ThreadPoolManager();
  final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(100);
  final ThreadPoolExecutor threadPool = new ThreadPoolExecutor(CORE_POOL_SIZE, MAX_POOL_SIZE, KEEP_ALIVE_TIME, TimeUnit.SECONDS,
          new ArrayBlockingQueue<Runnable>(WORK_QUEUE_SIZE), this.handler);
  final ScheduledFuture taskHandler = scheduler.scheduleAtFixedRate(accessBufferThread, 0, 1, TimeUnit.MILLISECONDS);
  Queue<String> msgQueue = new LinkedList<>();
  final RejectedExecutionHandler handler = new RejectedExecutionHandler() {
    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
      System.out.println(((AccessDbThread) r).getMsg() + "消息放入队列中重新等待执行");
      msgQueue.offer(((AccessDbThread) r).getMsg());
    }
  };
  final Runnable accessBufferThread = new Runnable() {
    @Override
    public void run() {
      if (hasMoreAcquire()) {
        String msg = msgQueue.poll();
        Runnable task = new AccessDbThread(msg);
        threadPool.execute(task);
      }
    }
  };

  private ThreadPoolManager() {
  }

  public static ThreadPoolManager newInstance() {
    return tpm;
  }

  private boolean hasMoreAcquire() {
    return !msgQueue.isEmpty();
  }

  public void addLogMsg(String msg) {
    Runnable task = new AccessDbThread(msg);
    threadPool.execute(task);
  }


}
