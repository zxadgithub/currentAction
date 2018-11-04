package com.zxa.utils;

/**
 * @ClassName: a
 * @Description: //TODO
 * @Author: zhangxin_an
 * @CreateDate: 2018/11/4 20:45
 */

public class BasicGenerator<T> implements Generator<T> {

	private Class<T> type;

	public BasicGenerator(Class<T> type) {
		this.type = type;
	}

	@Override
	public T next() {
		try {
			return type.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}

	public static <T> Generator<T> create(Class<T> type) {
		return new BasicGenerator<T>(type);
	}



}