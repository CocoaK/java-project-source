package com.biencloud.smarthome.service.common.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.lang3.StringUtils;

import sun.misc.BASE64Encoder;

/**
 * 加解密工具类。
 * 
 * @author kouy
 * @since 1.0 2012-5-14
 */
public class CryptoUtils {

	private static final String CHARSET_UTF8 = "UTF-8";
	private static final String DIGEST_MD5 = "MD5";
	private static final String DIGEST_SHA1 = "SHA-1";

	private CryptoUtils() {
		// Never instance
	}

	/**
	 * 使用MD5计算指定字符串的摘要值，如果指定字符串为空，则返回空串；<br/>
	 * 否则返回计算后的摘要值。 
	 * @param value 待做摘要的字符串
	 * @throws RuntimeException 当不支持MD5算法或字符串编码时
	 * @return
	 */
	public static String encodeByMD5(String value) {
		return digest(value, DIGEST_MD5, CHARSET_UTF8);
	}

	/**
	 * 使用SHA1计算指定字符串的摘要值，如果指定字符串为空，则返回空串；<br/>
	 * 否则返回计算后的摘要值。 
	 * @param value 待做摘要的字符串
	 * @throws RuntimeException 当不支持SHA1算法或字符串编码时
	 * @return
	 */
	public static String encodeBySHA1(String value) {
		return digest(value, DIGEST_SHA1, CHARSET_UTF8);
	}
	
	/**
	 * 校验摘要前的值使用MD5做摘要后是否和指定摘要值相等。
	 * @param digestValue 摘要值，使用 MD5做摘要
	 * @param value 摘要前的值
	 * IllegalArgumentException 当摘要值或摘要前的值为空时
	 * @throws RuntimeException 当不支持MD5算法或字符串编码时
	 * @return
	 */
	public static boolean validateByMD5(String digestValue, String value) {
		if (StringUtils.isEmpty(digestValue) || StringUtils.isEmpty(value))
			throw new IllegalArgumentException("digestValue or value are empty!");
			
		return digestValue.equals(encodeByMD5(value));
	}
	
	/**
	 * 校验摘要前的值使用SHA1做摘要后是否和指定摘要值相等。
	 * @param digestValue 摘要值，使用 SHA1做摘要
	 * @param value 摘要前的值
	 * IllegalArgumentException 当摘要值或摘要前的值为空时
	 * @throws RuntimeException 当不支持SHA1算法或字符串编码时
	 * @return
	 */
	public static boolean validateBySHA1(String digestValue, String value) {
		if (StringUtils.isEmpty(digestValue) || StringUtils.isEmpty(value))
			throw new IllegalArgumentException("digestValue or value are empty!");
			
		return digestValue.equals(encodeBySHA1(value));
	}

	// 使用指定的摘要算法和字符串编码计算指定的值的摘要值
	private static String digest(String value, String algorithm, String charset) {
		if (StringUtils.isEmpty(value))
			return "";

		try {
			MessageDigest md = MessageDigest.getInstance(algorithm);
			return new BASE64Encoder()
					.encode(md.digest(value.getBytes(charset)));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
     * MD5加密
     * 
     * @param plainText
     * @return
     */
    public static String encoder(String plainText) {
        if (plainText == null || plainText.length() == 0) {
            return "";
        }
        String str = "";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(plainText.getBytes());
            byte b[] = md.digest();
            int i;
            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
            str = buf.toString();
//            str = str.substring(8, 24);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return str;
    }
}
