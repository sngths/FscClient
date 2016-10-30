package com.tianxing.entity.info;

/**
 * Created by tianxing on 16/7/22.
 * 单个会话群组信息
 */
public class GroupInfo implements InfoEntity{
    private String roomID;
    private String roomName;


    public String getRoomID() {
        return roomID;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomID(String roomID) {
        this.roomID = roomID;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }
}
