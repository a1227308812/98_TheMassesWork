package com.westar.library_base.preview.ui;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.westar.library_base.preview.bean.GlobalBean;
import com.westar.masseswork_98.library_base.R;

import java.util.ArrayList;

/**
 * Created by luoyz on 2019/3/20 13:21
 * Version : 1.0
 * Last update by luoyz on 2019/3/20 13:21
 * Describe : 图片预览全屏DialogFragment
 */

public class ImagePreviewDialogFragment extends DialogFragment {
    private ImagePreviewVpAdapter mAdapter;
    private ArrayList<String> mUrlList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_fragment_image_preview, container, false);
        init(view);
        return view;
    }

    private void init(View view) {
        ViewPager vpImagePreview = view.findViewById(R.id.vpImagePreview);
        mUrlList = new ArrayList<>();
        ArrayList<String> list = getArguments().getStringArrayList(GlobalBean.KEY_IMAGE_URL);
        int pos = getArguments().getInt(GlobalBean.KEY_DEFAULT_POS, 0);
        if (list != null && !list.isEmpty()) {
            mUrlList.addAll(list);
            mAdapter = new ImagePreviewVpAdapter(getChildFragmentManager(), mUrlList);
            vpImagePreview.setAdapter(mAdapter);
            vpImagePreview.setCurrentItem(pos);
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        super.onActivityCreated(savedInstanceState);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(0xff000000));
        getDialog().getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
    }
}
