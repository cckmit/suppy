package com.zjjzfy.exchange.utils;

import com.google.common.collect.Maps;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import java.io.ByteArrayOutputStream;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;
import java.util.Map;

/**
 * Created by xiang.li on 2015/3/3. RSA 加解密工具类
 * 来自武汉，请使用他们方面的工具方法进行交互。
 */
public class RsaCode {
	/**
	 * 定义加密方式
	 */
	private final static String KEY_RSA = "RSA";
	/**
	 * 定义签名算法
	 */
	private final static String KEY_RSA_SIGNATURE = "MD5withRSA";
	/**
	 * 定义公钥算法
	 */
	private final static String KEY_RSA_PUBLICKEY = "RSAPublicKey";
	/**
	 * 定义私钥算法
	 */
	private final static String KEY_RSA_PRIVATEKEY = "RSAPrivateKey";

	/**
	 * 初始化密钥
	 * @return
	 */
	public static Map<String, Object> init() {
		Map<String, Object> map = null;
		try {
			KeyPairGenerator generator = KeyPairGenerator.getInstance(KEY_RSA);
			generator.initialize(1024);
			KeyPair keyPair = generator.generateKeyPair();
			// 公钥
			RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
			// 私钥
			RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
			// 将密钥封装为map
			map = Maps.newHashMap();
			map.put(KEY_RSA_PUBLICKEY, publicKey);
			map.put(KEY_RSA_PRIVATEKEY, privateKey);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return map;
	}

	/**
	 * 获取公钥
	 * @param map
	 * @return
	 */
	public static String getPublicKey(Map<String, Object> map) {
		String str = "";
		try {
			Key key = (Key) map.get(KEY_RSA_PUBLICKEY);
			str = encryptBase64(key.getEncoded());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return str;
	}

	/**
	 * 获取私钥
	 * @param map
	 * @return
	 */
	public static String getPrivateKey(Map<String, Object> map) {
		String str = "";
		try {
			Key key = (Key) map.get(KEY_RSA_PRIVATEKEY);
			str = encryptBase64(key.getEncoded());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return str;
	}

	/**
	 * 用私钥对信息生成数字签名
	 * @param data       加密数据
	 * @param privateKey 私钥
	 * @return
	 */
	public static String sign(String data, String privateKey) throws Exception {
//		try {
			// 解密由base64编码的私钥
			byte[] bytes = decryptBase64(privateKey);
			// 构造PKCS8EncodedKeySpec对象
			PKCS8EncodedKeySpec pkcs = new PKCS8EncodedKeySpec(bytes);
			// 指定的加密算法
			KeyFactory factory = KeyFactory.getInstance(KEY_RSA);
			// 取私钥对象
			PrivateKey key = factory.generatePrivate(pkcs);
			// 用私钥对信息生成数字签名
			Signature signature = Signature.getInstance(KEY_RSA_SIGNATURE);
			signature.initSign(key);
			signature.update(data.getBytes());
			return encryptBase64(signature.sign());
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return null;
	}

	/**
	 * 校验数字签名
	 * @param data      加密数据
	 * @param publicKey 公钥
	 * @param sign      数字签名
	 * @return 校验成功返回true，失败返回false
	 */
	public static boolean verify(String data, String publicKey, String sign) throws Exception  {
//		try {
			// 解密由base64编码的公钥
			byte[] bytes = decryptBase64(publicKey);
			// 构造X509EncodedKeySpec对象
			X509EncodedKeySpec keySpec = new X509EncodedKeySpec(bytes);
			// 指定的加密算法
			KeyFactory factory = KeyFactory.getInstance(KEY_RSA);
			// 取公钥对象
			PublicKey key = factory.generatePublic(keySpec);
			// 用公钥验证数字签名
			Signature signature = Signature.getInstance(KEY_RSA_SIGNATURE);
			signature.initVerify(key);
			signature.update(data.getBytes());
			return signature.verify(decryptBase64(sign));
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return false;
	}

	/**
	 * 公钥加密
	 * @param data 待加密数据
	 * @param key  公钥
	 * @return
	 */
	public static String encryptByPublicKey(String data, String key) throws Exception {
		int MAX_ENCRYPT_BLOCK = 117;
		int offSet = 0;
//		try {
			byte[] bytes = decryptBase64(key);
			// 取得公钥
			X509EncodedKeySpec keySpec = new X509EncodedKeySpec(bytes);
			KeyFactory factory = KeyFactory.getInstance(KEY_RSA);
			PublicKey publicKey = factory.generatePublic(keySpec);
			// 对数据加密
			Cipher cipher = Cipher.getInstance(factory.getAlgorithm());
			cipher.init(Cipher.ENCRYPT_MODE, publicKey);
			//RSA加密内容过长，超过117bytes，要分组加密
			byte[] resultBytes = {};
			byte[] cache = {};
			while (data.getBytes().length - offSet > 0) {
				if (data.getBytes().length - offSet > MAX_ENCRYPT_BLOCK) {
					cache = cipher.doFinal(data.getBytes(), offSet, MAX_ENCRYPT_BLOCK);
					offSet += MAX_ENCRYPT_BLOCK;
				} else {
					cache = cipher.doFinal(data.getBytes(), offSet, data.getBytes().length - offSet);
					offSet = data.getBytes().length;
				}
				resultBytes = Arrays.copyOf(resultBytes, resultBytes.length + cache.length);
				System.arraycopy(cache, 0, resultBytes, resultBytes.length - cache.length, cache.length);
			}
			return encryptBase64(resultBytes);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return null;
	}

	/**
	 * 私钥加密
	 * @param data 待加密数据
	 * @param key  私钥
	 * @return
	 */
	public static String encryptByPrivateKey(String data, String key) throws Exception {
		int MAX_ENCRYPT_BLOCK = 117;
		int offSet = 0;
//		try {
			byte[] bytes = decryptBase64(key);
			// 取得私钥
			PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(bytes);
			KeyFactory factory = KeyFactory.getInstance(KEY_RSA);
			PrivateKey privateKey = factory.generatePrivate(keySpec);
			// 对数据加密
			Cipher cipher = Cipher.getInstance(factory.getAlgorithm());
			cipher.init(Cipher.ENCRYPT_MODE, privateKey);
			//RSA加密内容过长，超过117bytes，要分组加密
			byte[] resultBytes = {};
			byte[] cache = {};
			while (data.getBytes().length - offSet > 0) {
				if (data.getBytes().length - offSet > MAX_ENCRYPT_BLOCK) {
					cache = cipher.doFinal(data.getBytes(), offSet, MAX_ENCRYPT_BLOCK);
					offSet += MAX_ENCRYPT_BLOCK;
				} else {
					cache = cipher.doFinal(data.getBytes(), offSet, data.getBytes().length - offSet);
					offSet = data.getBytes().length;
				}
				resultBytes = Arrays.copyOf(resultBytes, resultBytes.length + cache.length);
				System.arraycopy(cache, 0, resultBytes, resultBytes.length - cache.length, cache.length);
			}
			return encryptBase64(resultBytes);
//			byte[] result = cipher.doFinal(data.getBytes());
//			return encryptBase64(result);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return null;
	}

	/**
	 * 私钥解密
	 * @param data 加密数据
	 * @param key  私钥
	 * @return
	 */
	public static String decryptByPrivateKey(String data, String key) throws Exception {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		int MAX_DECRYPT_BLOCK= 128;
		int offSet = 0;
//		try {
			// 对私钥解密
			byte[] bytes = decryptBase64(key);
			// 取得私钥
			PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(bytes);
			KeyFactory factory = KeyFactory.getInstance(KEY_RSA);
			PrivateKey privateKey = factory.generatePrivate(keySpec);
			// 对数据解密
			Cipher cipher = Cipher.getInstance(factory.getAlgorithm());
			cipher.init(Cipher.DECRYPT_MODE, privateKey);
			//RSA解密内容过长，超过128bytes，要分组解密
			for(int i = 0; decryptBase64(data).length - offSet > 0; offSet = i * MAX_DECRYPT_BLOCK) {
				byte[] cache;
				if(data.getBytes().length - offSet > MAX_DECRYPT_BLOCK) {
					cache = cipher.doFinal(decryptBase64(data), offSet, MAX_DECRYPT_BLOCK);
				} else {
					cache = cipher.doFinal(data.getBytes(), offSet, decryptBase64(data).length - offSet);
				}
				out.write(cache, 0, cache.length);
				++i;
			}
			byte[] result = out.toByteArray();
			out.close();
			return new String(result);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return null;
	}

	/**
	 * 公钥解密
	 * @param data 加密数据
	 * @param key  公钥
	 * @return
	 */
	public static String decryptByPublicKey(String data, String key) throws Exception {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		int MAX_DECRYPT_BLOCK= 128;
		int offSet = 0;
//		try {
			// 对公钥解密
			byte[] bytes = decryptBase64(key);
			// 取得公钥
			X509EncodedKeySpec keySpec = new X509EncodedKeySpec(bytes);
			KeyFactory factory = KeyFactory.getInstance(KEY_RSA);
			PublicKey publicKey = factory.generatePublic(keySpec);
			// 对数据解密
			Cipher cipher = Cipher.getInstance(factory.getAlgorithm());
			cipher.init(Cipher.DECRYPT_MODE, publicKey);
			//RSA解密内容过长，超过128bytes，要分组解密
			for(int i = 0; decryptBase64(data).length - offSet > 0; offSet = i * MAX_DECRYPT_BLOCK) {
				byte[] cache;
				if(data.getBytes().length - offSet > MAX_DECRYPT_BLOCK) {
					cache = cipher.doFinal(decryptBase64(data), offSet, MAX_DECRYPT_BLOCK);
				} else {
					cache = cipher.doFinal(data.getBytes(), offSet, decryptBase64(data).length - offSet);
				}
				out.write(cache, 0, cache.length);
				++i;
			}
			byte[] result = out.toByteArray();
			out.close();
			return new String(result);
//			byte[] result = cipher.doFinal(decryptBase64(data));
//			return new String(result);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return null;
	}

	/**
	 * BASE64 加密
	 * @param key 需要加密的字节数组
	 * @return 字符串
	 * @throws Exception
	 */
	public static String encryptBase64(byte[] key) {
		return new String(Base64.encodeBase64(key));
	}

	/**
	 * BASE64 解密
	 * @param key 需要解密的字符串
	 * @return 字节数组
	 * @throws Exception
	 */
	public static byte[] decryptBase64(String key) {
		return Base64.decodeBase64(key);
	}

	/**
	 * 测试方法
	 *
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
//		String privateKey = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAJxBfaNkYjtTHjDFPIbYIZsqLfijioyyr3K2XOFqOwaTu0djT3VfbAZ29DVaGA9R+j5HhPwbv8W9AFki/mjOdX9YTbDMUInWfm7RA/mtFdtOsm6pU59SaIxrK9JzcPoEp2ZC4mGJ7qEIZaGdYq8wIs+2acyNZAxWeJmx/1gLq0WjAgMBAAECgYAWqiExLARbmojMhi9rV7o4KXwVjpMwKVLtjqoWQDRjldYaWrtRyQ+AOIzfmDvZLQjhM7qEXvoIEowUYdEJHrlIDsKnvT/ClNXwQ9hT8VAlfVzQWXsuW/uTb/IT5kWnOHiD4CtipGZyI4uaEtqfXtXNP3deoj2Bc6JvCK032Vv2UQJBAOdB3wDCyD4rncDJW69c6o0c1Hrz0rVslxTi8E03R19wKYyhOjKcch6lzaWyaZxAgDpiLSasvaMttw6sGgxWFC8CQQCs+VTy1OkqpinU6Y//OmZmtpKh+NJmGhUFITE1Gl1Tn7e2HOIwkNzUX6Scl2HQtw7fECFFGmtgnpEd58agYaTNAkB0ra2ASsaRZ5w+atnRjsXg0Mz8BA0p8FIB/cZUjzF5m7O6+pXMd1a0098AXRYf+gM4cdRqEPnVXPlhc7/ovX2NAkEAqNHUGgi45To1m6q1Mrnx1Y+dP15VIveTymTh3N01dvdsWt6H2DSaguAyDe8ltYnkhsr8XbByWmOIaLKcxk1mhQJALJUhdhi/aIO+LZ56Wcq7aFY36RimZ1EJPfIvuUiSeGHrKek31FKgLVOmbeFSkYiOlOUgwxrYQYbQrjEWGEXq1g==";
//		String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCcQX2jZGI7Ux4wxTyG2CGbKi34o4qMsq9ytlzhajsGk7tHY091X2wGdvQ1WhgPUfo+R4T8G7/FvQBZIv5oznV/WE2wzFCJ1n5u0QP5rRXbTrJuqVOfUmiMayvSc3D6BKdmQuJhie6hCGWhnWKvMCLPtmnMjWQMVniZsf9YC6tFowIDAQAB";
		// 生成公钥私钥
		Map<String, Object> map = init();
		String publicKey = getPublicKey(map);
		String privateKey = getPrivateKey(map);
		System.out.println("公钥: \n\r" + publicKey);
		System.out.println("私钥： \n\r" + privateKey);
		System.out.println("公钥加密--------私钥解密");
		String word = "{\"body\":{\"timeStamp\":\"20191211\",\"custNo\":\"80000208319\",\"custName\":\"客户积分\",\"custPhone\":\"15171532593\",\"custIdtype\":\"\",\"custIdcard\":\"\",\"dotNo\":\"10150\",\"deptNo\":\"1000\",\"busiType\":\"\",\"tranNo\":\"191\",\"tranType\":\"1\",\"money\":10000.00}}";
		String encWord = encryptByPublicKey(word, publicKey);
//		String encWord = "dENrcsVHSwIv7t62hhFWMjirrOvcxVFwRGrglXTWIHfGewrYDzYtKOnW5058KZvhIOBWgYM/b5BEJ3IcMQ+zU34riPjuflGPPIwb6pfMsYw6XWw0ul7sNnr9mS172et/TriWV4l+fvtFCdul65n7/MBWxG47BsiSgNqVqL7jFnAD8GxDXmfTgmg/ozo1zBfqnE7d6FjPONIQzUKzwNr9T1SnRZK19nSJ/lmLm5hu4Dp7JSvFJwMpBhRTCdEj4O4DDkGEeFPpM9P/knEls7MTzuivtygvCgApLUV+9BPQM85aBX+YRJGk74irmNW3/keOKUcen8WaZENWRudTyQnhMYdleC+rno4gH2MSA0DnXb6FkJaRRmtaeiDkqdYY3wek4vhjA2RXBgmdP20KujStwUI8Qjj7INeChCwP/OR4kw0RkBWg6q4Hv2/4ksSvoFys4tgEixRwqG1KZNUXFM06o8o8TdMFANheGzbTyW75/Zt62YTpjK36OGlsFUXFp6My";
		String decWord = new String(decryptByPrivateKey(encWord, privateKey));
		System.out.println("加密前: " + word + "\n\r" + "解密后: " + decWord);
		System.out.println("私钥加密--------公钥解密");
		String english = "Hello, World!";
		String encEnglish = encryptByPrivateKey(english, privateKey);
		System.out.println("***加密后: " + encEnglish);
		String decEnglish = new String(decryptByPublicKey(encEnglish, publicKey));
		System.out.println("加密前: " + english + "\n\r" + "解密后: " + decEnglish);
		System.out.println("私钥签名——公钥验证签名");
		// 产生签名
		String sign = sign(encEnglish, privateKey);
		System.out.println("签名:\r" + sign);
		// 验证签名
		boolean status = verify(encEnglish, publicKey, sign);
		System.out.println("状态:\r" + status);
	}
}