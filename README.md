## 爱学啊 小米有品商城项目 demo
- [课程链接](https://ixuea.com/courses/93)

### 配置相关
1. 自定义主题颜色，实现[自定义 key](https://github.com/LeiYao123/BasicCompose/blob/master/app/src/main/java/com/compose/ui/theme/Theme.kt) 覆盖 material design 默认 color schema
2. 图片亮暗模式适配：在 res 目录下新建 drawable-night-xxhdpi/drawable-xxhdpi 目录，分别放亮和暗模式的同名图片


### 单个技术点
1. 实现倒计时功能
    ```kotlin
    // 第一种方案利用 CountDownTimer
    
    // object : 语法，继承接口或抽象类 (CountDownTimer 为一个抽象类) 
    val cdt = object : CountDownTimer(10000, 1000) {
        override fun onTick(millisUntilFinished: Long) {
            timeLeft = millisUntilFinished.toInt() / 1000
            Log.d("Route", "timeLeft: $timeLeft")
        }
        override fun onFinish() {}
    }
    Log.d("Route", "前--执行了 cdt: $cdt")
    cdt.start()
    Log.d("Route", "后--执行了 cdt: $cdt")
    delay(5000)
    cdt.cancel()
    Log.d("Route", "cancel 结束 cdt: $cdt")
   
   
    // 第二种方案利用 delay （暂停当前协程不会阻塞主线程 UI 线程），只能在协程当中使用 
    // Thread.sleep 会阻塞 UI 线程
     while (timeLeft > 0) {
        delay(1000)
        // 执行逻辑，在 while 里面相当于开启一个死循环
    }
   
    // 或者协程实现 倒计时
    private var countdownJob: Job? = null
    fun startCountDown() {
        countdownJob = viewModelScope.launch {
            repeat(10) {
                delay(1000)
            }
        }
    }
    // 取消倒计时，取消协程任务 
    ```