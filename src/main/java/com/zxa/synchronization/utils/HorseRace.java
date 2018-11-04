package com.zxa.synchronization.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName: HorseRace
 * @Description: //TODO
 * @Author: zhangxin_an
 * @CreateDate: 2018/11/4 17:08
 */
public class HorseRace {
	static final int FINISH_LINE = 75;
	private List<Horse> horses = new ArrayList<Horse>();
	private ExecutorService executorService = Executors.newCachedThreadPool();
	private CyclicBarrier barrier;
	public HorseRace(int nHorse, final int pause){
		barrier = new CyclicBarrier(nHorse, new Runnable() {
			public void run() {
				StringBuilder stringBuilder = new StringBuilder();
				for( int i = 0; i < FINISH_LINE; i++){
					stringBuilder.append("=");
				}
				System.out.println(stringBuilder);
				for( Horse horse : horses){
					System.out.println(horse.tracks());
				}

				for(Horse horse : horses){
					if(horse.getStrides() >= FINISH_LINE){
						System.out.println(horse + "WON!");
						executorService.shutdownNow();
						return;
					}
				}

				try{
					TimeUnit.MILLISECONDS.sleep(pause);
				} catch (InterruptedException e) {
					System.out.println("barrier-action sleep interrupted");
				}


			}
		});

		for( int i = 0; i < nHorse; i++){
			Horse horse = new Horse(barrier);
			horses.add(horse);
			executorService.execute(horse);
		}



	}

	public static void main(String[] args) {
		int nHorse = 7;
		int pause = 100;
		new HorseRace(nHorse, pause);
	};








}
