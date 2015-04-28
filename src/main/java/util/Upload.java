package util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Upload {

	private static final Logger logger = LoggerFactory.getLogger(Upload.class);

	public static String uploadFile(InputStream uploadFile, String destPath, String filename) throws Exception {
		try {
			String time = "(" + DateUtil.formatTimestampToStringByFmt(DateUtil.getNowTimestamp(), DateUtil.ADC_MSG_SID) + ")";
			String suffix = filename.substring(filename.lastIndexOf("."), filename.length());
			String newName = filename.substring(0, filename.lastIndexOf(".")) + time + suffix;
			File file = new File(destPath + newName);
			int available = uploadFile.available();
			byte[] b = new byte[available];
			FileOutputStream foutput = new FileOutputStream(file);
			uploadFile.read(b);
			foutput.write(b);
			foutput.flush();
			foutput.close();
			uploadFile.close();
			return newName;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(LogUtil.stackTraceToString(e));
			return "";
		}

	}

}
