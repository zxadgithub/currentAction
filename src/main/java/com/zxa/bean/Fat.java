package com.zxa.bean;

/**
 * @ClassName: Fat
 * @Description: //高昂的对象（创建费时）
 * @Author: zhangxin_an
 * @CreateDate: 2018/11/4 20:34
 */
public class Fat {
	private volatile double d;
	private static int counter = 0;
	private final int id = counter++;
	public Fat(){
		for( int i = 1; i < 10000; i++){
			d += (Math.PI + Math.E) / (double)i;
		}
	}

	public void operation(){
		System.out.println(this);
	}

	@Override
	public String toString() {
		return "Fat id:" + id;
	}
}
