package com.zxa.synchronization.utils.exchange;

import com.zxa.bean.Fat;
import com.zxa.utils.BasicGenerator;

import java.util.List;
import java.util.concurrent.*;

/**
 * @ClassName: ExchangerDemo
 * @Description: //Exchanger示例
 * @Author: zhangxin_an
 * @CreateDate: 2018/11/4 20:24
 */
public class ExchangerDemo {
	static int size = 10;
	static int delay = 5;

	public static void main(String[] args) {
		ExecutorService executorService = Executors.newCachedThreadPool();
		Exchanger<List<Fat>> xc = new Exchanger<List<Fat>>();
		List<Fat> productList = new CopyOnWriteArrayList<Fat>();
		List<Fat> consumerList = new CopyOnWriteArrayList<Fat>();
		executorService.execute(new ExchangerProducer<Fat>(xc, BasicGenerator.create(Fat.class), productList));
		executorService.execute(new ExchangerConsumer<Fat>(xc, consumerList));
		try {
			TimeUnit.SECONDS.sleep(delay);
			executorService.shutdownNow();

			System.out.println(productList.size());
			System.out.println(consumerList.size() + "consumer" );

		} catch (InterruptedException e) {
			e.printStackTrace();
		}


	}


}
