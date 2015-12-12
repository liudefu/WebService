package com.zl.webService.service.impl;

import com.zl.webService.annotation.Action;
import com.zl.webService.annotation.BusinessComponent;
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
import java.lang.reflect.Method;
import java.util.Map;

/**
 * Created by Administrator on 2015/4/29.
 */
@BusinessComponent(name="BusinessComponent")
public class BusinessServiceImpl implements BusinessService{
    public static final Log LOG = LogFactory.getLog(BusinessServiceImpl.class);

    /**
     * Action所对应的方法的集合
     */
    private Map<String, Method> methodContexts;

    private StudentService studentService;

    public Map<String, Method> getMethodContexts() {
        return methodContexts;
    }

    public void setMethodContexts(Map<String, Method> methodContexts) {
        this.methodContexts = methodContexts;
    }

    public StudentService getStudentService() {
        return studentService;
    }

    public void setStudentService(StudentService studentService) {
        this.studentService = studentService;
    }

    public void business(Root root) throws Exception{

        Head head = root.getHead();
        Body body = root.getBody();
        Method method = methodContexts.get(head.getBusCode());
        if(method != null){
            method.invoke(this,body);
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
    @Action(busCode = "1001",busName = "新增学生信息")
    private void createStudent(Body body) throws Exception{
        Student student = new Student();
        try {
            org.apache.commons.beanutils.BeanUtils.copyProperties(
                    student,body.getBusStudent());
        } catch (Exception e) {
            e.printStackTrace();
            LOG.error(Configuration.getInstance().getValue(
                    Constants.BUS_STUDENT_INFO_ERROR));
            throw new ServiceException(
                    Configuration.getInstance().getValue(
                            Constants.BUS_STUDENT_INFO_ERROR));
        }
        studentService.create(student);
    }
    @Action(busCode = "1002",busName = "修改学生信息")
    private void modifyStudent(Body body) throws Exception{
        Student student = new Student();
        try {
            org.apache.commons.beanutils.BeanUtils.copyProperties(
                    student,body.getBusStudent());
        } catch (Exception e) {
            e.printStackTrace();
            LOG.error(Configuration.getInstance().getValue(
                    Constants.BUS_STUDENT_INFO_ERROR));
            throw new ServiceException(
                    Configuration.getInstance().getValue(
                            Constants.BUS_STUDENT_INFO_ERROR));
        }
        studentService.update(student);
    }
    @Action(busCode = "1003",busName = "删除学生信息")
    private void deleteStudent(Body body) throws Exception{
        Student student = new Student();
        try {
            org.apache.commons.beanutils.BeanUtils.copyProperties(
                    student,body.getBusStudent());
        } catch (Exception e) {
            e.printStackTrace();
            LOG.error(Configuration.getInstance().getValue(
                    Constants.BUS_STUDENT_INFO_ERROR));
            throw new ServiceException(
                    Configuration.getInstance().getValue(
                            Constants.BUS_STUDENT_INFO_ERROR));
        }
        studentService.delete(student);
    }
}
