package com.westar.masseswork_98.ui.activity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.CameraUpdate;
import com.amap.api.maps2d.CameraUpdateFactory;
import com.amap.api.maps2d.MapView;
import com.amap.api.maps2d.UiSettings;
import com.amap.api.maps2d.model.BitmapDescriptor;
import com.amap.api.maps2d.model.BitmapDescriptorFactory;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.Marker;
import com.amap.api.maps2d.model.MarkerOptions;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.westar.library_base.base.BasePresenter;
import com.westar.library_base.base.ToolbarActivity;

import com.westar.masseswork_98.R;
import com.westar.masseswork_98.adapter.PlaceItemAdapter;
import com.westar.masseswork_98.been.PlaceItem;
import com.westar.masseswork_98.util.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * 中心导航界面
 * Created by lgy on 19/4/28
 */

public class CenterNavigationActivity extends ToolbarActivity  {

    private Button btnCenterList; //中心列表按钮
    private Drawable dwCenterList; //中心列表左边图标
    private RecyclerView rvCenterListPlace; //中心列表数据
    private List<PlaceItem> placeItems;
    private PlaceItemAdapter mPlaceItemAdapter;
    private BottomSheetBehavior bottomSheetBehavior; //底部弹出框

    //地图及定位
    private UiSettings uiSettings;
    private MapView mMapView; // 声明一个地图视图对象
    private AMap mMapLayer; // 声明一个地图图层对象
    private AMapLocationClient mLocClient; // 声明一个定位客户端对象
    private double mLatitude;
    private double mLongitude;
    private BitmapDescriptor bitmapDesc;
    private BitmapDescriptor positionDesc;
    private boolean isPaused = true;
    private MarkerOptions mMarkerOptions;
    private List<LatLng> latLngs; //政务中心地点信息

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initLocation(savedInstanceState);
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_center_navigation;
    }

    @Override
    protected void findId() {
        btnCenterList= (Button) findViewById(R.id.btn_center_list);
        rvCenterListPlace = (RecyclerView) findViewById(R.id.rv_center_list_place);
        bottomSheetBehavior = BottomSheetBehavior.from(findViewById(R.id.bottom_sheet_layout));
    }

    @Override
    protected void initView() {
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED); //初始化底部弹出框为收缩状态
        initRecyclerViewData(); //初始化地点列表数据
        initRecyclerView(); //初始化地点列表
        initListeners();
    }

    private void initListeners() {
        //中心列表按钮监听
        findViewById(R.id.btn_center_list).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                } else if(bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_COLLAPSED)
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
            }
        });
        //中心列表弹框状态监听
        bottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            //政务中心列表按钮位置变化更换按钮图标
            @Override
            public void onStateChanged(@NonNull View view, int i) {
                if (bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {
                    dwCenterList = getResources().getDrawable(R.drawable.icon_zxdh_xx);
                    dwCenterList.setBounds(0, 0, Utils.dip2px(getBaseContext(), 12), Utils.dip2px(getBaseContext(), 8));
                    btnCenterList.setCompoundDrawables(dwCenterList, null, null, null);
                    btnCenterList.setCompoundDrawablePadding(Utils.dip2px(getBaseContext(), 10));
                } else if (bottomSheetBehavior.getState() ==  BottomSheetBehavior.STATE_COLLAPSED){
                    dwCenterList = getResources().getDrawable(R.drawable.icon_zxdh_lb);
                    dwCenterList.setBounds(0, 0, Utils.dip2px(getBaseContext(), 16), Utils.dip2px(getBaseContext(), 16));
                    btnCenterList.setCompoundDrawables(dwCenterList, null, null, null);
                    btnCenterList.setCompoundDrawablePadding(Utils.dip2px(getBaseContext(), 15));
                }
            }

            @Override
            public void onSlide(@NonNull View view, float v) {

            }
        });
        //定位按钮监听
        findViewById(R.id.btn_orientation).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLocClient.startLocation();
            }
        });
    }

    @Override
    protected void initData() {

    }

    //初始化地点列表
    private void initRecyclerView() {
        rvCenterListPlace.setLayoutManager(new LinearLayoutManager(getBaseContext()));
        mPlaceItemAdapter = new PlaceItemAdapter(getBaseContext(), this.placeItems);

        mPlaceItemAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ToastUtils.showShort("ok");
            }
        });
        rvCenterListPlace.setAdapter(mPlaceItemAdapter);
    }

    //初始化地点列表数据
    private void initRecyclerViewData() {
        placeItems = new ArrayList<>();
        PlaceItem placeItem1 = new PlaceItem();
        placeItem1.setName("XXX市政务服务中心");
        placeItem1.setAddress("成都市武侯区静安路123号");
        for (int i = 0; i < 10; i++ ){
            placeItems.add(placeItem1);
        }
    }

    //设置地图参数（无法缩放、无法滑动、隐藏缩放按键）
    private void initUiSetting() {
        uiSettings = mMapLayer.getUiSettings();
        uiSettings.setScrollGesturesEnabled(false);
        uiSettings.setZoomGesturesEnabled(false);
        uiSettings.setZoomControlsEnabled(false);
    }

    // 初始化地图定位
    private void initLocation(Bundle savedInstanceState) {
        // 从布局文件中获取名叫map的地图视图
        mMapView = findViewById(R.id.map);
        // 执行地图视图的创建操作
        mMapView.onCreate(savedInstanceState);
        // 先隐藏地图，待定位到当前城市时再显示
        mMapView.setVisibility(View.INVISIBLE);
        if (mMapLayer == null) {
            mMapLayer = mMapView.getMap(); // 从地图视图中获取地图图层
            initUiSetting(); //设置地图参数（无法缩放、无法滑动、隐藏缩放按键）
        }
//        mMapLayer.setOnMapClickListener(this); // 给地图图层设置地图点击监听器
        mMapLayer.setMyLocationEnabled(true); // 开启定位图层
        mLocClient = new AMapLocationClient(this.getApplicationContext()); // 创建一个定位客户端
        mLocClient.setLocationListener(new MyLocationListener()); // 设置定位监听器
        AMapLocationClientOption option = new AMapLocationClientOption(); // 创建定位参数对象
        option.setLocationMode(AMapLocationClientOption.AMapLocationMode.Battery_Saving); // 设置省电的定位模式
        option.setNeedAddress(true); // 设置true才能获得详细的地址信息
        option.setOnceLocation(true); //设置单次定位
        mLocClient.setLocationOption(option); // 给定位客户端设置定位参数
        mLocClient.startLocation(); // 命令定位客户端开始定位
    }

    // 定义一个定位监听器
    public class MyLocationListener implements AMapLocationListener {

        // 在接收到定位消息时触发
        public void onLocationChanged(AMapLocation location) {
            // 如果地图视图已经销毁，则不再处理新接收的位置
            if (location == null || mMapView == null) {
                return;
            }
            mLatitude = location.getLatitude(); // 获得该位置的纬度
            mLongitude = location.getLongitude(); // 获得该位置的经度
            LatLng ll = new LatLng(mLatitude, mLongitude); // 创建一个经纬度对象
            CameraUpdate update = CameraUpdateFactory.newLatLngZoom(ll, 15);
            mMapLayer.moveCamera(update); // 设置地图图层的地理位置与缩放比例
            mMapView.setVisibility(View.VISIBLE); // 定位到当前城市时再显示图层
            ToastUtils.showShort("u orientation");
            // 画当前位置
            bitmapDesc = BitmapDescriptorFactory
                    .fromResource(R.drawable.icon_zxdh_ls);
            MarkerOptions ooMarker = new MarkerOptions().draggable(false)
                    .visible(true).icon(bitmapDesc).position(new LatLng(mLatitude, mLongitude));
            mMapLayer.addMarker(ooMarker);
            addPositionMarker(); //根据经纬度增加政务中心地点
            mMapLayer.setOnMarkerClickListener(new AMap.OnMarkerClickListener() {
                @Override
                public boolean onMarkerClick(Marker marker) {
                    marker.showInfoWindow();
                    ToastUtils.showShort("u click the point");
                    return true;
                }
            });
        }
    }

    //根据经纬度增加政务中心地点
    private void addPositionMarker() {
        String title = "政务中心";
        String content = "地点";
        latLngs = new ArrayList<>();
        latLngs.add(new LatLng(30.6207300000,104.0395500000));
        latLngs.add(new LatLng(30.6221100000,104.0358300000));
        positionDesc = BitmapDescriptorFactory
                .fromResource(R.drawable.icon_zxdh_hs);
        for (LatLng latLng : latLngs) {
            mMarkerOptions = new MarkerOptions()
                    .draggable(false)
                    .visible(true)
                    .icon(positionDesc)
                    .title(title)
                    .snippet(content)
                    .position(latLng);
            mMapLayer.addMarker(mMarkerOptions);
        }
    }


//    高德地图所需重写生命周期方法
    @Override
    public void onPause() {
    mMapView.onPause();
    isPaused = true;
    super.onPause();
}

    @Override
    public void onResume() {
        if (isPaused) {
            mMapView.onResume();
            isPaused = false;
        }
        super.onResume();
    }

    @Override
    public void onDestroy() {
        // 退出时销毁定位
        if (null != mLocClient) {
            mLocClient.onDestroy();
            mLocClient = null;
        }
        // 关闭定位图层
        if (mMapLayer != null) {
            mMapLayer.setMyLocationEnabled(false);
        }
        mMapView.onDestroy();
        mMapView = null;
        super.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mMapView.onSaveInstanceState(outState);
    }

    @Override
    public String setBarTitle() {
        return "中心导航";
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void onOther(Object data) {

    }

    @Override
    public void onSuccess(Object data) {

    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public LifecycleTransformer bindViewToLifecycle() {
        return null;
    }
}
