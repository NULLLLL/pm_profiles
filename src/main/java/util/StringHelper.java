package util;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class StringHelper {

	private static String s_Charset = "utf-8";

	private static final Log log = LogFactory.getLog(StringHelper.class);

	private static final Pattern Keyword_Separator = Pattern.compile("[;；|]+");

	public static String[] KeywordSeparator(String keyword) {
		String[] keys = Keyword_Separator.split(keyword);
		Set<String> result = new HashSet<String>();
		for (String key : keys) {
			String k = key.trim();
			if (k.length() > 0)
				result.add(k);
		}
		return result.toArray(new String[result.size()]);
	}

	public static boolean isEmpty(String string) {
		return string == null || string.trim().equals("") || string.trim().equals("\"null\"") || string.equals("null");
	}

	public static boolean isNotEmpty(String string) {
		return !isEmpty(string);
	}

	public static boolean stringEquals(String s1, String s2) {
		if (s1 == null) {
			s1 = "";
		}
		if (s2 == null) {
			s2 = "";
		}
		return s1.equals(s2);
	}

	/**
	 * 清除空白字符
	 * 包括 空格、回车、换行符、制表符
	 * @param string
	 * @return
	 */
	public static String removeBlank(String string) {
		Pattern p = Pattern.compile("\\s*|\t|\r|\n");
		Matcher m = p.matcher(string);
		return m.replaceAll("");
	}

	public static String join(Object[] objs, String separator) {
		if (objs == null || objs.length == 0 || separator == null)
			return null;
		StringBuilder sb = new StringBuilder();
		for (Object obj : objs) {
			if (obj == null)
				continue;
			sb.append(separator).append(obj.toString());
		}
		return sb.toString().substring(separator.length());
	}

	public static String joinRow(Object[] objs, String separator) {
		if (objs == null || objs.length == 0 || separator == null)
			return null;
		StringBuilder sb = new StringBuilder();
		for (Object obj : objs) {
			sb.append(separator).append(obj != null ? obj.toString() : "");
		}
		return sb.toString().substring(separator.length());
	}

	@SuppressWarnings("rawtypes")
	public static String join(Collection objs, String separator) {
		if (objs == null || objs.size() == 0 || separator == null)
			return null;
		StringBuilder sb = new StringBuilder(256);
		for (Object obj : objs) {
			if (obj == null)
				continue;
			sb.append(separator).append(obj.toString());
		}
		return sb.toString().substring(separator.length());
	}

	public static String joinIds(Collection<Long> ids, String separator) {
		if (ids == null || ids.size() == 0 || separator == null)
			return null;
		Iterator<Long> itr = ids.iterator();
		Long first = itr.next();
		StringBuilder sb = new StringBuilder(ids.size() * (first.toString().length() + 1));
		sb.append(first);
		while (itr.hasNext())
			sb.append(separator).append(itr.next());
		return sb.toString();
	}

	public static String joinIds(Long[] ids, String separator) {
		if (ids == null || ids.length == 0 || separator == null)
			return null;
		StringBuilder sb = new StringBuilder(ids.length * (ids[0].toString().length() + 1));
		sb.append(ids[0]);
		for (int i = 1; i < ids.length; i++)
			sb.append(separator).append(ids[i]);
		return sb.toString();
	}

	public static String listToString(List<Long> permission) {
		StringBuilder builder = new StringBuilder();
		builder.append(" ( ");
		for (int i = 0; i < permission.size(); i++) {
			if (permission.size() == 1) {
				return builder.append(permission.get(0)).toString();
			}
			builder.append(permission.get(i)).append(",");
		}
		builder.append(" ) ");
		String str = builder.deleteCharAt(builder.length() - 4).toString();
		return str;
	}

	public static void stringToByteArray(byte[] pByteArray, String pStringToConvert) throws Exception {
		if (pByteArray == null || pStringToConvert == null)
			return;
		System.arraycopy(pStringToConvert.getBytes(s_Charset), 0, pByteArray, 0, pStringToConvert.getBytes(s_Charset).length);
	}

	public static int postionInArray(String[] arr, String name) {
		for (int i = 0; i < arr.length; i++) {
			boolean boo = stringEquals(name, arr[i]);
			if (boo == true) {
				return i;
			} else {
				continue;
			}
		}
		return -1;
	}

	public static boolean isEmptyList(List<?> list) {
		int count = 0;
		for (int i = 0; i < list.size(); i++) {
			Object object = list.get(i);
			if ("".equals(object)) {
				continue;
			} else {
				count++;
			}
		}
		if (count == 0) {
			return true;
		} else {
			return false;
		}
	}
}
