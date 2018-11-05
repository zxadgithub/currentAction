package com.zxa.cache;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: Memoizerl
 * @Description: //通过HashMap实现缓存
 * @Author: zhangxin_an
 * @CreateDate: 2018/11/4 21:42
 */
public class Memoizerl<A, V> implements Computable<A, V> {
	private final Map<A, V> cache = new HashMap<>();
	private final Computable<A, V> computable;

	public Memoizerl(Computable<A, V> computable) {
		this.computable = computable;
	}

	@Override
	public synchronized V compute(A arg) throws InterruptedException {
		V result = cache.get(arg);
		if(result == null){
			result = computable.compute(arg);
			cache.put(arg, result);
		}
		return result;
	}
}
