package com.zxa.thread;

import com.zxa.bean.Product;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @ClassName: PreLoader
 * @Description: //TODO
 * @Author: zhangxin_an
 * @CreateDate: 2018/11/3 21:26
 */
public class PreLoader {
	private final FutureTask<Product> future = new FutureTask<Product>(new Callable<Product>(){
		public Product call() throws Exception{
			//执行一些耗时任务
			return loadProduct();
		}
	});

	private final Thread thread = new Thread(future);

	public void start(){
		thread.start();
	}

	private Product loadProduct() {
		Product product = new Product();
		//可从数据库或者其他方式获取，耗时任务
		return product;
	}

	public Product get(){
		try {
			return  future.get();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		return null;
	}

}
