package com.westar.masseswork_98.been;

import android.graphics.drawable.Drawable;

public class MoreFuncIconItem {
    private String name;
    private Drawable drawable;

    public MoreFuncIconItem(String name, Drawable drawable) {
        this.name = name;
        this.drawable = drawable;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setDrawable(Drawable drawable) {
        this.drawable = drawable;
    }

    public Drawable getDrawable() {
        return drawable;
    }
}
