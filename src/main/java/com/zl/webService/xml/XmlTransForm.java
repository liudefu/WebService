package com.zl.webService.xml;

import com.zl.webService.service.ServiceException;
import com.zl.webService.Constants;
import com.zl.webService.util.Configuration;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;

/**
 * @author <a href="mailto:luckylucky3210@163.com">ZL</a>
 * @version $Id$
 *          Date: 2013-12-9
 *          Time: 22:08:30
 */
public class XmlTransForm {
    public static final Log LOG = LogFactory.getLog(XmlTransForm.class);

    private static JAXBContext publicCtx;
    private static Unmarshaller unmarshaller;
    private static Marshaller marshaller;
    /**
     * 报文转换为对象
     *
     * @param xmlString
     * @param type
     * @return
     * @throws JAXBException
     */
    public static Object xmlToObj(String xmlString, Class type) {
        LOG.debug("接收的报文：\n" + xmlString);
        Object object = new Object();
        try {
            publicCtx = JAXBContext.newInstance(type);
            unmarshaller = publicCtx.createUnmarshaller();
            object = unmarshaller.unmarshal(new StringReader(xmlString));
        } catch (JAXBException e) {
            e.printStackTrace();
            throw new ServiceException(
                    Configuration.getInstance().getValue(
                            Constants.XML_UNMARSHALLER_ERROR));
        }
        return object;
    }

    /**
     * 对象转换为报文
     *
     * @param obj
     * @return
     * @throws JAXBException
     */
    public static String objToXml(Object obj){
        StringWriter tempString = new StringWriter();
        String xmlString = "";
        try {
            publicCtx = JAXBContext.newInstance(obj.getClass());
            marshaller = publicCtx.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
            marshaller.marshal(obj, tempString);
            xmlString = tempString.toString();
            LOG.debug("发送的报文：\n" + xmlString);
        } catch (JAXBException e) {
            e.printStackTrace();
            throw new ServiceException(
                    Configuration.getInstance().getValue(
                            Constants.XML_MARSHALLER_ERROR));
        }
        return xmlString;
    }
}
