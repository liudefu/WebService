package com.zl.webService.service.impl;

import com.zl.webService.entity.Student;
import com.zl.webService.service.StudentService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.Date;


public class StudentServiceImplTest {

    private static Log LOG = LogFactory.getLog(StudentServiceImplTest.class);
    private StudentService studentService;

    private String[] getConfigLocations() {
        return new String[]{"applicationContext-student.xml"};
    }

    @BeforeTest
    public void setUp() {
        ApplicationContext atx = new ClassPathXmlApplicationContext(
                getConfigLocations());
        studentService = (StudentService) atx.getBean("studentService");
    }

    @Test
    public void testCreateStudent() throws Exception {
        Student student;
        for (int i = 10; i < 50; i++) {
            student = new Student();
            student.setName("name" + i);
            student.setSex(i % 2 + "");
            student.setStudyNo("20090931" + i);
            student.setBirthday(new Date());
            LOG.info(studentService.createStudent(student));
        }
    }

    @Test
    public void testSearchStudent() {
        Student student = new Student();
        //student.setStudentId (117);
        LOG.info(studentService.searchStudent(student, 2, 15));
    }
    @Test
    public void testModifyStudent() {
        Student student = new Student();
        student.setStudentId (117);
        student.setName("1234");
        student.setSex("1");
        student.setStudyNo ("2009093134");
        studentService.modifyStudent(student);
    }
    @Test
    public void testDeleteStudent() {
        Student student;
        student = new Student();
        student.setStudentId(800);
        studentService.deleteStudent(student);
    }
}