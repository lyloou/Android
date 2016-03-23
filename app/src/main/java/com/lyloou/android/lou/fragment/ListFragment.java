package com.lyloou.android.lou.fragment;

import java.util.ArrayList;
import java.util.Arrays;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.lyloou.android.R;
import com.lyloou.android.activity.WebRootActivity;
import com.lyloou.android.adapter.LouAdapter;
import com.lyloou.android.adapter.LouHolder;
import com.lyloou.android.app.LouApp;
import com.lyloou.android.constants.LouConstants;
import com.lyloou.android.fragment.BaseFragment;
import com.lyloou.android.lou.activity.ListActivity;
import com.lyloou.android.test.ActivitySingleFragmentActivityActivity;
import com.lyloou.android.test.AnimatorShowAndHideActivity;
import com.lyloou.android.test.DrawableTbtnSelectorActivity;
import com.lyloou.android.test.FragmentWebRootFragmentActivity;
import com.lyloou.android.test.ViewCircleViewActivity;
import com.lyloou.android.test.ViewClockViewActivity;
import com.lyloou.android.test.ViewColorPickerActivity;
import com.lyloou.android.test.ViewHistogramActivity;
import com.lyloou.android.test.ViewNumActivity;
import com.lyloou.android.test.ViewSwitchActivity;

public class ListFragment extends BaseFragment implements OnItemClickListener {
    private Context mContext;
    private ArrayList<String> mDatas;
    public static final String DATA_LEVEL = "data_level";
    private LouAdapter<String> mLouAdapter;

    public ListFragment() {
        super();
    }

    public static Fragment newInstance(String[] datas) {
        Fragment fragment = new ListFragment();
        Bundle bundle = new Bundle();
        bundle.putStringArray(DATA_LEVEL, datas);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lou_main, container, false);
        initData();
        initView(view);
        return view;
    }

    private void initData() {
        mContext = getActivity();
        String[] strings = getArguments().getStringArray(DATA_LEVEL);
        if(strings == null){
            strings = new String[]{};
        }
        mDatas = new ArrayList<String>(Arrays.asList(strings));
    }

    private void initView(View view) {
        ListView lvMain = (ListView) view.findViewById(R.id.lv_main);
        // lvMain.setAdapter(new ArrayAdapter<String>(mContext, android.R.layout.simple_list_item_1, mDatas));
        lvMain.setAdapter(mLouAdapter = new LouAdapter<String>(mContext, lvMain, android.R.layout.simple_list_item_1) {
            @Override
            protected void assign(LouHolder holder, String s) {
                holder.putText(android.R.id.text1, s);
            }
        });
        mLouAdapter.initList(mDatas);
        lvMain.setOnItemClickListener(this);
    }

    private void toListActivity(Context context, int arrId) {
        String[] array = context.getResources().getStringArray(arrId);
        Intent intent = new Intent(context, ListActivity.class);
        intent.putExtra(DATA_LEVEL, array);
        startActivity(intent);
    }

    private void toActivity(Context context, Class<?> cls) {
        Intent intent = new Intent(mContext, cls);
        startActivity(intent);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,
                            long id) {

        String item = mDatas.get(position);
        switch (item) {
            case "activity":
                toListActivity(mContext, R.array.activity);
                break;
            case "fragment":
                toListActivity(mContext, R.array.fragment);
                break;
            case "util":
                toListActivity(mContext, R.array.util);
                break;
            case "view":
                toListActivity(mContext, R.array.view);
                break;
            case "animation":
                toListActivity(mContext, R.array.animation);
                break;
            case "drawable":
                toListActivity(mContext, R.array.drawable);
                break;
            case "other":
                toListActivity(mContext, R.array.other);
                break;

            // activity
            case "Single Fragment Activity":
                toActivity(mContext, ActivitySingleFragmentActivityActivity.class);
                break;
            case "Web View Activity":
                WebRootActivity.startActivity(mContext, LouConstants.URL_LYLOOU_CSDN);
                break;

            // fragment
            case "Web View Fragment":
                toActivity(mContext, FragmentWebRootFragmentActivity.class);
                break;

            // util

            // view
            case "Clock View":
                toActivity(mContext, ViewClockViewActivity.class);
                break;
            case "Circle View":
                toActivity(mContext, ViewCircleViewActivity.class);
                break;
            case "Color Picker View":
                toActivity(mContext, ViewColorPickerActivity.class);
                break;
            case "Histogram View":
                toActivity(mContext, ViewHistogramActivity.class);
                break;
            case "Num View":
                toActivity(mContext, ViewNumActivity.class);
                break;
            case "Switch View":
                toActivity(mContext, ViewSwitchActivity.class);
                break;

            // animation
            case "Show and Hide Animator":
                toActivity(mContext, AnimatorShowAndHideActivity.class);
                break;

            // drawable
            case "Tbtn Drawable":
                toActivity(mContext, DrawableTbtnSelectorActivity.class);
                break;
            // other

        }
    }

}
