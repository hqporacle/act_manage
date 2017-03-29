package com.clemson.service;

import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

import org.apache.avalon.framework.configuration.ConfigurationException;
import org.apache.avalon.framework.configuration.DefaultConfiguration;
import org.krysalis.barcode4j.BarcodeException;
import org.krysalis.barcode4j.BarcodeGenerator;
import org.krysalis.barcode4j.BarcodeUtil;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clemson.controller.MobileController;
import com.clemson.mappers.EnrollMapper;
import com.clemson.mappers.TestArrangeMapper;
import com.clemson.mappers.TestSchoolMapper;
import com.clemson.mappers.TestSchoolRetestMapper;
import com.clemson.model.Enroll;
import com.clemson.model.TestArrange;
import com.clemson.model.TestSchoolRetest;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

@Service("EnrollService")
public class EnrollServiceImpl implements EnrollService {
	@Autowired
	private EnrollMapper enrollMapper;
	@Autowired
	private TestSchoolMapper testSchoolMapper;
	@Autowired
	private TestArrangeMapper testArrangeMapper;
	@Autowired
	private TestSchoolRetestMapper testSchoolRetestMapper;
	
	public void insertEnroll(Enroll enroll) {
		enrollMapper.insertEnroll(enroll);
	}
	
	public ArrayList<Enroll> getEnrollByStudentId(int studentId) {
		ArrayList<Enroll> enrolls = enrollMapper.getEnrollByStudentId(studentId);
		
		// 如果是待选考场状态，注入符合条件的考场列表
		for (Enroll enroll : enrolls) {
			if (enroll.getEnrollStatus() == 1) {
				enroll.setSchoolIdAndName(testSchoolMapper.getSchoolIdAndNameByTestId(enroll.getTestId()));
			}
		}
		return enrolls;
	}

	public ArrayList<Enroll> getAllEnroll() {
		return enrollMapper.getAllEnroll();
	}
	
	public Enroll getEnrollByEnrollNum(Enroll enroll) {
		return enrollMapper.getEnrollByEnrollNum(enroll);
	}
	
	public void setEnrollStatusByEnrollNum(Enroll enroll) {
		enrollMapper.setEnrollStatusByEnrollNum(enroll);
	}
	
	public void setSchoolIdByEnrollNum(Enroll enroll) {
		enrollMapper.setSchoolIdByEnrollNum(enroll);
	}
	
	public void setTestNumByEnrollNum(Enroll enroll) {
		enrollMapper.setTestNumByEnrollNum(enroll);
	}
	
	public void generateTestNum(Enroll enroll) {
		String part1 = String.format("%04d", enroll.getEnrollNum());
		String part2 = String.format("%04d", enroll.getStudentId());
		String part3 = String.format("%02d", enroll.getTestId());
		String part4 = String.format("%02d", enroll.getSchoolId());
		enroll.setTestNum(part1 + part2 + part3 + part4);
	}
	
