package com.zxa.synchronization.utils;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Semaphore;

/**
 * @ClassName: TestSmaphore
 * @Description: //TODO
 * @Author: zhangxin_an
 * @CreateDate: 2018/11/4 11:00
 */
public class TestSemaphore {
	private final Set<Object> set;
	private final Semaphore sem;

	public TestSemaphore(int bound) {
		this.set = Collections.synchronizedSet(new HashSet<Object>());
		sem = new Semaphore(bound);

	}

	public boolean add(Object o) throws InterruptedException{
		//首先获得许可
		sem.acquire();

		boolean wasAdded = false;
		try {
			wasAdded = set.add(o);
			return wasAdded;
		}
		finally {
			//如果add操作不成功，立刻释放许可
			if(!wasAdded){
				sem.release();
			}
		}


	}

	public boolean remove(Object o){
		boolean wasRemoved = set.remove(o);

		//删除成功，释放许可
		if(wasRemoved){
			sem.release();
		}
		return wasRemoved;
	}



}
