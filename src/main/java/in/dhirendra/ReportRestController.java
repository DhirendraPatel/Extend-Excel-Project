package in.dhirendra;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletResponse;

@RestController
public class ReportRestController 
{
	@Autowired
	private ReportService reportService;
	
	
	@GetMapping("/excel")
	public void generateExcelReport(HttpServletResponse response) throws Exception 
	{
		response.setContentType("application/octel-stream");
		String headerkey = "Content-Disposition";
		String headerValue = "attachment;filename=course.xls";
		
		response.setHeader(headerkey, headerValue);
		
		reportService.generateExcel(response);
	}
}
