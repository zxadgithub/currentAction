## Java并发编程实战代码目录
### 并发同步工具类(com.zxa.synchronization.utils)
[博客链接](https://blog.csdn.net/zxadcsdn/article/details/83691288)
* 闭锁（CountDown) => TestCountDown
* 栅栏（CyclicBarrier) =>（Horse,HorseRace）
*  信号量 => TestSemaphore
* 栅栏（Exchanger) => com.zxa.synchronization.utils.exchange

### 构建高效可伸缩的结果缓存
[博客链接](https://blog.csdn.net/zxadcsdn/article/details/83743227)

1、通过HashMap实现缓存 （com.zxa.cache.Memoizer1）

2、通过ConcurrentHashMap实现缓存（com.zxa.cache.Memoizer2）

3、ConcurrentHashMap基础上基于FutureTask实现（com.zxa.cache.Memoizer3）

4、优化Map的put操作（com.zxa.cache.Memoizer)

#### 并行性（Callable,Future,CompletionService(Executor && BlockingQueue)）
1、Callable和Future(com.zxa.parallel.FutureRenderer)