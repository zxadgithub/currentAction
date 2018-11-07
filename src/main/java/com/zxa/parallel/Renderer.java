package com.zxa.parallel;

import com.sun.scenario.effect.ImageData;

import java.util.List;
import java.util.concurrent.*;

/**
 * @ClassName: Renderer
 * @Description: //CompletionService实现一组任务并行获取结果
 * @Author: zhangxin_an
 * @CreateDate: 2018/11/7 20:55
 */
public class Renderer {
	private final ExecutorService executorService;

	Renderer(ExecutorService executorService){
		this.executorService = executorService;
	}

	void renderPage(CharSequence source){
		List<ImageInfo> infos = scanForImageInfo(source);

		//构造函数传入Executor，委托其执行任务
		CompletionService<ImageData> completionService = new ExecutorCompletionService<>(executorService);
		for(final ImageInfo imageInfo : infos){
			completionService.submit(new Callable<ImageData>() {
				@Override
				public ImageData call() throws Exception {
					return imageInfo.downloadImage();
				}
			});
		}
		renderText(source);

		try {
		for(int t = 0, n = infos.size(); t < n; t++ ){
				Future<ImageData> future = completionService.take();
				ImageData imageData = future.get();
				renderImage(imageData);
		}
	} catch (InterruptedException e) {
		e.printStackTrace();
	} catch (ExecutionException e) {
			e.printStackTrace();
		}

	}

	private void renderImage(ImageData imageData) {
	}

	private void renderText(CharSequence source) {
	}

	private List<ImageInfo> scanForImageInfo(CharSequence source) {
		return null;
	}

}
