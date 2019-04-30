package com.westar.masseswork_98.been;

/**
 * Created by ZWP on 2019/4/30 16:46.
 * 描述：模拟card基本信息
 */
public class MeCardInfo {
    String title;
    String authenticationStatus;
    String describle;
    String describle2;

    String colors[] = {"#5698f0", "#5289e7", "#4a6dd5"};

    String typeUrl;

    public String getTitle() {
        return title;
    }

    public MeCardInfo setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getAuthenticationStatus() {
        return authenticationStatus;
    }

    public MeCardInfo setAuthenticationStatus(String authenticationStatus) {
        this.authenticationStatus = authenticationStatus;
        return this;
    }

    public String getDescrible() {
        return describle;
    }

    public MeCardInfo setDescrible(String describle) {
        this.describle = describle;
        return this;
    }

    public String getDescrible2() {
        return describle2;
    }

    public MeCardInfo setDescrible2(String describle2) {
        this.describle2 = describle2;
        return this;
    }

    public String[] getColors() {
        return colors;
    }

    public MeCardInfo setColors(String[] colors) {
        this.colors = colors;
        return this;
    }

    public String getTypeUrl() {
        return typeUrl;
    }

    public MeCardInfo setTypeUrl(String typeUrl) {
        this.typeUrl = typeUrl;
        return this;
    }
}
