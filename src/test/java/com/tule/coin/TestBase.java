package com.tule.coin;

import com.tule.coin.util.EccUtils;
import com.tule.coin.wallet.Wallet;
import org.junit.Test;

import java.nio.charset.StandardCharsets;

public class TestBase {
    protected Integer appId = 416790557;
    protected String googleCode = "GTVHRMFIFAJ4FR6N";
    protected String userPub = "MFkwEwYHKoZIzj0CAQYIKoZIzj0DAQcDQgAE6/n4lQsWjGrRTzVcs6Y9wd3n5+DMbzoKvtgd66EU27LwMvoF18ZUiQMy4HJjEYQbMqry3ghA8Etr2asH1ufGMg==";
    protected String userPrivate = "";
    protected String appPub = "MFkwEwYHKoZIzj0CAQYIKoZIzj0DAQcDQgAEeTOVA+bjFGB/zSmI8xCGvHGKv7HfqA9ZezdSFoyDCWezq8qxJm1/7ty0J3MJwO6A3wqeJoO3au/+6kZnkdXuvA==";
    //创建地址
    @Test
    public void createAddr() {
        Wallet wallet = new Wallet(appId, appPub, userPrivate, userPub, googleCode);
        System.out.println(wallet.createAddress("TRX", 1, ""));
    }
    //获取所有币种
    @Test
    public void getAllCoinInfo() {
        Wallet wallet = new Wallet(appId, appPub, userPrivate, userPub, googleCode);
        System.out.println(wallet.getAllCoinInfo());
    }
    //检查地址
    @Test
    public void checkAddress() {
        Wallet wallet = new Wallet(appId, appPub, userPrivate, userPub, googleCode);
        System.out.println(wallet.checkAddress("USDT(TRC20)","TBfeH3URUBDwJJnaHTt5D7WGyjCnvHZ27Q"));
    }
    //出金
    @Test
    public void sendTo(){
        Wallet wallet = new Wallet(appId, appPub, userPrivate, userPub, googleCode);
        System.out.println(wallet.sendTo("6be45108","USDT(TRC20)","TBfeH3URUBDwJJnaHTt5D7WGyjCnvHZ27Q","1",""));
    }
    //查询指定充值记录
    @Test
    public void queryDepositRecord(){
        Wallet wallet = new Wallet(appId, appPub, userPrivate, userPub, googleCode);
        System.out.println(wallet.queryDepositRecord("USDT(TRC20)","0","6b92456dc90e3b9d7e0ecff53f5c0c8f1c664a26bbf60c13cae712e1c4e730b8"));
    }
    //查询指定提取记录
    @Test
    public void queryWithdrawRecord(){
        Wallet wallet = new Wallet(appId, appPub, userPrivate, userPub, googleCode);
        System.out.println(wallet.queryWithdrawRecord("USDT(TRC20)","6be45108","ef477aca2cd853fcbe75812a2f9b9fbde18afffd6e6bda65976824ecbad4ce5a"));
    }
    //查看钱包出金记录
    @Test
    public void walletWithdrawRecords(){
        Wallet wallet = new Wallet(appId, appPub, userPrivate, userPub, googleCode);
        System.out.println(wallet.walletWithdrawRecords("USDT(TRC20)"));
    }
    //查看钱包充值记录
    @Test
    public void walletDepositRecords(){
        Wallet wallet = new Wallet(appId, appPub, userPrivate, userPub, googleCode);
        System.out.println(wallet.walletDepositRecords("USDT(TRC20)"));
    }
    //获取余额
    @Test
    public void getBalance(){
        Wallet wallet = new Wallet(appId, appPub, userPrivate, userPub, googleCode);
        System.out.println(wallet.getBalance("USDT(TRC20)","TBfeH3URUBDwJJnaHTt5D7WGyjCnvHZ27Q"));
    }
}
