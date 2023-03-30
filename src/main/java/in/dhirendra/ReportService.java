package in.dhirendra;

import java.io.IOException;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class ReportService {
	@Autowired
	private CourseRepository CourseRepo;

	public void generateExcel(HttpServletResponse response) throws IOException {
		List<Course> courses = CourseRepo.findAll();
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet Sheet = workbook.createSheet("Courses Info");
		HSSFRow row = Sheet.createRow(0);

		row.createCell(0).setCellValue("Cid");
		row.createCell(1).setCellValue("cname");
		row.createCell(2).setCellValue("cprice");

		int dataRowIndex = 1;
		for (Course course : courses) {
			HSSFRow dataRow = Sheet.createRow(dataRowIndex);
			dataRow.createCell(0).setCellValue(course.getCid());
			dataRow.createCell(1).setCellValue(course.getCname());
			dataRow.createCell(2).setCellValue(course.getCprice());
			dataRowIndex ++;
		}
		 
		 ServletOutputStream ops= response.getOutputStream();
		 workbook.write(ops);
		 workbook.close();
		 ops.close();
	}

}
