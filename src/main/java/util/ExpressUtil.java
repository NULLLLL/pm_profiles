package util;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import net.java.dev.eval.Expression;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExpressUtil {

	private static final Logger logger = LoggerFactory.getLogger(ExpressUtil.class);

	/**
	 * 计算字符串型的公式(可以输入三目运算符)
	 * @param express
	 * @return
	 */
	public static BigDecimal stringExpress(String express) {
		try {
			String replaceSign = express.replaceAll("sign", ">0?1:0");
			//			String replaceAll = replaceSign.replaceAll("\\(\\(0\\)>0\\?1:0\\)", "0").replaceAll("\\(\\(1\\)>0\\?1:0\\)", "1");
			//			String removeBlank = StringHelper.removeBlank(replaceSign);
			Expression expression = new Expression(replaceSign);
			Map<String, BigDecimal> variables = new HashMap<String, BigDecimal>();
			variables.put("e", new BigDecimal(Math.E));
			BigDecimal result = expression.eval(variables);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(LogUtil.stackTraceToString(e));
			return null;
		}

	}

}
