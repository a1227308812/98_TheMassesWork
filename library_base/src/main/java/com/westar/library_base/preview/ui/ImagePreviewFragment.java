package com.westar.library_base.preview.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.westar.masseswork_98.library_base.R;

import uk.co.senab.photoview.PhotoView;


/**
 * Created by luoyz on 2019/3/20 13:35
 * Version : 1.0
 * Last update by luoyz on 2019/3/20 13:35
 * Describe :
 */

public class ImagePreviewFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_image_preview, container, false);
        PhotoView pvImagePreview = view.findViewById(R.id.pvImagePreview);
        String url = getArguments().getString("url");
        if (null != url && !"".equals(url)) {
            Glide.with(this).load(url).into(pvImagePreview);
        }
        return view;
    }

}
