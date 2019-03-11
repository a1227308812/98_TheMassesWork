package com.westar.masseswork_98.library_base.base;

import android.support.annotation.NonNull;

import com.blankj.utilcode.util.PermissionUtils;
import com.qmuiteam.qmui.widget.dialog.QMUIDialog;
import com.qmuiteam.qmui.widget.dialog.QMUIDialogAction;
import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.westar.masseswork_98.library_base.R;
import com.westar.masseswork_98.library_base.callback.IPermissionsCallBack;

import io.reactivex.functions.Consumer;

/**
 * Created by ZWP on 2019/3/1.
 * 描述：权限请求超类
 */
public class BasePermissionActivity extends BaseRxActivity {
    /**
     * 申请权限
     *
     * @param prms
     * @param isShow   是否显示弹窗
     * @param allShow  是否一直显示
     * @param callBack 回调
     */
    protected void requestPermissions(final String[] prms, final boolean isShow, final boolean allShow, final IPermissionsCallBack callBack) {
        addSubscribe(new RxPermissions(this).requestEachCombined(prms).subscribe(new Consumer<Permission>() {
            @Override
            public void accept(Permission permission) throws Exception {
                // 用户已经同意该权限
                if (permission.granted) {
                    callBack.permissionSuccess(permission.name);
                }
                // 用户拒绝了该权限，没有选中『不再询问』（Never ask again）,那么下次再次启动时。还会提示请求权限的对话框
                else if (permission.shouldShowRequestPermissionRationale) {
                    if (isShow) {
                        showPermissionsDialog(prms, isShow, false, allShow, callBack);
                    } else {
                        if (callBack != null) {
                            callBack.permissionErro(permission.name);
                        }
                    }
                }
                // 用户拒绝了该权限，而且选中『不再询问』
                else {
                    if (isShow) {
                        showPermissionsDialog(prms, isShow, true, allShow, callBack);
                    } else {
                        if (callBack != null) {
                            callBack.permissionErro(permission.name);
                        }
                    }
                }
            }
        }));
    }

    /**
     * 拒绝权限弹窗
     *
     * @param prms     权限
     * @param isShow   是否显示弹窗
     * @param isNever  不再提示
     * @param allShow  是否一直显示弹窗
     * @param callBack 回调
     */
    protected void showPermissionsDialog(final String[] prms, final boolean isShow, final boolean isNever, final boolean allShow, final IPermissionsCallBack callBack) {
        QMUIDialog.MessageDialogBuilder messageDialogBuilder = new QMUIDialog.MessageDialogBuilder(this);
        messageDialogBuilder.setMessage(true == isNever ? getResources().getString(R.string.request_permissions_dialog_open_setting_str) : getResources().getString(R.string.request_permissions_dialog_open_again_str))
                //确认
                .addAction(getResources().getString(R.string.request_permissions_dialog_open), new QMUIDialogAction.ActionListener() {
                    @Override
                    public void onClick(QMUIDialog dialog, int index) {
                        if (isNever) {
                            //跳转手机设置界面
                            PermissionUtils.launchAppDetailsSettings();
                        } else {
                            //请求权限确认
                            requestPermissions(prms, isShow, allShow, callBack);
                        }
                        dialog.dismiss();
                    }
                })
                //取消
                .addAction(getResources().getString(R.string.request_permissions_dialog_open), new QMUIDialogAction.ActionListener() {
                    @Override
                    public void onClick(QMUIDialog dialog, int index) {
                        if (!allShow) {
                            callBack.permissionErro("");
                        } else {
                            requestPermissions(prms, isShow, allShow, callBack);
                        }
                        dialog.dismiss();
                    }
                })
                .setCanceledOnTouchOutside(false)
                .setCancelable(false)
                .show();
    }

    /**
     * 检查权限提示框
     *
     * @param prms
     * @param requestStr
     * @param callBack
     */
    public void showRequestPermissions(final String[] prms, String requestStr, final IPermissionsCallBack callBack) {
        QMUIDialog.MessageDialogBuilder messageDialogBuilder = new QMUIDialog.MessageDialogBuilder(this);
        messageDialogBuilder
                .setMessage(requestStr)
                .addAction(getResources().getString(R.string.request_permissions_dialog_open), new QMUIDialogAction.ActionListener() {
                    @Override
                    public void onClick(QMUIDialog dialog, int index) {
                        dialog.dismiss();
                        if (callBack != null) {
                            requestPermissions(prms, false, false, callBack);
                        }
                    }
                })
                .addAction(R.string.request_permissions_dialog_cancel, new QMUIDialogAction.ActionListener() {
                    @Override
                    public void onClick(QMUIDialog dialog, int index) {
                        dialog.dismiss();
                        if (callBack != null) {
                            callBack.permissionErro("");
                        }
                    }
                })
                .setCanceledOnTouchOutside(false)
                .setCancelable(false)
                .show();
    }

}
