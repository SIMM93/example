
import lombok.SneakyThrows;

import java.util.*;

import static com.smartbr.wx.BasePay.sign;

public class OutSing {

    public static long getTimeStamp() {
        Date d = new Date();
        long timeStamp = d.getTime() / 1000;     //getTime()得到的是微秒， 需要换算成秒
        return timeStamp;
    }

    public static String getNonceStr() {
        String s = UUID.randomUUID().toString().replace("-", "");
        return s;
    }


    @SneakyThrows
    public static String createSign(String timeStamp,
                                    String nonceStr,
                                    String prepay_id,
                                    String appid
    ) {

        String privateKey = appid + "\n"
                + timeStamp + "\n"
                + nonceStr + "\n"
                + prepay_id + "\n";
        String path = "E:/pk/apiclient_key.pem";
        String signature = sign(privateKey.getBytes(), path);

        return signature;
    }

    public static void main(String[] args) {
        String timeStamp = String.valueOf(getTimeStamp());
        String nonceStr = getNonceStr();
        String prepay_id = "prepay_id=wx2214170331598421beb2f6664f929c0000 ";
        String appid = "wxff79a780ba70d948";

        String k = createSign(timeStamp, nonceStr, prepay_id, appid);
        System.out.println("timeStamp: " + timeStamp);
        System.out.println("nonceStr: " + nonceStr);
        System.out.println("prepay_id: " + prepay_id);
        System.out.println("appid: " + appid);
        System.out.println("signType: " + "RSA");
        System.out.println("paySign: " + k);


    }
}

