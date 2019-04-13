package com.westar.module_woyaochaxun.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.scwang.smartrefresh.layout.util.DensityUtil;
import com.westar.module_woyaochaxun.R;
import com.westar.module_woyaochaxun.model.TimeLineNodeModel;

import java.util.List;

/**
 * 时间轴适配器
 * Created by zb on 2019/4/11.
 */

public class TimeLineAdapter extends BaseQuickAdapter {

    private Context mContext;

    public TimeLineAdapter(int layoutResINullable, List data,Context context) {
        super(R.layout.item_time_line, data);
        this.mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, Object item) {
        TimeLineNodeModel timeLineNodeModel = (TimeLineNodeModel) item;

        TextView tvTimeLine = helper.getView(R.id.tv_time_line);

        //时间连接线高亮显示
        if (timeLineNodeModel.isShowLightLine()){
            tvTimeLine.setWidth(DensityUtil.dp2px(4));
            tvTimeLine.setBackgroundColor(mContext.getResources().getColor(R.color.color_timeline_blue));
        }else {
            tvTimeLine.setWidth(DensityUtil.dp2px(2));
            tvTimeLine.setBackgroundColor(mContext.getResources().getColor(R.color.color_timeline_grey));
        }

        //最后一条数据隐藏时间连接线
        if (helper.getAdapterPosition() == mData.size()-1){
            tvTimeLine.setVisibility(View.GONE);
        }else {
            tvTimeLine.setVisibility(View.VISIBLE);
        }


        //受理 办理中  办结
        String nodeName = "";
        switch (timeLineNodeModel.getNodeType()){

        }
        helper.setText(R.id.tv_timeline_bottom,nodeName);



    }
}
