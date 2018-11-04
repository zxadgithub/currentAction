package com.zxa.synchronization.utils;

import com.zxa.thread.TestRunnable;

import java.util.concurrent.CountDownLatch;

/**
 * @ClassName: TestCountDown
 * @Description: //TODO
 * @Author: zhangxin_an
 * @CreateDate: 2018/11/3 10:37
 */
public class TestCountDown {
	public long timeTasks(int nThreads, final Runnable task){
		final CountDownLatch startGate = new CountDownLatch(1);
		final CountDownLatch endGate = new CountDownLatch(nThreads);
		for( int i = 0; i < nThreads; i++){
			Thread  t = new Thread(i + ""){
				@Override
				public void run(){
					try {
						System.out.println("阻塞等待事件" + Thread.currentThread().getName() );
						startGate.await();
						try {
							task.run();
						}finally {
							endGate.countDown();
						}
					}catch (Exception e){
						e.printStackTrace();
					}
				}
			};
			t.start();
		}

		long start = System.nanoTime();
		System.out.println("主线程执行");
		try {
			Thread.sleep(10);
			//让主线程等一下看事件组会执行吗
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		startGate.countDown();
		System.out.println("等待事件组可以执行");
		try {
			System.out.println("等待事件组未执行完毕");
			endGate.await();
			System.out.println("等待事件组执行完毕");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		long end = System.nanoTime();
		return end - start;


	}


	public static void main(String[] args) {
		TestCountDown testCountDown = new TestCountDown();
		long duration = testCountDown.timeTasks(10, new TestRunnable());
		System.out.println(duration);
	}


}

