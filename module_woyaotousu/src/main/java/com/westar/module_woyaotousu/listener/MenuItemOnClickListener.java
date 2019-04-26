package com.westar.module_woyaotousu.listener;

import android.view.View;

import com.westar.module_woyaotousu.databean.MenuItem;
import com.westar.module_woyaotousu.fragment.BottomMenuFragment;

public abstract class MenuItemOnClickListener implements View.OnClickListener{

    public MenuItemOnClickListener(BottomMenuFragment _bottomMenuFragment, MenuItem _menuItem) {
        this.bottomMenuFragment = _bottomMenuFragment;
        this.menuItem = _menuItem;
    }

    public BottomMenuFragment getBottomMenuFragment() {
        return bottomMenuFragment;
    }

    public void setBottomMenuFragment(BottomMenuFragment bottomMenuFragment) {
        this.bottomMenuFragment = bottomMenuFragment;
    }

    public MenuItem getMenuItem() {
        return menuItem;
    }

    public void setMenuItem(MenuItem menuItem) {
        this.menuItem = menuItem;
    }

    private BottomMenuFragment bottomMenuFragment;
    private MenuItem menuItem;

    @Override
    public void onClick(View v){

        if(bottomMenuFragment != null && bottomMenuFragment.isVisible()) {
            bottomMenuFragment.dismiss();
        }

        this.onClickMenuItem(v, this.menuItem);
    }
    public abstract void onClickMenuItem(View v, MenuItem menuItem);
}
