package com.westar.library_base.view;

import android.content.Context;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;

import com.qmuiteam.qmui.widget.QMUIWrapContentListView;
import com.qmuiteam.qmui.widget.popup.QMUIPopup;

/**
 * Created by ZWP on 2019/6/10 15:34.
 * 描述：
 */
public class ListPopup extends QMUIPopup {
    private BaseAdapter mAdapter;
    public ListView listView;

    public BaseAdapter getmAdapter() {
        return mAdapter;
    }

    public ListPopup setmAdapter(BaseAdapter mAdapter) {
        this.mAdapter = mAdapter;
        return this;
    }

    /**
     * 构造方法。
     *
     * @param context   传入一个 Context。
     * @param direction Popup 的方向，为 {@link QMUIPopup#DIRECTION_NONE}, {@link QMUIPopup#DIRECTION_TOP} 和 {@link QMUIPopup#DIRECTION_BOTTOM} 中的其中一个值。
     */
    public ListPopup(Context context, @Direction int direction) {
        super(context, direction);
    }

    public void create(int width, int maxHeight, AdapterView.OnItemClickListener onItemClickListener) {
        listView = new QMUIWrapContentListView(mContext, maxHeight);
        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(width, maxHeight);
        listView.setLayoutParams(lp);
        listView.setAdapter(mAdapter);
        listView.setVerticalScrollBarEnabled(false);
        listView.setOnItemClickListener(onItemClickListener);
        listView.setDivider(null);
        setContentView(listView);
    }
}
