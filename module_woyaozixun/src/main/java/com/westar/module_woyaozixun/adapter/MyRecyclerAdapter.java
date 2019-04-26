package com.westar.module_woyaozixun.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.coorchice.library.SuperTextView;
import com.github.library.bubbleview.BubbleTextView;
import com.westar.module_woyaozixun.R;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

public class MyRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final Context mContext;
    private final List<Integer> typeList;

    private static final int TYPE_ROBOT = 0;
    private static final int TYPE_USER = 1;

    public MyRecyclerAdapter(Context context, List<Integer> typeList) {
        this.mContext = context;
        this.typeList = typeList;
    }

    @Override
    public int getItemViewType(int position) {
        int viewType = -1;
        int index = position % 2;
        switch (index) {
            case 0:
                viewType = TYPE_ROBOT;
                break;
            case 1:
                viewType = TYPE_USER;
                break;
        }
        return viewType;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_ROBOT) {
//            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recyclerview_robot, parent, false);
            View view = View.inflate(mContext, R.layout.item_recyclerview_robot, null);
            RobotViewHolder robotViewHolder = new RobotViewHolder(view);
            return robotViewHolder;
        } else if (viewType == TYPE_USER) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recyclerview_user, parent, false);
            UserViewHolder userViewHolder = new UserViewHolder(view);
            return userViewHolder;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (getItemViewType(position)) {
            case 0:
                final RobotViewHolder robotViewHolder = (RobotViewHolder) holder;
                robotViewHolder.btvMsg.setText("Hi，我是小辰。我猜您可能对以下问题感到疑惑：\n" +
                        "1.这是该事项的常见问题1？\n" +
                        "2.这是该事项的常见问题2？\n" +
                        "3.这是该事项的常见问题3？\n" +
                        "请回复问题对应数字获取答案。");
                break;

            case 1:
                final UserViewHolder userViewHolder = (UserViewHolder) holder;
                userViewHolder.btvMsg.setText("ok");
                break;
        }
    }

    @Override
    public int getItemCount() {
        return typeList.size();
    }

    public void addData(int position) {
        typeList.add(position);
        notifyItemInserted(position);
    }

    public class RobotViewHolder extends RecyclerView.ViewHolder {
        public TextView tvTime;
        public SuperTextView stvPicture;
        public BubbleTextView btvMsg;
        public RobotViewHolder(View viewItem) {
            super(viewItem);
            tvTime = (TextView) viewItem.findViewById(R.id.tv_time);
            stvPicture = (SuperTextView) viewItem.findViewById(R.id.picture_robot);
            btvMsg = (BubbleTextView) viewItem.findViewById(R.id.btv_robot);
        }
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {
        public BubbleTextView btvMsg;
        public SuperTextView stvPicture;
        public UserViewHolder(View viewItem) {
            super(viewItem);
            btvMsg = (BubbleTextView) viewItem.findViewById(R.id.btv_user);
            stvPicture = (SuperTextView) viewItem.findViewById(R.id.picture_user);
        }
    }
}
