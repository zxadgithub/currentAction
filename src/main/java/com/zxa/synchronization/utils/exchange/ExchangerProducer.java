package com.zxa.synchronization.utils.exchange;

import com.zxa.utils.Generator;

import java.util.List;
import java.util.concurrent.Exchanger;

/**
 * @ClassName: ExchangerProducer
 * @Description: //生产者
 * @Author: zhangxin_an
 * @CreateDate: 2018/11/4 20:04
 */
public class ExchangerProducer<T> implements Runnable {

	private Generator<T> generator;
	private Exchanger<List<T>> exchanger;
	private List<T> holder;
	ExchangerProducer(Exchanger<List<T>> exchg, Generator<T> generator, List<T> holder){
		exchanger = exchg;
		this.generator = generator;
		this.holder = holder;
	}


	public void run() {
		try {
			while(!Thread.interrupted()){
				for( int i = 0; i < ExchangerDemo.size; i++){
					System.out.println("生产者：" + generator.next());
					holder.add(generator.next());
				}
				holder = exchanger.exchange(holder);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
