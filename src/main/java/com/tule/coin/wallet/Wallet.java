package com.tule.coin.wallet;

import com.tule.coin.client.BaseClient;
import com.tule.coin.http.*;
import com.tule.coin.util.ParamsCheckUtils;
import org.json.JSONObject;

import java.util.Random;

public class Wallet extends BaseClient {

    public Wallet(Integer appId, String appPub, String userPrivate, String userPub, String googleCode) {
        super(appId, appPub, userPrivate, userPub, googleCode);
    }

    public JSONObject createAddress(String symbol, Integer number, String memo) {
        CoinRequest request = new CoinRequest();
        request.addBody("symbol", symbol);
        request.addBody("number", number);
        request.addBody("memo", memo);
        request.setUri(WalletConst.CREATEADDRESS);
        request.addHeader(Headers.CONTENT_TYPE, HttpContentType.JSON_DATA);
        request.setBodyFormat(EBodyFormat.RAW_JSON);
        return requestServer(request);
    }

    public JSONObject checkAddress(String symbol, String address) {
        CoinRequest request = new CoinRequest();
        request.addBody("symbol", symbol);
        request.addBody("address", address);
        request.setUri(WalletConst.CHECKADDRESS);
        request.addHeader(Headers.CONTENT_TYPE, HttpContentType.JSON_DATA);
        request.setBodyFormat(EBodyFormat.RAW_JSON);
        return requestServer(request);
    }

    public JSONObject getBalance(String symbol, String address) {
        CoinRequest request = new CoinRequest();
        request.addBody("symbol", symbol);
        request.addBody("address", address);
        request.setUri(WalletConst.GETBALANCE);
        request.addHeader(Headers.CONTENT_TYPE, HttpContentType.JSON_DATA);
        request.setBodyFormat(EBodyFormat.RAW_JSON);
        return requestServer(request);
    }
    public JSONObject getBalance(String symbol, String address,String memo) {
        CoinRequest request = new CoinRequest();
        request.addBody("symbol", symbol);
        request.addBody("address", address);
        request.addBody("memo", memo);
        request.setUri(WalletConst.GETBALANCE);
        request.addHeader(Headers.CONTENT_TYPE, HttpContentType.JSON_DATA);
        request.setBodyFormat(EBodyFormat.RAW_JSON);
        return requestServer(request);
    }

    public JSONObject sendTo(String orderId, String symbol, String to, String amount, String memo) {
        CoinRequest request = new CoinRequest();
        Long timestamp = System.currentTimeMillis() / 1000;
        Integer index = new Random().nextInt(Integer.MAX_VALUE);
        request.addBody("orderId", orderId);
        request.addBody("symbol", symbol);
        request.addBody("to", to);
        request.addBody("amount", amount);
        request.addBody("memo", memo);
        request.addBody("timestamp", timestamp);
        String signData = ParamsCheckUtils.calcSignData(request.getBody(),this.googleCode,index);
        request.addBody("index", index);
        request.addBody("signData", signData);
        request.setUri(WalletConst.SENDTO);
        request.addHeader(Headers.CONTENT_TYPE, HttpContentType.JSON_DATA);
        request.setBodyFormat(EBodyFormat.RAW_JSON);
        return requestServer(request);
    }
    public JSONObject getAllCoinInfo(){
        CoinRequest request = new CoinRequest();
        request.setUri(WalletConst.GETALLCOININFO);
        request.addHeader(Headers.CONTENT_TYPE, HttpContentType.JSON_DATA);
        request.setBodyFormat(EBodyFormat.RAW_JSON);
        return requestServer(request);
    }
    public JSONObject queryDepositRecord(String symbol,String index,String hash){
        CoinRequest request = new CoinRequest();
        request.addBody("symbol", symbol);
        request.addBody("index", index);
        request.addBody("hash", hash);
        request.setUri(WalletConst.QUERYDEPOSITRECORD);
        request.addHeader(Headers.CONTENT_TYPE, HttpContentType.JSON_DATA);
        request.setBodyFormat(EBodyFormat.RAW_JSON);
        return requestServer(request);
    }
    public JSONObject queryWithdrawRecord(String symbol,String orderId,String hash){
        CoinRequest request = new CoinRequest();
        request.addBody("symbol", symbol);
        request.addBody("orderId", orderId);
        request.addBody("hash", hash);
        request.setUri(WalletConst.QUERYWITHDRAWRECORD);
        request.addHeader(Headers.CONTENT_TYPE, HttpContentType.JSON_DATA);
        request.setBodyFormat(EBodyFormat.RAW_JSON);
        return requestServer(request);
    }
    public JSONObject walletWithdrawRecords(String symbol){
        CoinRequest request = new CoinRequest();
        request.addBody("symbol", symbol);
        request.setUri(WalletConst.WALLETWITHDRAWRECORDS);
        request.addHeader(Headers.CONTENT_TYPE, HttpContentType.JSON_DATA);
        request.setBodyFormat(EBodyFormat.RAW_JSON);
        return requestServer(request);
    }
    public JSONObject walletDepositRecords(String symbol){
        CoinRequest request = new CoinRequest();
        request.addBody("symbol", symbol);
        request.setUri(WalletConst.WALLETDEPOSITRECORDS);
        request.addHeader(Headers.CONTENT_TYPE, HttpContentType.JSON_DATA);
        request.setBodyFormat(EBodyFormat.RAW_JSON);
        return requestServer(request);
    }
}
