package com.zl.webService.entity;

import java.util.Date;

/**
 * @author <a href="mailto:luckylucky3210@163.com">ZL</a>
 * @version $Id$
 *          Date: 2013-12-7
 *          Time: 9:42:57
 */
public class Student {

    private long studentId;
    private String name;
    private String sex;
    private String studyNo;
    private Date birthday;

    public long getStudentId() {
        return studentId;
    }

    public void setStudentId(long studentId) {
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Student student = (Student) o;

        if (studentId != student.studentId) return false;
        if (birthday != null ? !birthday.equals(student.birthday) : student.birthday != null) return false;
        if (name != null ? !name.equals(student.name) : student.name != null) return false;
        if (sex != null ? !sex.equals(student.sex) : student.sex != null) return false;
        if (studyNo != null ? !studyNo.equals(student.studyNo) : student.studyNo != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (studentId ^ (studentId >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (sex != null ? sex.hashCode() : 0);
        result = 31 * result + (studyNo != null ? studyNo.hashCode() : 0);
        result = 31 * result + (birthday != null ? birthday.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId=" + studentId +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", studyNo='" + studyNo + '\'' +
                ", birthday=" + birthday +
                '}';
    }
}
