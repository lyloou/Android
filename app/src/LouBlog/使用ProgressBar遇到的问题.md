######【关键词】
`ProgressBar` `自动取消`

######【问题】
给dialog设置了不可取消的属性，如果请求没能得到相应，进度条却无法关闭，程序相当于崩溃，这很影响用户体验；

######【解决办法】
发出任务的时候，post一个定时的“失败的Runnable”；
如果成功响应了，取消进度条，并且取消这个“失败的Runnable”；
如果没能成功响应，则“失败的Runnable”得到了执行，在“失败的Runnable”中取消进度条；

######【原则】
开启了进度条，就一定要关闭它；

######【代码】
```
    private Handler mHandler = new Handler();

    private Runnable mFailedRunnable = new Runnable() {
 
        @Override
        public void run() {
            dismissDialog();
            ToastUtil.toastOnMain(mContext, "Tips: Failed!");
             // TODO 失败的逻辑
        }
    };
 
    private ProgressDialog mDialog = null;
    private void showDialog(String str) {
        if (mDialog == null) {
            mDialog = new ProgressDialog(mContext);
            mDialog.setCancelable(false);
            mDialog.setTitle("Tips:");
        }
        mDialog.setMessage(str);
        if (!mDialog.isShowing()) {
            mDialog.show();
        }
    }
 
    private void dismissDialog() {
        if (mDialog != null && mDialog.isShowing())
            mDialog.dismiss();
    }
```

**发出请求的时候，显示进度条：**
```
showDialog("开始XXX...");
// TODO 发出请求的逻辑；
mHandler.postDelayed(mFailedRunnable, 5000);
```

**结果1：在请求得到相应的时候，关闭进度条，取消失败的Runnable：**
```
dismissDialog();
mHandler.removeCallbacks(mFailedRunnable);
// TODO 响应成功后的操作；
```

**结果2：如果请求没有得到响应，则会执行 mFailedRunnable:**
`mFailedRunnable 中会取消进度条的显示；`

***
**扩展：**
如果请求是连续的*（例如：连接蓝牙有这几个过程：正在找设备 --> 找到了设备 --> 正在连接设备 --> 连接成功 --> 正在配对设备 --> 配对成功后的其他请求）*，则只改变进度条提示文字而不需要关闭进度条：
```
mHandler.removeCallbacks(mFailedRunnable); // 上一个设备得到响应了，则取消“失败的Runnable”
showDialog("开始另一个XXX...");
//TODO 发出请求的逻辑
mHandler.postDelayed(mFailedRunnable, 5000);
```

