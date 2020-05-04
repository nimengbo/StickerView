package com.example.abner.stickerdemo.model;

import java.io.Serializable;

/**
 * Created by Abner on 15/6/11.
 * QQ 230877476
 * Email nimengbo@gmail.com
 */
public class StickerPropertyModel implements Serializable {
    private static final long serialVersionUID = 3800737478616389410L;

    //贴纸id
    private long stickerId;
    //文本
    private String text;
    //x坐标
    private float xLocation;
    //y坐标
    private float yLocation;
    //角度
    private float degree;
    //缩放值
    private float scaling;
    //气泡顺序
    private int order;

    //水平镜像 1镜像 2未镜像
    private int horizonMirror;

    //贴纸PNG URL
    private String stickerURL;

    public int getHorizonMirror() {
        return horizonMirror;
    }

    public void setHorizonMirror(final int horizonMirror) {
        this.horizonMirror = horizonMirror;
    }

    public String getStickerURL() {
        return stickerURL;
    }

    public void setStickerURL(final String stickerURL) {
        this.stickerURL = stickerURL;
    }

    public long getStickerId() {
        return stickerId;
    }

    public void setStickerId(final long stickerId) {
        this.stickerId = stickerId;
    }

    public String getText() {
        return text;
    }

    public void setText(final String text) {
        this.text = text;
    }

    public float getxLocation() {
        return xLocation;
    }

    public void setxLocation(final float xLocation) {
        this.xLocation = xLocation;
    }

    public float getyLocation() {
        return yLocation;
    }

    public void setyLocation(final float yLocation) {
        this.yLocation = yLocation;
    }

    public float getDegree() {
        return degree;
    }

    public void setDegree(final float degree) {
        this.degree = degree;
    }

    public float getScaling() {
        return scaling;
    }

    public void setScaling(final float scaling) {
        this.scaling = scaling;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(final int order) {
        this.order = order;
    }
}
