package util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Locale;

public class EncryptUtil {

    public static String encrypt(String password) {
        byte temResult[] = new byte[16];
        String result = "";

        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(("www.MD5.com.cn" + password).getBytes(StandardCharsets.UTF_8));
            temResult = md.digest();

            for (int i = 0; i < temResult.length; i++) {
                if (temResult[i] < 0) {
                    temResult[i] += 128;
                }
                String temp = Integer.toHexString(temResult[i]).toUpperCase(Locale.ROOT);
                if (temResult[i] < 16) {
                    temp = "0" + temp;
                }
                result += temp;
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            result = "";
        }
        return result;
    }
}
