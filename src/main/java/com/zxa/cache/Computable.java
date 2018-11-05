package com.zxa.cache;

/**
 * @ClassName: Computable
 * @Description: //TODO
 * @Author: zhangxin_an
 * @CreateDate: 2018/11/4 21:40
 */
public interface Computable<A, V> {
	V compute(A arg) throws InterruptedException;
}
