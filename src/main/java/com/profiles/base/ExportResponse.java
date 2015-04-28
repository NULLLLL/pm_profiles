package com.profiles.base;

import java.io.BufferedOutputStream;
import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class ExportResponse {

	public static void export(HttpServletResponse response, HSSFWorkbook hssfWorkbook, String fileName) {
		BufferedOutputStream outputStream = null;
		try {
			byte[] bytes = hssfWorkbook.getBytes();
			response.reset();
			response.addHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes("utf-8"), "ISO-8859-1"));
			response.addHeader("Content-Length", "" + bytes.length);
			response.setContentType("application/octet-stream");
			outputStream = new BufferedOutputStream(response.getOutputStream());
			outputStream.write(bytes);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (outputStream != null)
				try {
					outputStream.flush();
					outputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}

}
