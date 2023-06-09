package com.tule.coin.util;


import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.SocketAddress;

public class CoinClientConfiguration {

    // 连接超时设置
    private int connectionTimeoutMillis;
    private int socketTimeoutMillis;
    private Proxy proxy;

    public CoinClientConfiguration() {
        this.connectionTimeoutMillis = 0;
        this.socketTimeoutMillis = 0;
        this.proxy = Proxy.NO_PROXY;
    }

    public CoinClientConfiguration(int connectionTimeoutMillis, int socketTimeoutMillis, Proxy proxy) {
        this.connectionTimeoutMillis = connectionTimeoutMillis;
        this.socketTimeoutMillis = socketTimeoutMillis;
        this.proxy = proxy;
    }

    public int getConnectionTimeoutMillis() {
        return connectionTimeoutMillis;
    }

    public void setConnectionTimeoutMillis(int connectionTimeoutMillis) {
        this.connectionTimeoutMillis = connectionTimeoutMillis;
    }

    public int getSocketTimeoutMillis() {
        return socketTimeoutMillis;
    }

    public void setSocketTimeoutMillis(int socketTimeoutMillis) {
        this.socketTimeoutMillis = socketTimeoutMillis;
    }

    public Proxy getProxy() {
        return proxy;
    }

    public void setProxy(Proxy proxy) {
        this.proxy = proxy;
    }

    public void setProxy(String host, int port, Proxy.Type type) {
        SocketAddress addr = new InetSocketAddress(host, port);
        this.proxy = new Proxy(type, addr);
    }
}
