package com.zxa.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName: Memoizer2
 * @Description: //通过ConcurrentHashMap实现缓存
 * @Author: zhangxin_an
 * @CreateDate: 2018/11/4 21:59
 */
public class Memoizer2<A, V> implements Computable<A, V> {

	private final Map<A, V> cache = new ConcurrentHashMap<>();
	private final Computable<A, V> computable;

	public Memoizer2(Computable<A, V> computable) {
		this.computable = computable;
	}

	@Override
	public V compute(A arg) throws InterruptedException {
		V result = cache.get(arg);
		if(result == null){
			result = computable.compute(arg);
			cache.put(arg, result);
		}
		return result;
	}
}
