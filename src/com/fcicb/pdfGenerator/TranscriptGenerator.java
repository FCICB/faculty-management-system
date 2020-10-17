package com.fcicb.pdfGenerator;

import com.fcicb.domain.Course;
import com.fcicb.domain.Student;
import com.fcicb.domain.StudentCourse;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class TranscriptGenerator {

    private GetTranscriptInfoImp getTranscriptInfoImpl;
    private Document document;
    private PdfWriter writer;
    private PdfReader reader;
    private PdfStamper stamper;
    private AcroFields acroFields;
    private Map fields;
    private static final String sourceFile = "resources/studentTranscriptTemplate.pdf";
    private static final String destinationFile = "resources/studentTranscript.pdf";

    public TranscriptGenerator() throws IOException, DocumentException {
        getTranscriptInfoImpl = new GetTranscriptInfoImp();
        document = new Document();
        reader = new PdfReader(sourceFile);
        stamper = new PdfStamper(reader, new FileOutputStream(destinationFile));
        acroFields = stamper.getAcroFields();

        File file = new File(destinationFile);
        file.getParentFile().mkdirs();
    }

    public void generateTranscript(int studentId) {
        try {
            writer = PdfWriter.getInstance(document, new FileOutputStream(destinationFile));
            Rectangle pageSize = reader.getPageSize(1);

            getTranscriptInfoImpl.queryStudentInfo(studentId);
            Student queryStudentInfo = getTranscriptInfoImpl.getStudent();

            fields = acroFields.getFields();
            if (fields != null) {
                acroFields.setField("Name", queryStudentInfo.getFname() + " " + queryStudentInfo.getLname());
                acroFields.setField("Level", String.valueOf(queryStudentInfo.getLevel()));
                acroFields.setField("GPA", String.valueOf(queryStudentInfo.getGpa()));
                acroFields.setField("TotalHours", String.valueOf(queryStudentInfo.getCompletedHours()));
            }

            PdfPTable table = new PdfPTable(3);

            PdfPCell CourseNameCell = new PdfPCell(new Phrase("Course Name"));
            PdfPCell CourseHoursCell = new PdfPCell(new Phrase("Course Hours"));
            PdfPCell CourseGradesCell = new PdfPCell(new Phrase("Course Grades"));

            table.addCell(CourseNameCell);
            table.addCell(CourseHoursCell);
            table.addCell(CourseGradesCell);

            table.setHeaderRows(1);

            getTranscriptInfoImpl.queryCourseInfo(studentId);
            ArrayList<StudentCourse> courses = getTranscriptInfoImpl.getCourses();

            for (StudentCourse course : courses) {
                PdfPCell courseName = new PdfPCell(new Phrase(course.getCourseName()));
                PdfPCell courseHours = new PdfPCell(new Phrase(String.valueOf(course.getCourseHours())));
                PdfPCell courseGrades = new PdfPCell(new Phrase(String.valueOf(course.getGrade())));
                table.addCell(courseName);
                table.addCell(courseHours);
                table.addCell(courseGrades);
            }

            ColumnText column = new ColumnText(stamper.getOverContent(1));
            column.setSimpleColumn(36, 36, 559, 540);
            column.addElement(table);

            int pagecount = 1;
            int status = column.go();
            while (ColumnText.hasMoreText(status)) {
                status = extendNewPage(stamper, pageSize, column, ++pagecount);
            }

            stamper.setFormFlattening(true);
            stamper.close();
            reader.close();

        } catch (DocumentException | FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int extendNewPage(PdfStamper stamper, Rectangle pagesize, ColumnText column, int pagecount) throws DocumentException {
        stamper.insertPage(pagecount, pagesize);
        PdfContentByte canvas = stamper.getOverContent(pagecount);
        column.setCanvas(canvas);
        column.setSimpleColumn(36, 36, 559, 806);
        return column.go();
    }

    public boolean isStudentInfoAvailable(int id) {
        return getTranscriptInfoImpl.checkStudent(id);
    }

}