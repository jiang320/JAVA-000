package week04;


/*
/**
 * 本周作业：（必做）思考有多少种方式，在main函数启动一个新线程或线程池，
 * 异步运行一个方法，拿到这个方法的返回值后，退出主线程？
 * 写出你的方法，越多越好，提交到github。
 *
 * 一个简单的代码参考：
 //https://github.com/mrly202016/JAVA-000/blob/main/Week_04/src/main/java/homework/One.java
 //https://github.com/useaname/JAVA-000/blob/main/Week_04/Homework03.java
 */


import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class week4_homework {
    static int x;
    static  final int DEFAULT_FIBO=20;
    public static void main(String[] args) throws ExecutionException, InterruptedException, BrokenBarrierException {


//        long start=System.currentTimeMillis();
     //1.FutureTask
        LocalTime start= LocalTime.now();
        FutureTask<Integer> task1= new FutureTask<>(new WithCallable());
        new Thread(task1).start();
        printtask("FutureTask",task1.get(),start);

        //Future
        start = LocalTime.now();
        Future<Integer> task2=
                Executors.newCachedThreadPool()
                .submit(()-> new WithCallable().call());
        while(!task2.isDone()){
            System.out.println("task2 wait ");}

        printtask("Future",task2.get(),start);

        //3.CompletableFuture
        final LocalTime startTime= LocalTime.now();

        CompletableFuture
                .supplyAsync(()->new WithCallable().call())
                     .thenAccept(res-> printtask("CompletableFuture",res,startTime));


        //4.CountDownLatch
        start = LocalTime.now();
        CountDownLatch countDownLatch= new CountDownLatch(1);
        new Thread(new WithThread(countDownLatch)).start();
        countDownLatch.await();
        printtask("CountDownLatch",x,start);

        //5.CyclicBarrier
        start= LocalTime.now();
        x=0;
        CyclicBarrier cyclicBarrier =new CyclicBarrier(2);
        Thread task5 =new Thread(()-> {
            x= fibo(DEFAULT_FIBO);
            try {
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        });
        task5.start();
        cyclicBarrier.await();
        printtask("CyclicBarrier",x,start);
         AtomicInteger value = new AtomicInteger();
        Thread thread = new Thread(()->{value.set(sum());});

    }

static class WithCallable implements Callable<Integer>{

    @Override
    public Integer call()  {
        return  fibo(DEFAULT_FIBO);
    }
}

static class WithThread implements  Runnable{
    private final CountDownLatch countDownLatch;

    WithThread(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
         x= fibo(DEFAULT_FIBO);
        countDownLatch.countDown();
    }
}
    static void printtask(String desc, int taskResult, LocalTime startTime){
        if(startTime==null){
            return;
        }
        LocalTime endTime = LocalTime.now();
        long duration= ChronoUnit.MILLIS.between(startTime,endTime);
        System.out.println(
                String.format( "[实现方式: %s], 返回值为: %d, 执行时间为 %d Millis",
                 desc,taskResult,duration)
        );
    }

        private static int sum() {
            return fibo(36);
        }

        private static int fibo(int a) {
            if ( a < 2)
                return 1;
            return fibo(a-1) + fibo(a-2);
        }
}
