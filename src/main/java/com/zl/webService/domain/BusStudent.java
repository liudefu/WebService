package com.zl.webService.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

/**
 * Created by Administrator on 2015/4/29.
 */
@XmlRootElement(name = "student")
@XmlAccessorType(XmlAccessType.NONE)
public class BusStudent {
    @XmlElement(name = "student_id")
    private String studentId;
    @XmlElement(name = "name")
    private String name;
    @XmlElement(name = "sex")
    private String sex;
    @XmlElement(name = "study_no")
    private String studyNo;
    @XmlElement(name = "birthday")
    private Date birthday;

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getStudyNo() {
        return studyNo;
    }

    public void setStudyNo(String studyNo) {
        this.studyNo = studyNo;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "BusStudent{" +
                "studentId=" + studentId +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", studyNo='" + studyNo + '\'' +
                ", birthday=" + birthday +
                '}';
    }
}
