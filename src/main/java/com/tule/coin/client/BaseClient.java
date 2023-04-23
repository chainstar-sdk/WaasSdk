package com.tule.coin.client;


import com.tule.coin.error.CoinError;
import com.tule.coin.http.*;
import com.tule.coin.util.*;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.Proxy;
import java.nio.charset.StandardCharsets;
public abstract class BaseClient {

    protected Integer appId;
    protected String appPub;
    protected String userPrivate;
    protected String userPub;
    protected String googleCode;
    protected CoinClientConfiguration config;
    protected static final Logger LOGGER = LoggerFactory.getLogger(BaseClient.class);

    /*
     * BaseClient constructor
     */
    protected BaseClient(Integer appId, String appPub, String userPrivate, String userPub, String googleCode) {
        this.appId = appId;
        this.appPub = appPub;
        this.userPrivate = userPrivate;
        this.userPub = userPub;
        this.googleCode = googleCode;
    }

    /**
     * @param timeout 服务器建立连接的超时时间（单位：毫秒）
     */
    public void setConnectionTimeoutInMillis(int timeout) {
        if (config == null) {
            config = new CoinClientConfiguration();
        }
        this.config.setConnectionTimeoutMillis(timeout);
    }

    /**
     * @param timeout 通过打开的连接传输数据的超时时间（单位：毫秒）
     */
    public void setSocketTimeoutInMillis(int timeout) {
        if (config == null) {
            config = new CoinClientConfiguration();
        }
        this.config.setSocketTimeoutMillis(timeout);
    }

    /**
     * 设置访问网络需要的http代理
     *
     * @param host 代理服务器地址
     * @param port 代理服务器端口
     */
    public void setHttpProxy(String host, int port) {
        if (config == null) {
            config = new CoinClientConfiguration();
        }
        this.config.setProxy(host, port, Proxy.Type.HTTP);
    }

    /**
     * 设置访问网络需要的socket代理
     *
     * @param host 代理服务器地址
     * @param port 代理服务器端口
     */
    public void setSocketProxy(String host, int port) {
        if (config == null) {
            config = new CoinClientConfiguration();
        }
        this.config.setProxy(host, port, Proxy.Type.SOCKS);
    }


    /**
     * send request to server
     *
     * @param request CoinRequest object
     * @return JSONObject of server response
     */
    protected JSONObject requestServer(CoinRequest request) {
        //请求添加data
        String bodyStr = request.getBodyStr();
        request.addBody("appId", appId);
        request.addBody("data", EccUtils.encrypt(bodyStr.getBytes(StandardCharsets.UTF_8), appPub));
        // 请求API
        CoinResponse response = CoinHttpClient.post(request);
        String resData = response.getBodyStr();
        Integer status = response.getStatus();
        if (status.equals(200) && !resData.equals("")) {
            try {
                JSONObject res = new JSONObject(resData);
                if (res.has("data") && !res.isNull("data") && !res.getString("data").equals("")) {
                    byte[] decrypt = EccUtils.decrypt(res.getString("data").getBytes(StandardCharsets.UTF_8), userPrivate);
                    return new JSONObject(new String(decrypt));
                }
                return res;
            } catch (JSONException e) {
                return Util.getGeneralError(-1, resData);
            }
        } else {
            LOGGER.warn(String.format("call failed! response status: %d, data: %s", status, resData));
            return CoinError.NET_TIMEOUT_ERROR.toJsonResult();
        }
    }

}
