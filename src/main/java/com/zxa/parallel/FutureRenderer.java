package com.zxa.parallel;

import com.sun.scenario.effect.ImageData;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @ClassName: parallel
 * @Description: //Callable和Future实现并行页面渲染
 * @Author: zhangxin_an
 * @CreateDate: 2018/11/7 20:05
 */
public class FutureRenderer {
	private final ExecutorService executorService = Executors.newCachedThreadPool();

	/**
	 * @description 加载文本与加载图像并行操作，先渲染文本，在根据加载图形的线程返回值渲染图像
	 * @method  renderPage
	 * @params  [source]
	 * @return void
	 * @date: 2018/11/7 20:16
	 * @author:zhangxin_an
	 */
	void renderPage(CharSequence source){
		//获取图像信息（包括url）
		final List<ImageInfo> imageInfos = scanForInageInfo(source);
		Callable<List<ImageData>> task = new Callable<List<ImageData>>() {
			@Override
			public List<ImageData> call() throws Exception {
				List<ImageData> result = new ArrayList<>();
				for (ImageInfo imageInfo : imageInfos){
					//下载图片到本地
					result.add(imageInfo.downloadImage());
				}
				return result;
			}
		};
		Future<List<ImageData>> future = executorService.submit(task);
		//渲染文本
		renderText(source);

		try{
			List<ImageData> imageData = future.get();
			//执行渲染图像操作...TODO
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			future.cancel(true);
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}


	}

	private void renderText(CharSequence source) {
	}


	private List<ImageInfo> scanForInageInfo(CharSequence source) {
		return null;
	}

}
