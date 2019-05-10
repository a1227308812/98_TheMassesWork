package com.westar.masseswork_98.ui.activity;


import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.AppCompatCheckedTextView;
import android.text.TextUtils;
import android.view.View;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.blankj.utilcode.util.ToastUtils;
import com.coorchice.library.SuperTextView;
import com.jakewharton.rxbinding2.view.RxView;
import com.jakewharton.rxbinding2.widget.RxCheckedTextView;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.westar.Config;
import com.westar.been.LocationNode;
import com.westar.library_base.base.BasePresenter;
import com.westar.library_base.base.ToolbarActivity;
import com.westar.library_base.http.been.HttpRequest;
import com.westar.masseswork_98.R;
import com.westar.masseswork_98.been.AddressInfo;
import com.westar.masseswork_98.mvp.contract.AddAddressContract;
import com.westar.masseswork_98.mvp.presenter.AddAddressPresenter;
import com.westar.masseswork_98.ui.custom.CustomTextInputEditText;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.functions.Consumer;

/**
 * Created by ZWP on 2019/5/10 11:53.
 * 描述：添加新地址页面
 */
public class AddAddressActivity extends ToolbarActivity implements AddAddressContract.View {


    @BindView(R.id.et_sjr)
    TextInputEditText etSjr;
    @BindView(R.id.et_sjhm)
    TextInputEditText etSjhm;
    @BindView(R.id.et_szdq)
    CustomTextInputEditText etSzdq;
    @BindView(R.id.tl_szdq)
    TextInputLayout tlSzdq;
    @BindView(R.id.et_xxjd)
    TextInputEditText etXxjd;
    @BindView(R.id.et_yzbm)
    TextInputEditText etYzbm;
    @BindView(R.id.ct_szmr)
    AppCompatCheckedTextView ctSzmr;
    @BindView(R.id.complete)
    SuperTextView complete;

    AddAddressPresenter presenter;

    @Override
    protected BasePresenter createPresenter() {
        return presenter = new AddAddressPresenter();
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_add_address;
    }

    @Override
    protected void findId() {

    }

    @Override
    protected void initView() {
        addSubscribe(RxView.clicks(complete).throttleFirst(Config.WINDOWDURATION, TimeUnit.SECONDS).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                if (checkNetWork()) {
                    if (checkRequestInfo()) {
                        // TODO: 2019/5/10 采集页面数据
                        presenter.addAddress(new HttpRequest());
                    }
                } else {
                    ToastUtils.showShort("哎呀！没有网络了！请检查网络连接！");
                }
            }
        }));
        addSubscribe(RxView.clicks(tlSzdq).throttleFirst(Config.WINDOWDURATION, TimeUnit.SECONDS).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                if (checkNetWork()) {
                    presenter.getServiceAddressData(new HttpRequest());
                } else {
                    ToastUtils.showShort("哎呀！没有网络了！请检查网络连接！");
                }
            }
        }));
        ctSzmr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ctSzmr.setChecked(!ctSzmr.isChecked());
            }
        });
    }

    private boolean checkRequestInfo() {
        if (TextUtils.isEmpty(etSjr.getText().toString())) {
            ToastUtils.showShort("收件人姓名不能为空");
            return false;
        }
        if (TextUtils.isEmpty(etSjhm.getText().toString())) {
            ToastUtils.showShort("收件人手机号不能为空");
            return false;
        }
        if (TextUtils.isEmpty(etSzdq.getText().toString())) {
            ToastUtils.showShort("所在地区不能为空");
            return false;
        }
        if (TextUtils.isEmpty(etXxjd.getText().toString())) {
            ToastUtils.showShort("详细街道不能为空");
            return false;
        }
        if (TextUtils.isEmpty(etYzbm.getText().toString())) {
            ToastUtils.showShort("邮政编码不能为空");
            return false;
        }
        return true;
    }

    @Override
    protected void initData() {

    }

    @Override
    public String setBarTitle() {
        return "新增地址信息";
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
        setResultOk(null);
    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public LifecycleTransformer bindViewToLifecycle() {
        return this.bindToLifecycle();
    }

    @Override
    public void getServiceAddressData(List<LocationNode> locationNodeList) {
        choiceAddress(locationNodeList);
    }

    private void choiceAddress(List<LocationNode> locationNodeList) {
        // TODO: 2019/5/6
        if (null != locationNodeList && locationNodeList.size() > 0) {
            final List<LocationNode> provinceList = locationNodeList;
            final List<List<LocationNode>> cityList = new ArrayList<>();
            final List<List<List<LocationNode>>> areaList = new ArrayList<>();
            //初始化数据
            for (LocationNode province : provinceList) {
                List<LocationNode> citys = province.getChilds();
                cityList.add(citys);

                List<List<LocationNode>> cityAreas = new ArrayList<>();
                for (LocationNode city : citys) {
                    List<LocationNode> areas = city.getChilds();
                    cityAreas.add(areas);
                }
                areaList.add(cityAreas);
            }

            OptionsPickerBuilder builder = new OptionsPickerBuilder(mContext, new OnOptionsSelectListener() {
                @Override
                public void onOptionsSelect(int options1, int options2, int options3, View v) {
                    etSzdq.setText(provinceList.get(options1).getName() + "--"
                            + cityList.get(options1).get(options2).getName() + "--"
                            + areaList.get(options1).get(options2).get(options3).getName());
                }
            });

            builder.setTitleText("请选择地址")
                    .setOutSideCancelable(true)
                    .setCyclic(false, false, false)
                    .build();

            OptionsPickerView pvOptions = builder.build();
            pvOptions.setKeyBackCancelable(true);
            pvOptions.setPicker(provinceList, cityList, areaList);
            pvOptions.show();
        }
    }


}
