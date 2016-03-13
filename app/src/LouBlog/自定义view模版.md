######【关键词】
`自定义View` `模版`

######【问题】
* 写一个自定义View的模板

######【注意事项】
* 自定义View的时候，会遇到 padding 无效的问题，需要做特殊处理；
* 如果有滑动的操作，需要解决滑动冲突；


######【代码】
```
 
public class CustomView extends View {
 
    private Paint mPaint;
    private FontMetricsInt mFontMetricsInt;
    private Rect mRect;
 
    private int mWidth;
    private int mHeight;
 
    private int mPaddingStart;
    private int mPaddingEnd;
    private int mPaddingTop;
    private int mPaddingBottom;
 
    private int mLeft;
    private int mTop;
    private int mRight;
    private int mBottom;

    private Context mContext;
 
    public CustomView (Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initTool();
    }
 
    public CustomView (Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }
 
    public CustomView (Context context) {
        this(context, null);
    }
 
    private void initTool() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mPaint.setTextSize(sp2Px(mContext, 11));
        mPaint.setTextAlign(Paint.Align.CENTER);
        mRect = new Rect();
        mFontMetricsInt = mPaint.getFontMetricsInt();
    }
 
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        mWidth = getSizeFromMeasureSpec(widthMeasureSpec, 280);
        mHeight = getSizeFromMeasureSpec(heightMeasureSpec, 280);
 
        mPaddingStart = getPaddingStart();
        mPaddingEnd = getPaddingEnd();
        mPaddingTop = getPaddingTop();
        mPaddingBottom = getPaddingBottom();
 
        mLeft = mPaddingStart;
        mTop = mPaddingTop;
        mRight = mWidth - mPaddingEnd;
        mBottom = mHeight - mPaddingBottom;
 
        setMeasuredDimension(mWidth, mHeight);
    }
 
    // 事件拦截，防止滑动冲突，与ScrollView等可滑动控件之间；
    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        ViewParent parent = getParent();
        switch(event.getAction()){
        case MotionEvent.ACTION_DOWN:
            parent.requestDisallowInterceptTouchEvent(true);
            break;
        case MotionEvent.ACTION_UP:
            parent.requestDisallowInterceptTouchEvent(false);
            break;
        default:
            break;
        }
        return super.dispatchTouchEvent(event);
    }
 
    @Override
    protected void onDraw(Canvas canvas) {

        // 画背景，如果存在的画
        Drawable bg = getBackground();
        if(bg!=null){
            bg.draw(canvas);
        }

        // 画背景
        canvas.drawColor(Color.RED);

        // 画 Padding 以内区域的背景
        mRect.set(mLeft, mTop, mRight, mBottom);
        mPaint.setColor(Color.YELLOW);
        canvas.drawRect(mRect, mPaint);

        // TODO 其他画图的操作（注意：不推荐在 onDraw 方法中 new 对象，可以使用全局变量）
        super.onDraw(canvas);
    }


    // 工具类
    public static int getSizeFromMeasureSpec(int measureSpec, int defaultSize) {
        int result = 0;
        int mode = MeasureSpec.getMode(measureSpec);
        int size = MeasureSpec.getSize(measureSpec);
        if(mode == MeasureSpec.EXACTLY){
            result = size;
        } else {
            result = defaultSize;
            if(mode == MeasureSpec.AT_MOST){
                result = Math.min(defaultSize, size);
            }
        }
        return result;
    }
    public static float sp2Px(Context context, float sp){
        DisplayMetrics metrics = new DisplayMetrics();
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        display.getMetrics(metrics);
        float px = metrics.scaledDensity;
        return sp * px;
    }
}
```