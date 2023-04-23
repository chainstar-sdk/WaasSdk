package com.tule.coin.util;


import org.apache.commons.codec.binary.Hex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

/**
 * Description:
 * 校验参数是否存在
 * 并根据预设的枚举返回异常
 * Date: 2019/8/28 11:27
 * Created by slipshield
 */
public class ParamsCheckUtils {

    private final static Logger logger = LoggerFactory.getLogger(ParamsCheckUtils.class);

    private static String calcGoogleAuth(String data, String googleAuth, Long timestamp, int index){
        String key = TOTP.getTOTP(googleAuth, timestamp / 30);
        try {
            data = data.substring(0, data.length() - 1);
            String sign = "";
            if (-1 != index){
                int signV = SimpleHash.calcHash(data+key,index);
                sign = Integer.toString(signV);
            }else {
                MessageDigest md = MessageDigest.getInstance("MD5");
                byte[] b = (data + key).getBytes("UTF-8");
                sign = Hex.encodeHexString(md.digest(b));
            }
            return sign;
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String calcSignData(Map<String,Object> paramsMap, String googleKey,Integer index){
        BigDecimal bigAmount = new BigDecimal(paramsMap.get("amount").toString());
        paramsMap.put("amount", bigAmount.stripTrailingZeros().toPlainString());

        Set set = paramsMap.keySet();
        Object[] array = set.toArray();
        Arrays.sort(array);
        String data = "";
        for (Object key : array) {
            data += key + "=" + paramsMap.get(key) + "&";
        }

        String signData = ParamsCheckUtils.calcGoogleAuth(data, googleKey, Long.valueOf(paramsMap.get("timestamp").toString()),index);
        return signData;
    }


}
