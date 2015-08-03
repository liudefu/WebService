package com.zl.webService.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 * Created by Administrator on 2015/8/3.
 */
public class ZipManager {
    public final static Log LOG = LogFactory.getLog(ZipManager.class);
    //是否需要压缩/解压
    private boolean needZip;
    //压缩文件名
    private static final String ZIP_FILE_NAME = "zl.XML";

    public boolean isNeedZip() {
        return needZip;
    }

    public void setNeedZip(boolean needZip) {
        this.needZip = needZip;
    }

    /**
     * 压缩
     *
     * @param zippedContent
     * @return 压缩失败返回null
     */
    public byte[] zip(String zippedContent) {
        if (isNeedZip()) {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ZipOutputStream zip = new ZipOutputStream(
                    new BufferedOutputStream(byteArrayOutputStream));
            byte[] retVal = null;
            try {
                zip.putNextEntry(new ZipEntry(ZIP_FILE_NAME));
                zip.write(zippedContent.getBytes());
                zip.flush();
                byteArrayOutputStream.close();
                zip.close();
                retVal = byteArrayOutputStream.toByteArray();
            } catch (IOException e) {
                LOG.warn("Can't zip the input content:" + zippedContent, e);
            }
            return retVal;
        }
        return zippedContent.getBytes();
    }

    /**
     * 解压
     *
     * @param content
     * @return
     */
    public String unZip(byte[] content) {
        //TODO:find the error decoding bug and delete
        if (isNeedZip()) {
            StringBuffer unCompressedData = new StringBuffer();
            try {
                ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(
                        content);
                InputStream zipStream = new BufferedInputStream(
                        byteArrayInputStream);
                ZipInputStream zipInputStream = new ZipInputStream(zipStream);
                ZipEntry zipEntry = null;
                while ((zipEntry = zipInputStream.getNextEntry()) != null) {
                    while (true) {
                        byte[] b = new byte[100];
                        int count = -1;
                        if ((count = zipInputStream.read(b)) != -1) {
                            unCompressedData.append(new String(b, 0, count));
                        } else {
                            break;
                        }
                    }
                    LOG.debug("The unzipped content is : " + unCompressedData);
                }
            } catch (IOException e) {
                LOG.warn("Can't unzip the input content", e);
            }
            return unCompressedData.toString();
        } else {
            return new String(content);
        }
    }
}
