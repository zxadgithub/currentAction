package com.zxa.cache;

import java.util.Map;
import java.util.concurrent.*;

/**
 * @ClassName: Memoizer3
 * @Description: //ConcurrentHashMap基础上基于FutureTask实现
 * @Author: zhangxin_an
 * @CreateDate: 2018/11/5 8:12
 */
public class Memoizer3<A, V> implements Computable<A, V> {
	private final Map<A, Future<V>> cache = new ConcurrentHashMap<>();
	private final Computable<A, V> computable;

	public Memoizer3(Computable<A, V> computable) {
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
			futureTask = ft;
			cache.put(arg, ft);
			ft.run();
		}
		try{
			return futureTask.get();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		return null;
	}
}
