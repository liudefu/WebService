package com.zl.webService.service.impl;


import com.zl.webService.service.ServiceException;
import com.zl.webService.entity.Student;
import com.zl.webService.service.StudentService;
import com.zl.webService.Constants;
import com.zl.webService.util.Configuration;
import com.zl.webService.domain.Body;
import com.zl.webService.domain.Root;
import com.zl.webService.service.BusinessService;
import com.zl.webService.service.WebServiceInterface;
import com.zl.webService.xml.XmlTransForm;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.bind.JAXBException;
import java.util.List;

/**
 * @author <a href="mailto:luckylucky3210@163.com">ZL</a>
 * @version $Id$
 *          Date: 2013-12-9
 *          Time: 21:04:44
 */
@WebService(targetNamespace = "http://service.zl.com/",
        endpointInterface = "com.zl.webService.service.WebServiceInterface")
public class WebServiceInterfaceImpl implements WebServiceInterface {
    private BusinessService businessService;

    public BusinessService getBusinessService() {
        return businessService;
    }

    public void setBusinessService(BusinessService businessService) {
        this.businessService = businessService;
    }

    @WebMethod
    public String xmlBusiness(String xmlString) {
        String returnXml = "";
        try {
            Root root = (Root) XmlTransForm.xmlToObj(xmlString, Root.class);
            if(root.getHead() == null){
                //报文头不存在
                returnXml = serviceExceptionXml(new ServiceException(
                        Configuration.getInstance().getValue(
                                Constants.XML_HEAD_NOT_EXIT)));
            }else if(root.getBody() == null){
                //报文体不存在
                returnXml = serviceExceptionXml(new ServiceException(
                        Configuration.getInstance().getValue(
                                Constants.XML_BODY_NOT_EXIT)));
            }else{
                getBusinessService().business(root);
                returnXml = XmlTransForm.objToXml(root);
            }
        } catch (ServiceException e) {
            returnXml = serviceExceptionXml(e);
        } catch (Exception e){
            returnXml = unknownExceptionXml(e);
        }
        return returnXml;
    }

    @WebMethod
    public List searchStudent(Student student, int pageNumber) {
        return null;
    }


    private String serviceExceptionXml(ServiceException e){
        Root root = new Root();
        Body body = new Body();
        root.setBody(body);
        body.setInfo(e.getMessage());
        body.setResultFlag(false);
        return XmlTransForm.objToXml(root);
    }

    private String unknownExceptionXml(Exception e){
        Root root = new Root();
        Body body = new Body();
        root.setBody(body);
        body.setInfo(e.getMessage());
        body.setResultFlag(false);
        return XmlTransForm.objToXml(root);
    }
}
