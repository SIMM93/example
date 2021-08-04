package com.smartbr.wx;

import com.alibaba.fastjson.JSONObject;
import okhttp3.HttpUrl;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.*;

/**
 * @author sc
 */
public class BasePay {

    private static final String charset = "UTF-8";

    /**
     * 生成组装请求头
     *
     * @param method             请求方式
     * @param url                请求地址
     * @param mercId             商户ID
     * @param serial_no          证书序列号
     * @param privateKeyFilePath 私钥路径
     * @param body               请求体
     * @return 组装请求的数据
     * @throws Exception
     */
    protected static String getToken(String method, HttpUrl url, String mercId, String serial_no, String privateKeyFilePath, String body) throws Exception {
        String nonceStr = UUID.randomUUID().toString().replace("-", "");
        long timestamp = System.currentTimeMillis() / 1000;
        String message = buildMessage(method, url, timestamp, nonceStr, body);
        String signature = sign(message.getBytes("UTF-8"), privateKeyFilePath);
        return "mchid=\"" + mercId + "\","
                + "nonce_str=\"" + nonceStr + "\","
                + "timestamp=\"" + timestamp + "\","
                + "serial_no=\"" + serial_no + "\","
                + "signature=\"" + signature + "\"";
    }

    /**
     * 生成签名
     *
     * @param message            请求体
     * @param privateKeyFilePath 私钥的路径
     * @return 生成base64位签名信息
     * @throws Exception
     */
    public static String sign(byte[] message, String privateKeyFilePath) throws Exception {
        Signature sign = Signature.getInstance("SHA256withRSA");
        sign.initSign(getPrivateKey(privateKeyFilePath));
        sign.update(message);
        return Base64.getEncoder().encodeToString(sign.sign());
    }

    /**
     * 组装签名加载
     *
     * @param method    请求方式
     * @param url       请求地址
     * @param timestamp 请求时间
     * @param nonceStr  请求随机字符串
     * @param body      请求体
     * @return 组装的字符串
     */
    static String buildMessage(String method, HttpUrl url, long timestamp, String nonceStr, String body) {
        String canonicalUrl = url.encodedPath();
        if (url.encodedQuery() != null) {
            canonicalUrl += "?" + url.encodedQuery();
        }
        return method + "\n"
                + canonicalUrl + "\n"
                + timestamp + "\n"
                + nonceStr + "\n"
                + body + "\n";
    }


    /**
     * 获取私钥。
     *
     * @param filename 私钥文件路径  (required)
     * @return 私钥对象
     */
    static PrivateKey getPrivateKey(String filename) throws IOException {
        String content = new String(Files.readAllBytes(Paths.get(filename)), "UTF-8");
        try {
            String privateKey = content.replace("-----BEGIN PRIVATE KEY-----", "")
                    .replace("-----END PRIVATE KEY-----", "")
                    .replaceAll("\\s+", "");
            KeyFactory kf = KeyFactory.getInstance("RSA");
            return kf.generatePrivate(new PKCS8EncodedKeySpec(Base64.getDecoder().decode(privateKey)));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("当前Java环境不支持RSA", e);
        } catch (InvalidKeySpecException e) {
            throw new RuntimeException("无效的密钥格式");
        }
    }

    /**
     * 构造签名串
     *
     * @param signMessage 待签名的参数
     * @return 构造后带待签名串
     */
    protected static String buildSignMessage(ArrayList<String> signMessage) {
        if (signMessage == null || signMessage.size() <= 0) {
            return null;
        }
        StringBuilder sbf = new StringBuilder();
        for (String str : signMessage) {
            sbf.append(str).append("\n");
        }
        return sbf.toString();
    }


    /**
     * v3 支付异步通知验证签名
     *
     * @param body 异步通知密文
     * @param key  api 密钥
     * @return 异步通知明文
     * @throws Exception 异常信息
     */
    public static String verifyNotify(String body, String key) throws Exception {
        // 获取平台证书序列号
        JSONObject resultObject = JSONObject.parseObject(body);
        JSONObject resource = resultObject.getJSONObject("resource");
        String cipherText = resource.getString("ciphertext");
        String nonceStr = resource.getString("nonce");
        String associatedData = resource.getString("associated_data");
        AesUtil aesUtil = new AesUtil(key.getBytes(StandardCharsets.UTF_8));
        // 密文解密
        return aesUtil.decryptToString(
                associatedData.getBytes(StandardCharsets.UTF_8),
                nonceStr.getBytes(StandardCharsets.UTF_8),
                cipherText
        );
    }

