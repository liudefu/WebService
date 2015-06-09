package com.zl.webService.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by Administrator on 2015/4/29.
 */
@XmlRootElement(name = "body")
@XmlAccessorType(XmlAccessType.NONE)
public class Body {
    @XmlElement(name = "student")
    private BusStudent busStudent;

    @XmlElement(name = "info")
    private String info;//返回信息
    @XmlElement(name = "result_flag")
    private boolean resultFlag;//返回结果

    public BusStudent getBusStudent() {
        return busStudent;
    }

    public void setBusStudent(BusStudent busStudent) {
        this.busStudent = busStudent;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public boolean isResultFlag() {
        return resultFlag;
    }

    public void setResultFlag(boolean resultFlag) {
        this.resultFlag = resultFlag;
    }
}
