package com.zxa.cache;

import java.math.BigInteger;

/**
 * @ClassName: ExpensiceFunction
 * @Description: //获取数据的过程
 * @Author: zhangxin_an
 * @CreateDate: 2018/11/4 21:42
 */
public class ExpensiveFunction implements Computable<String, BigInteger> {
	@Override
	public BigInteger compute(String arg) throws InterruptedException {
		return new BigInteger(arg);
	}
}
