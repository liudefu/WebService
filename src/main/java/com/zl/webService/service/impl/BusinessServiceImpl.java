package com.zl.webService.service.impl;

import com.zl.webService.service.ServiceException;
import com.zl.webService.entity.Student;
import com.zl.webService.service.StudentService;
import com.zl.webService.Constants;
import com.zl.webService.util.Configuration;
import com.zl.webService.domain.Body;
import com.zl.webService.domain.BusStudent;
import com.zl.webService.domain.Head;
import com.zl.webService.domain.Root;
import com.zl.webService.service.BusinessService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.lang.reflect.InvocationTargetException;

/**
 * Created by Administrator on 2015/4/29.
 */
public class BusinessServiceImpl implements BusinessService{
    public static final Log LOG = LogFactory.getLog(BusinessServiceImpl.class);
    private StudentService studentService;

    public StudentService getStudentService() {
        return studentService;
    }

    public void setStudentService(StudentService studentService) {
        this.studentService = studentService;
    }

    public void business(Root root) throws Exception{
        Head head = root.getHead();
        Body body = root.getBody();
        if("1001".equals(head.getBusCode())){
            //新增学生信息
            createStudent(body);
            head.setBusCode("2001");
        }else if("1002".equals(head.getBusCode())){
            //修改学生信息
            modifyStudent(body);
            head.setBusCode("2002");
        }else if("1003".equals(head.getBusCode())){
            deleteStudent(body);
            head.setBusCode("2003");
        }else {
            LOG.error(Configuration.getInstance().getValue(
                    Constants.XML_BUSY_CODE_NOT_EXIT));
            throw new ServiceException(
                    Configuration.getInstance().getValue(
                            Constants.XML_BUSY_CODE_NOT_EXIT));
        }
        body.setResultFlag(true);
        body.setInfo("操作成功");
    }

    /**
     * 修改学生信息
     * @param body
     */
    private void createStudent(Body body) throws Exception{
        Student student = new Student();
        try {
            org.apache.commons.beanutils.BeanUtils.copyProperties(
                    student,body.getBusStudent());
        } catch (Exception e) {
            LOG.error(Configuration.getInstance().getValue(
                    Constants.BUS_STUDENT_INFO_ERROR));
            throw new ServiceException(
                    Configuration.getInstance().getValue(
                            Constants.BUS_STUDENT_INFO_ERROR));
        }
        studentService.createStudent(student);
    }

    private void modifyStudent(Body body) throws Exception{
        Student student = new Student();
        try {
            org.apache.commons.beanutils.BeanUtils.copyProperties(
                    student,body.getBusStudent());
        } catch (Exception e) {
            LOG.error(Configuration.getInstance().getValue(
                    Constants.BUS_STUDENT_INFO_ERROR));
            throw new ServiceException(
                    Configuration.getInstance().getValue(
                            Constants.BUS_STUDENT_INFO_ERROR));
        }
        studentService.modifyStudent(student);
    }

    private void deleteStudent(Body body) throws Exception{
        Student student = new Student();
        try {
            org.apache.commons.beanutils.BeanUtils.copyProperties(
                    student,body.getBusStudent());
        } catch (Exception e) {
            LOG.error(Configuration.getInstance().getValue(
                    Constants.BUS_STUDENT_INFO_ERROR));
            throw new ServiceException(
                    Configuration.getInstance().getValue(
                            Constants.BUS_STUDENT_INFO_ERROR));
        }
        studentService.deleteStudent(student);
    }
}
