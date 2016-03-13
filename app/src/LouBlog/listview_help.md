```
<?xml version="1.0" encoding="utf-8"?>

<!-- 设置无滚动条，无分割线，背景透明 -->
<ListView xmlns:android="http://schemas.android.com/apk/res/android"
    android:id="@+id/lv"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:divider="@color/transparent"
    android:fadingEdge="none"
    android:listSelector="@color/transparent"
    android:scrollbars="none" />
```

**ListView 取消点击效果**
`mLvList.setSelector(new ColorDrawable(Color.TRANSPARENT));`

**ListView 设置点击效果**
```
mLvList.setSelector(R.drawable.lv_timer_item);
<?xml version="1.0" encoding="utf-8"?>
<selector xmlns:android="http://schemas.android.com/apk/res/android" >
    <item android:state_pressed="true" android:drawable="@color/timer_lv_item"/>
    <item android:drawable="@android:color/transparent"></item>
</selector>
<color name="timer_lv_item">#88575756</color>
```
