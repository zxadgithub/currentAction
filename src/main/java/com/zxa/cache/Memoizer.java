package com.zxa.cache;

import java.util.Map;
import java.util.concurrent.*;

/**
 * @ClassName: Memoizer3
 * @Description: //TODO
 * @Author: zhangxin_an
 * @CreateDate: 2018/11/5 8:12
 */
public class Memoizer<A, V> implements Computable<A, V> {
	private final Map<A, Future<V>> cache = new ConcurrentHashMap<>();
	private final Computable<A, V> computable;

	public Memoizer(Computable<A, V> computable) {
		this.computable = computable;
	}

	@Override
	public V compute(A arg) throws InterruptedException {
		Future<V> futureTask = cache.get(arg);
		if(futureTask == null){
			Callable<V> eval = new Callable<V>() {
				@Override
				public V call() throws Exception {
					return computable.compute(arg);
				}
			};
			FutureTask<V> ft = new FutureTask<>(eval);
			//原子操作保证计算一致性
			futureTask =cache.putIfAbsent(arg, ft);
			if(futureTask == null) {
				futureTask = ft;
				ft.run();
			}
		}
		try{
			return futureTask.get();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		return null;
	}
}
