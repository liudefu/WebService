package com.zl.webService.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by Administrator on 2015/4/29.
 */
@XmlRootElement(name = "head")
@XmlAccessorType(XmlAccessType.NONE)
public class Head {
    @XmlElement(name = "bus_code")
    private String busCode;//
    @XmlElement(name = "bus_name")
    private String busName;

    public String getBusCode() {
        return busCode;
    }

    public void setBusCode(String busCode) {
        this.busCode = busCode;
    }

    public String getBusName() {
        return busName;
    }

    public void setBusName(String busName) {
        this.busName = busName;
    }
}
