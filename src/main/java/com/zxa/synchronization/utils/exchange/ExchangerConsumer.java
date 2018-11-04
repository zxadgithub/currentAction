package com.zxa.synchronization.utils.exchange;

import java.util.List;
import java.util.concurrent.Exchanger;

/**
 * @ClassName: ExchangerConsumer
 * @Description: //消费者
 * @Author: zhangxin_an
 * @CreateDate: 2018/11/4 20:25
 */
public class ExchangerConsumer<T> implements Runnable {
	private Exchanger<List<T>> exchanger;
	private List<T> holder;
	private volatile T value;

	public ExchangerConsumer(Exchanger<List<T>> exchanger, List<T> holder){
		this.exchanger = exchanger;
		this.holder = holder;
	}

	public void run() {
		try {
			while(!Thread.interrupted()){
				holder = exchanger.exchange(holder);
				System.out.println("消费者：" + holder.size());
			}
			for(T x : holder){
				value = x;

				holder.remove(x);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Final value: " + value);
	}
}
