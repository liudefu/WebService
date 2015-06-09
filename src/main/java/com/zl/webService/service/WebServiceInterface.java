package com.zl.webService.service;

import com.zl.webService.entity.Student;

import javax.jws.WebService;
import java.util.List;

/**
 * @author <a href="mailto:luckylucky3210@163.com">ZL</a>
 * @version $Id$
 *          Date: 2013-12-9
 *          Time: 21:02:19
 */
@WebService
public interface WebServiceInterface {
    String xmlBusiness(String xmlString);

    List searchStudent(Student student, int pageNumber);

}
