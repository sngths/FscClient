package com.tianxing.entity.info;

/**
 * Created by tianxing on 16/7/22.
 * 单个会话群组信息
 */
public class GroupInfo implements InfoEntity{
    private String roomName = "";
    private String roomTitle;


    public String getRoomName() {
        return roomName;
    }

    public String getRoomTitle() {
        return roomTitle;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }
}