	public void generatePDF(Enroll enroll){
		// Barcode EAN-13 (12 digits only!)
		DefaultConfiguration cfg = new DefaultConfiguration("barcode");

	    //Bar code type
	    DefaultConfiguration child = new DefaultConfiguration("ean-13");
	    cfg.addChild(child);
	    
	    //Human readable text position
	    DefaultConfiguration attr = new DefaultConfiguration("human-readable");
	    DefaultConfiguration subAttr = new DefaultConfiguration("placement");
	    subAttr.setValue("bottom");
	    attr.addChild(subAttr);
	        
	    child.addChild(attr);
		
		BarcodeUtil util = BarcodeUtil.getInstance();
	    BarcodeGenerator gen = null;
		try {
			gen = util.createBarcodeGenerator(cfg);
			OutputStream fout = new FileOutputStream(MobileController.class.getClassLoader().getResource("").getPath() + "pdf/ean-13.jpg");
		    int resolution = 200;
		    BitmapCanvasProvider canvas = new BitmapCanvasProvider(
		        fout, "image/jpeg", resolution, BufferedImage.TYPE_BYTE_BINARY, false, 0);

		    // 12 digits!
		    gen.generateBarcode(canvas, enroll.getTestNum());
		    canvas.finish();
		} catch (ConfigurationException e) {
			e.printStackTrace();
		} catch (BarcodeException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// PDF
		Document document = new Document();
		try {
			PdfWriter.getInstance(document, new FileOutputStream(MobileController.class.getClassLoader().getResource("").getPath() + "pdf/" + enroll.getTestNum() + ".pdf"));
			
			document.open();
			
			BaseFont bfChinese = BaseFont.createFont(MobileController.class.getClassLoader().getResource("").getPath() + "pdf/SIMKAI.TTF", 
	                BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
	        Font FontChinese = new Font(bfChinese, 12, Font.NORMAL);

			PdfPTable table = new PdfPTable(2);  
			PdfPCell cell;
			cell = new PdfPCell(new Paragraph(enroll.getTestName() + " 准考证", FontChinese));  
			cell.setColspan(2);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);  
			table.addCell(cell);  

			table.addCell(new PdfPCell(new Paragraph("姓名", FontChinese)));
			table.addCell(new PdfPCell(new Paragraph(enroll.getStudentName(), FontChinese)));
			
			table.addCell(new PdfPCell(new Paragraph("性别", FontChinese)));
			table.addCell(new PdfPCell(new Paragraph(enroll.getStudentNation(), FontChinese)));
			
			table.addCell(new PdfPCell(new Paragraph("生日", FontChinese)));
			table.addCell(new PdfPCell(new Paragraph(enroll.getStudentBirthday(), FontChinese)));
			
			table.addCell(new PdfPCell(new Paragraph("民族", FontChinese)));
			table.addCell(new PdfPCell(new Paragraph(enroll.getStudentNation(), FontChinese)));
			
			table.addCell(new PdfPCell(new Paragraph("身份证号", FontChinese)));
			table.addCell(new PdfPCell(new Paragraph(enroll.getIdcNumber(), FontChinese)));
			
			table.addCell(new PdfPCell(new Paragraph("高考报名号", FontChinese)));
			table.addCell(new PdfPCell(new Paragraph(enroll.getCeeNumber(), FontChinese)));
			
			table.addCell(new PdfPCell(new Paragraph("艺术号", FontChinese)));
			table.addCell(new PdfPCell(new Paragraph(enroll.getArtNumber(), FontChinese)));
			
			table.addCell(new PdfPCell(new Paragraph("考试名称", FontChinese)));
			table.addCell(new PdfPCell(new Paragraph(enroll.getTestName(), FontChinese)));
			
			table.addCell(new PdfPCell(new Paragraph("考试大类", FontChinese)));
			table.addCell(new PdfPCell(new Paragraph(enroll.getTypeName(), FontChinese)));
			
			table.addCell(new PdfPCell(new Paragraph("考试小类", FontChinese)));
			table.addCell(new PdfPCell(new Paragraph(enroll.getSubTypeName(), FontChinese)));
			
			table.addCell(new PdfPCell(new Paragraph("考点名称", FontChinese)));
			table.addCell(new PdfPCell(new Paragraph(enroll.getSchoolName(), FontChinese)));
			
			table.addCell(new PdfPCell(new Paragraph("考点地址", FontChinese)));
			table.addCell(new PdfPCell(new Paragraph(enroll.getSchoolAddress(), FontChinese)));
			
			table.addCell(new PdfPCell(new Paragraph("准考证号", FontChinese)));
			table.addCell(new PdfPCell(new Paragraph(enroll.getTestNum(), FontChinese)));
			
			TestArrange testArrange = new TestArrange();
			testArrange.setSchoolId(enroll.getSchoolId());
			testArrange.setTestId(enroll.getTestId());
			ArrayList<TestArrange> testArranges = testArrangeMapper.getTestArrangeByTestIdAndSchoolId(testArrange);
			
			int i = 1;
			for (TestArrange theTestArrange : testArranges) {
				String line1Title = "科目" + i + "名称";
				table.addCell(new PdfPCell(new Paragraph(line1Title, FontChinese)));
				table.addCell(new PdfPCell(new Paragraph(theTestArrange.getSubjectName(), FontChinese)));
				
				String line2Title = "科目" + i + "教室";
				table.addCell(new PdfPCell(new Paragraph(line2Title, FontChinese)));
				table.addCell(new PdfPCell(new Paragraph(theTestArrange.getClassNo(), FontChinese)));
				
				String line3Title = "科目" + i + "日期";
				table.addCell(new PdfPCell(new Paragraph(line3Title, FontChinese)));
				table.addCell(new PdfPCell(new Paragraph(theTestArrange.getDate(), FontChinese)));
				
				i++;
			}
			
			TestSchoolRetest testSchoolRetest = new TestSchoolRetest();
			testSchoolRetest.setTestId(enroll.getTestId());
			testSchoolRetest.setSchoolId(enroll.getSchoolId());
			testSchoolRetest = testSchoolRetestMapper.getTestSchoolRetestByTestIdAndSchoolId(testSchoolRetest);
			
			table.addCell(new PdfPCell(new Paragraph("复试教室", FontChinese)));
			table.addCell(new PdfPCell(new Paragraph(testSchoolRetest.getClassNo(), FontChinese)));
			
			table.addCell(new PdfPCell(new Paragraph("复试开始时间", FontChinese)));
			table.addCell(new PdfPCell(new Paragraph(testSchoolRetest.getRetestStartTime(), FontChinese)));
			
			table.addCell(new PdfPCell(new Paragraph("复试结束时间", FontChinese)));
			table.addCell(new PdfPCell(new Paragraph(testSchoolRetest.getRetestEndTime(), FontChinese)));
			
			Image image = Image.getInstance(MobileController.class.getClassLoader().getResource("").getPath() + "pdf/ean-13.jpg");
			table.addCell(new PdfPCell(new Paragraph("准考证号条形码", FontChinese)));
			table.addCell(image);
			
			document.add(table);  
			
			document.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return;
	}
	
	public void insertEnrolls(ArrayList<Enroll> enrollList) {
		for (Enroll enroll : enrollList) {
			enrollMapper.insertEnroll(enroll);
		}
		return;
	}
	
	public ArrayList<Enroll> getEnrollBySchoolId(int schoolId) {
		return enrollMapper.getEnrollBySchoolId(schoolId);
	}
}
