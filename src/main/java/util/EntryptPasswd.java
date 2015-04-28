package util;

import org.springside.modules.security.utils.Digests;
import org.springside.modules.utils.Encodes;

public class EntryptPasswd {
	
	public static final String HASH_ALGORITHM = "SHA-1";
	public static final int HASH_INTERATIONS = 1024;
	private static final int SALT_SIZE = 8;
	
	public static void main(String arg[]){
		entryptPassword("111111");
	}
	
	/**
	 * 设定安全的密码，生成随机的salt并经过1024次 sha-1 hash
	 */
	public static void entryptPassword(String plainPasswd) {
		byte[] saltArray = Digests.generateSalt(SALT_SIZE);
		String salt = Encodes.encodeHex(saltArray);
		byte[] hashPassword = Digests.sha1(plainPasswd.getBytes(), saltArray, HASH_INTERATIONS);
		String passwd = Encodes.encodeHex(hashPassword);
		System.err.println("salt-----:" + salt);
		System.err.println("passwd---:" + passwd);
	}

}
