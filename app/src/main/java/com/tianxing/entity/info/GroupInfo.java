package com.tianxing.entity.info;

import android.util.Log;

/**
 * Created by tianxing on 16/7/22.
 * 单个会话群组信息
 */
public class GroupInfo implements InfoEntity{
    private String roomName = "";


    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        Log.e("GroupInfo", "存入房间名 " + roomName);
        this.roomName = roomName;
    }
}
