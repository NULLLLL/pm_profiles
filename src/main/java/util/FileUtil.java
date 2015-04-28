package util;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioSystem;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 文件操作工具类
 */
public class FileUtil {

	private static final Logger logger = LoggerFactory.getLogger(FileUtil.class);

	/**
	 * 获取文件流
	 * @param path 文件路径
	 * @return InputStream
	 * @throws Exception
	 */
	public static InputStream getInputStream(String path) throws Exception {
		File file = new File(path);
		try {
			InputStream inputStream = new FileInputStream(file);
			return inputStream;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(LogUtil.stackTraceToString(e));
			return null;
		}
	}

	/**
	 * the traditional io way
	 * @param filename
	 * @return
	 * @throws IOException
	 */
	public static byte[] toByteArray(InputStream uploadFile) throws IOException {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		BufferedInputStream in = null;
		try {
			in = new BufferedInputStream(uploadFile);
			int buf_size = 1024;
			byte[] buffer = new byte[buf_size];
			int len = 0;
			while (-1 != (len = in.read(buffer, 0, buf_size))) {
				bos.write(buffer, 0, len);
			}
			return bos.toByteArray();
		} catch (IOException e) {
			logger.error(LogUtil.stackTraceToString(e));
			e.printStackTrace();
			throw e;
		} finally {
			try {
				if (in != null)
					in.close();
			} catch (IOException e) {
				logger.error(LogUtil.stackTraceToString(e));
				e.printStackTrace();
			}
			bos.close();
		}
	}

	/**
	 * 写文件
	 * @author wang
	 * @param path 生成的文件路径
	 * @param fileArray 文件内容
	 * @throws Exception
	 */
	public static void writeFile(String path, byte[] fileArray) throws Exception {
		try {
			File file = new File(path);
			if (!file.exists()) {
				file.createNewFile();
			}
			FileOutputStream foStream = new FileOutputStream(path);
			foStream.write(fileArray);
			foStream.close();
		} catch (Exception e) {
			logger.error(LogUtil.stackTraceToString(e));
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * 获取音频文件的播放时间
	 * @author wang
	 * @param 文件路径 filePath
	 * @throws Exception
	 * @return 秒
	 */
	public static long getMp3Time(String filePath) throws Exception {
		File file = new File(filePath);
		long time = 0l;
		AudioFileFormat audioFileFormat = AudioSystem.getAudioFileFormat(file);
		try {
			Map<?, ?> props = audioFileFormat.properties();
			if (props.containsKey("duration")) {
				time = Math.round((((Long) props.get("duration")).longValue()) / 1000 / 1000);
			}
		} catch (Exception e) {
			logger.error(LogUtil.stackTraceToString(e));
			e.printStackTrace();
		}
		return time;
	}

	/**
	 * 删除指定文件夹下所有文件
	 * @param path　
	 * @return
	 */
	public static boolean delAllFile(String path) {
		boolean flag = false;
		File file = new File(path);
		if (!file.exists())
			return flag;
		if (!file.isDirectory())
			return flag;
		String[] tempList = file.list();
		File temp = null;
		String newPath = null;
		if (tempList == null || tempList.length == 0)
			return flag;
		for (int i = 0; i < tempList.length; i++) {
			if (path.endsWith(File.separator))
				newPath = path + tempList[i];
			else
				newPath = path + File.separator + tempList[i];
			temp = new File(newPath);
			if (temp.isFile()) {
				temp.delete();
				continue;
			}
			if (temp.isDirectory())
				flag = delAllFile(newPath);//第归删除子文件夹里面的文件
		}
		logger.info("成功删除了" + tempList.length + "个文件！");
		return flag;
	}

	/** 
	 * 删除单个文件 
	 * @param   path    被删除文件的文件名 
	 * @return 单个文件删除成功返回true，否则返回false 
	 */
	public static boolean deleteFile(String path) {
		boolean flag = false;
		File file = new File(path);
		// 路径为文件且不为空则进行删除  
		if (file.isFile() && file.exists()) {
			file.delete();
			flag = true;
		}
		logger.info(" file delete as : " + path);
		return flag;
	}

	public static String getCellFormatValue(Cell cell) {
		String cellvalue = "";
		if (cell != null) {
			switch (cell.getCellType()) {
			case XSSFCell.CELL_TYPE_NUMERIC:
			case XSSFCell.CELL_TYPE_FORMULA:
				if (HSSFDateUtil.isCellDateFormatted(cell)) {
					Date date = cell.getDateCellValue();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					cellvalue = sdf.format(date);
				} else {
					cellvalue = String.valueOf(cell.getNumericCellValue());
					if (cellvalue.endsWith(".0"))
						cellvalue = cellvalue.replace(".0", "");
				}
				break;
			case XSSFCell.CELL_TYPE_STRING:
				cellvalue = cell.getRichStringCellValue().getString();
				break;
			default:
				cellvalue = "";
			}
		} else
			cellvalue = "";
		return cellvalue;
	}
}
