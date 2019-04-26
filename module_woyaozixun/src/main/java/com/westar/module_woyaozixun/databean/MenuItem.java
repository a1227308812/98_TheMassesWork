package com.westar.module_woyaozixun.databean;

import com.westar.module_woyaozixun.listener.MenuItemOnClickListener;

public class MenuItem {
    public MenuItem() {

    }

    /**
     *
     * @param _item_name 菜单项名称
     * @param _text 菜单项显示内容
     * @param _menuItemOnClickListener 菜单点击回调事件
     */
    public MenuItem(String _item_name, String _text, MenuItemOnClickListener _menuItemOnClickListener){
        this.item_name = _item_name;
        this.text = _text;
        this.menuItemOnClickListener = _menuItemOnClickListener;
    }


    private String item_name;
    private String text;

    public String getItem_name() {
        return item_name;
    }

    public String getText() {
        return text;
    }


    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public void setText(String text) {
        this.text = text;
    }


    public MenuItemOnClickListener getMenuItemOnClickListener() {
        return menuItemOnClickListener;
    }

    public void setMenuItemOnClickListener(MenuItemOnClickListener menuItemOnClickListener) {
        this.menuItemOnClickListener = menuItemOnClickListener;
    }

    private MenuItemOnClickListener menuItemOnClickListener;


}
