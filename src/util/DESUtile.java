package util;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import java.security.Key;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;

public class DESUtile {
    private static Key key;
    private static String KEY_STR = "Shop_System";
    private static String CHARSETNAME = "UTF-8";
    private static String ALGORITHM = "DES";

    // 静态代码块
    static {
        try {
            // 生成DES算法对象
            KeyGenerator generator = KeyGenerator.getInstance(ALGORITHM);
            // 运用SHA1安全策略
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            // 设置上密钥种子
            secureRandom.setSeed(KEY_STR.getBytes());
            // 初始化基于SHA1的算法对象
            generator.init(secureRandom);
            // 生成密钥对象
            key = generator.generateKey();
            generator = null;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * * 获取加密后的信息 * @param str * @return
     */

    public static String getEncryptString(String str) {
        // 基于BASE64编码，接收byte[]并转换为String
        Encoder encoder = Base64.getEncoder();
        try {
            // 按UTF-8编码
            byte[] bytes = str.getBytes(CHARSETNAME);
            // 获取加密对象
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            // 初始化密码信息
            cipher.init(Cipher.ENCRYPT_MODE, key);
            // 加密
            byte[] doFinal = cipher.doFinal(bytes);
            // byte[] to encode好的String并返回
            return encoder.encodeToString(doFinal);
        } catch (Exception e) {
            // TODO: handle exception
            throw new RuntimeException(e);
        }
    }

    /**
     * 64 * 获取解密后的信息 65 * 66 * @param str 67 * @return 68
     */

    public static String getDecryptString(String str) {
        // 基于BASE64编码，接收byte[]并转换为String
        // BASE64Decoder base64decoder = new BASE64Decoder();
        Decoder decoder = Base64.getDecoder();
        try {
            // 将字符串decode为byte[]

            byte[] bytes = decoder.decode(str);
            // 获取解密对象
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            // 初始化解密信息
            cipher.init(Cipher.DECRYPT_MODE, key);
            // 解密
            byte[] doFinal = cipher.doFinal(bytes);
            // 返回解密之后的信息
            return new String(doFinal, CHARSETNAME);
        } catch (Exception e) {
            // TODO: handle exception
            throw new RuntimeException(e);
        }
    }

}
