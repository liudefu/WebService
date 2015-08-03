package com.zl.webService.util;

/**
 * Created by Administrator on 2015/8/3.
 */

import com.zl.webService.entity.Student;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.supercsv.io.CsvListReader;
import org.supercsv.io.CsvListWriter;
import org.supercsv.io.ICsvListWriter;
import org.supercsv.prefs.CsvPreference;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * CSV文件的读写
 */

public class CsvHelper {

    public static final Log LOG = LogFactory.getLog(CsvHelper.class);
    private static final String EMPTY_STRING = "";
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static List parseStudents(String csvFile) {
        List<Student> students = new ArrayList<Student>();
        Reader reader = new StringReader(csvFile);
        try {
            CsvListReader csvListReader = new CsvListReader(reader,
                    CsvPreference.EXCEL_PREFERENCE);
            //读取header，但此处忽略
            csvListReader.getHeader(true);
            List<String> row;
            while ((row = csvListReader.read()) != null) {
                Student student = new Student();
                student.setStudentId(Long.parseLong(row.get(0)));
                student.setName(row.get(1));
                student.setSex(row.get(2));
                student.setStudyNo(row.get(3));
                student.setBirthday(sdf.parse(row.get(4)));
                students.add(student);
            }
            if (LOG.isInfoEnabled()) {
                LOG.info("Medical size is: " + students.size());
            }
        } catch (Exception e) {
            if (LOG.isWarnEnabled()) {
                LOG.warn("Can't parse CSV file.", e);
            }
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                if (LOG.isWarnEnabled()) {
                    LOG.warn("Can't close CSV reader.", e);
                }
            }
        }

        return students;
    }

    /**
     * 生成公司医疗目录的CSV内容的生成
     */
    public static String generateCsvFromMedicalCodesWithForCompany(List medicalCodes) {
        final StringWriter stringWriter = new StringWriter();
        ICsvListWriter csvWriter = new CsvListWriter(stringWriter,
                CsvPreference.EXCEL_PREFERENCE);

        try {
            //定义列名
            String[] header = new String[]
                    {"studentId", "name", "sex", "studyNo", "birthday"};

            //写入列名
            csvWriter.writeHeader(header);
            //写入数据
            for (Iterator iterator = medicalCodes.iterator(); iterator.hasNext(); ) {
                Student student = (Student) iterator.next();
                csvWriter.write(
                        student.getStudentId(),
                        checkAndReplaceNull(student.getName()),
                        checkAndReplaceNull(student.getSex()),
                        checkAndReplaceNull(student.getStudyNo()),
                        checkAndReplaceNull(student.getBirthday())
                );
            }

        } catch (Exception e) {
            if (LOG.isWarnEnabled()) {
                LOG.warn("Can't write CSV file.", e);
            }
        } finally {
            try {
                csvWriter.close();
            } catch (IOException e) {
                if (LOG.isWarnEnabled()) {
                    LOG.warn("Can't close CSV writer.", e);
                }
            }
        }

        return stringWriter.toString();

    }

    private static String checkAndReplaceNull(String originalString) {
        return originalString == null ? EMPTY_STRING : originalString;
    }

    private static Date checkAndReplaceNull(Date originalDate) {
        return originalDate == null ? new Date() : originalDate;
    }
}
