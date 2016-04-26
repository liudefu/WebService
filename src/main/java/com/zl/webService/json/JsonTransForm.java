package com.zl.webService.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zl.webService.Constants;
import com.zl.webService.entity.Student;
import com.zl.webService.service.ServiceException;
import com.zl.webService.util.Configuration;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.IOException;
import java.util.Date;

/**
 * @author <a href="mailto:luckylucky3210@163.com">ZL</a>
 * @version $Id$
 *          Date: 2016/4/26
 *          Time: 11:51
 */
public class JsonTransForm {
    public static final Log LOG = LogFactory.getLog(JsonTransForm.class);
    private static ObjectMapper mapper;

    static {
        mapper = new ObjectMapper();
    }

    /**
     * 报文解析
     * @param jsonString
     * @param type
     * @return
     */
    public static Object jsonToObj(String jsonString,Class type){
        LOG.info("接收的报文是：" + jsonString);
        try {
            return mapper.readValue(jsonString, type);
        } catch (IOException e) {
            e.printStackTrace();
            throw new ServiceException(Configuration.getInstance().getValue(
                    Constants.JSON_UNMARSHALLER_ERROR));
        }
    }

    /**
     * 构造报文
     * @param object
     * @return
     */
    public static String objToJson(Object object){
        try {
            String jsonString = mapper.writeValueAsString(object);
            LOG.info("发送的报文是" + jsonString);
            return jsonString;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new ServiceException(Configuration.getInstance().getValue(
                    Constants.JSON_MARSHALLER_ERROR));
        }
    }
    public static void main(String[] args){
        Student student = new Student();
        student.setName("小王");
        student.setSex("男");
        student.setStudentId(1);
        student.setStudyNo("2009093101");
        student.setBirthday(new Date());
        System.out.println(JsonTransForm.objToJson(student));
    }
}