    /**
     * 处理返回对象
     *
     * @param request
     * @return
     */
    protected static String readData(HttpServletRequest request) {
        BufferedReader br = null;
        try {
            StringBuilder result = new StringBuilder();
            br = request.getReader();
            for (String line; (line = br.readLine()) != null; ) {
                if (result.length() > 0) {
                    result.append("\n");
                }
                result.append(line);
            }
            return result.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    /**
     * 微信支付GET请求
     *
     * @param url_prex           请求域名
     * @param url                请求路由
     * @param mchid              商户号
     * @param serial_no          证书序列号
     * @param privateKeyFilePath 证书秘钥地址
     * @return
     * @throws Exception
     */
    protected static Object getRequest(String url_prex, String url, String mchid, String serial_no, String privateKeyFilePath) throws Exception {
        JSONObject body = null;
        //创建httpclient对象
        CloseableHttpClient client = HttpClients.createDefault();
        //创建post方式请求对象
        HttpGet httpGet = new HttpGet(url_prex + url);
        // 处理请求头报文
        String post = getToken("GET", HttpUrl.parse(url_prex + url), mchid, serial_no, privateKeyFilePath, "");
        //设置header信息
        //指定报文头【Content-type】、【User-Agent】
        httpGet.setHeader("Content-type", "application/json");
        httpGet.setHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
        httpGet.setHeader("Accept", "application/json");
        httpGet.setHeader("Authorization",
                "WECHATPAY2-SHA256-RSA2048 " + post);
        //执行请求操作，并拿到结果（同步阻塞）
        CloseableHttpResponse response = client.execute(httpGet);
        //获取结果实体
        HttpEntity entity = response.getEntity();
        if (entity != null) {
            //按指定编码转换结果实体为String类型
            body = JSONObject.parseObject(EntityUtils.toString(entity, charset));
        }
        EntityUtils.consume(entity);
        //释放链接
        response.close();
        return body;
    }

    /**
     * 微信支付POST请求
     *
     * @param url_prex           请求域名
     * @param url                请求路由
     * @param mchid              商户号
     * @param serial_no          证书序列号
     * @param privateKeyFilePath 证书秘钥地址
     * @param jsonStr            请求体Json字符串
     * @return
     * @throws Exception
     */
    protected static Object postRequest(String url, String mchid, String serial_no, String privateKeyFilePath, String jsonStr) throws Exception {
        String body = "";
        //创建httpclient对象
        CloseableHttpClient client = HttpClients.createDefault();
        //创建post方式请求对象
        HttpPost httpPost = new HttpPost(url);
        //装填参数
        StringEntity s = new StringEntity(jsonStr, charset);
        s.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE,
                "application/json"));
        //设置参数到请求对象中
        httpPost.setEntity(s);
        String post = getToken("POST", HttpUrl.parse(url), mchid, serial_no, privateKeyFilePath, jsonStr);
        //设置header信息
        //指定报文头【Content-type】、【User-Agent】
        httpPost.setHeader("Content-type", "application/json");
        httpPost.setHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
        httpPost.setHeader("Accept", "application/json");
        httpPost.setHeader("Authorization",
                "WECHATPAY2-SHA256-RSA2048 " + post);
        //执行请求操作，并拿到结果（同步阻塞）
        CloseableHttpResponse response = client.execute(httpPost);
        //获取结果实体
        HttpEntity entity = response.getEntity();
        if (entity != null) {
            //按指定编码转换结果实体为String类型
            body = EntityUtils.toString(entity, charset);
        }
        EntityUtils.consume(entity);
        //释放链接
        response.close();
        return body;
    }

    /**
     * 通知微信
     *
     * @param response
     * @param plainText
     * @throws Exception
     */
    protected static void sendMessage(HttpServletResponse response, String plainText) throws Exception {
        Map<String, String> map = new HashMap<>(12);
        // 需要通过证书序列号查找对应的证书，verifyNotify 中有验证证书的序列号
        if (plainText == null || plainText.length() == 0) {
            response.setStatus(200);
            map.put("code", "SUCCESS");
            map.put("message", "SUCCESS");
        } else {
            response.setStatus(500);
            map.put("code", "ERROR");
            map.put("message", "签名错误");
        }
        response.setHeader("Content-type", "application/json");
        response.getOutputStream().write(JSONObject.toJSONString(map).getBytes(StandardCharsets.UTF_8));
        response.flushBuffer();
    }


}
