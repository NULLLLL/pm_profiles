package util;

import java.math.BigDecimal;

public class NumberFormatterUtil {

	public static Double scaleDouble(Double value, int newScale) {
		if (value == null) {
			return value;
			
		}
		return new java.math.BigDecimal(value).setScale(newScale, java.math.BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	public static Float scaleFloat(Float value, int newScale) {
		if (value == null) {
			return value;
		}
		return new java.math.BigDecimal(value).setScale(newScale, java.math.BigDecimal.ROUND_HALF_UP).floatValue();
	}

	public static float getFormatterNumber(float divisor, float dividend) {
		if (dividend == 0)
			return 0;
		return new BigDecimal(divisor / dividend).setScale(3, BigDecimal.ROUND_HALF_UP).floatValue();
	}

	public static float getDivisorNumber(float divisor, float dividend, int scaleNum) {
		if (dividend == 0)
			return 0;
		return new BigDecimal(divisor / dividend).setScale(scaleNum, BigDecimal.ROUND_HALF_UP).floatValue();
	}
	
	public static Double subtract(Double minuend ,Double subtrahend){
		BigDecimal m = new BigDecimal(Double.toString(minuend));
		BigDecimal s = new BigDecimal(Double.toString(subtrahend));
		return m.subtract(s).setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	public static Double add(Double addend ,Double augend){
		BigDecimal m = new BigDecimal(Double.toString(addend));
		BigDecimal s = new BigDecimal(Double.toString(augend));
		return m.add(s).setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	public static Double divide(Double addend ,Double augend){
		BigDecimal m = new BigDecimal(Double.toString(addend));
		BigDecimal s = new BigDecimal(Double.toString(augend));
		return m.divide(s,1,BigDecimal.ROUND_HALF_UP).doubleValue();
	}
}
