package com.tianxing.data;

import com.tianxing.model.communication.xmpp.XmppServerInfo;

/**
 * Created by tianxing on 16/7/9.
 */
public class Config {

    private LocalConfig localConfig = null;

    private XmppServerInfo xmppServerInfo;


    public void setLocalConfig(LocalConfig localConfig) {
        this.localConfig = localConfig;
    }

    public LocalConfig getLocalConfig() {
        return localConfig;
    }

    public XmppServerInfo getXmppServerInfo() {
        return xmppServerInfo;
    }

    public void setXmppServerInfo(XmppServerInfo xmppServerInfo) {
        this.xmppServerInfo = xmppServerInfo;
    }
}
