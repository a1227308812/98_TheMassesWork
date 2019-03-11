//package com.westar.masseswork_98.library_base.base;
//
//import com.blankj.utilcode.util.PermissionUtils;
//import com.qmuiteam.qmui.widget.dialog.QMUIDialog;
//import com.tbruyelle.rxpermissions2.RxPermissions;
//import com.xiaohou.basetools.R;
//import com.xiaohou.basetools.callback.IPermissionsCallBack;
//
///**
// * Created by ZWP on 2019/3/1.
// * 描述：
// */
//public class BasePermissionFragment extends BaseRxFragment {
//    /**
//     * 申请权限
//     * @param prms
//     * @param isShow 是否选择弹窗
//     * @param allShow 是否一直显示
//     * @param callBack 回调
//     */
//    protected void requestPermissions(String[] prms, boolean isShow, boolean allShow, IPermissionsCallBack callBack) {
//        addSubscribe(new RxPermissions(this).requestEachCombined(prms)
//                .subscribe(permission -> {
//                    if (permission.granted) {
//                        callBack.permissionSuccess(permission.name);
//                    } else if (permission.shouldShowRequestPermissionRationale) {
//                        if (isShow) {
//                            showPermissionsDialog(prms, isShow, false, allShow, callBack);
//                        } else {
//                            if (callBack != null) {
//                                callBack.permissionErro(permission.name);
//                            }
//                        }
//                    } else {
//                        if (isShow) {
//                            showPermissionsDialog(prms, isShow, true, allShow, callBack);
//                        } else {
//                            if (callBack != null) {
//                                callBack.permissionErro(permission.name);
//                            }
//                        }
//                    }
//                }));
//    }
//
//    /**
//     * 拒绝权限弹窗
//     * @param prms 权限
//     * @param isShow 是否显示弹窗
//     * @param isNever 不再提示
//     * @param allShow 是否一直显示弹窗
//     * @param callBack 回调
//     */
//    protected void showPermissionsDialog(String[] prms, boolean isShow, boolean isNever, boolean allShow, IPermissionsCallBack callBack) {
//        QMUIDialog.MessageDialogBuilder messageDialogBuilder = new QMUIDialog.MessageDialogBuilder(getActivity());
//        messageDialogBuilder
//                .setMessage(isNever == true ? getResources().getString(R.string.request_permissions_dialog_open_setting_str) : getResources().getString(R.string.request_permissions_dialog_open_again_str))
//                .addAction(getResources().getString(R.string.request_permissions_dialog_open), (dialog, index) -> {
//                    if (isNever) {
//                        PermissionUtils.launchAppDetailsSettings();
//                    } else {
//                        requestPermissions(prms, isShow, allShow, callBack);
//                    }
//                    dialog.dismiss();
//                })
//                .addAction(R.string.request_permissions_dialog_cancel, (dialog, index) -> {
//                    if (!allShow) {
//                        callBack.permissionErro("");
//                    } else {
//                        requestPermissions(prms, isShow, allShow, callBack);
//                    }
//                    dialog.dismiss();
//                })
//                .setCanceledOnTouchOutside(false)
//                .setCancelable(false)
//                .show();
//    }
//
//    /**
//     * 检查权限提示框
//     *
//     * @param prms
//     * @param requestStr
//     * @param callBack
//     */
//    public void showRequestPermissions(String[] prms, String requestStr, IPermissionsCallBack callBack) {
//        QMUIDialog.MessageDialogBuilder messageDialogBuilder = new QMUIDialog.MessageDialogBuilder(getActivity());
//        messageDialogBuilder
//                .setMessage(requestStr)
//                .addAction(getResources().getString(R.string.request_permissions_dialog_open), (dialog, index) -> {
//                    dialog.dismiss();
//                    if (callBack != null) {
//                        requestPermissions(prms, false, false, callBack);
//                    }
//                })
//                .addAction(R.string.request_permissions_dialog_cancel, (dialog, index) -> {
//                    dialog.dismiss();
//                    if (callBack != null) {
//                        callBack.permissionErro("");
//                    }
//                })
//                .setCanceledOnTouchOutside(false)
//                .setCancelable(false)
//                .show();
//    }
//
//
//}
