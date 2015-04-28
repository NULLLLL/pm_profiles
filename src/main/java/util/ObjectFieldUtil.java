package util;

import java.lang.reflect.Field;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ObjectFieldUtil {

	private static final Logger logger = LoggerFactory.getLogger(ObjectFieldUtil.class);

	/**
	 * 获取指定的对象的指定列的值
	 * @param clazz 类
	 * @param object 对象
	 * @param name 列名
	 * @return 指定列的值
	 * @throws Exception
	 */
	public static String getValue(Class<?> clazz, Object object, String name) throws Exception {
		try {
			String newName1 = StringHelper.removeBlank(name.replaceAll("（", "").replaceAll("）", ""));
			String newName = newName1.replaceAll("\\(", "").replaceAll("\\)", "");
			Field field = clazz.getDeclaredField(newName);
			field.setAccessible(true);
			String value = field.get(object).toString();
			if (StringHelper.isEmpty(value))
				return "0";
			else
				return value;
		} catch (Exception e) {
			logger.error(LogUtil.stackTraceToString(e));
			return "0";
		}
	}

	/**
	 * 给指定对象的指定列设置值
	 * @param clazz 类
	 * @param object 对象
	 * @param fieldName 列名
	 * @param value 要设定的值
	 * @return 
	 * @throws Exception
	 */
	public static Object setValue(Class<?> clazz, Object object, String fieldName, String value) throws Exception {
		try {
			String newName1 = StringHelper.removeBlank(fieldName.replaceAll("（", "").replaceAll("）", ""));
			String newName = newName1.replaceAll("\\(", "").replaceAll("\\)", "");
			Field field = clazz.getDeclaredField(newName);
			field.setAccessible(true);
			field.set(object, value);
			return object;
		} catch (Exception e) {
			logger.error(LogUtil.stackTraceToString(e));
			return null;
		}
	}

}
