package com.tule.coin.wallet;

public class WalletConst {
    //创建地址
    static final String CREATEADDRESS = "https://waas-gateway-prod.nbttfc365.com/coin/v1/wallet/createAddress";
    //验证地址
    static final String CHECKADDRESS = "https://waas-gateway-prod.nbttfc365.com/coin/v1/wallet/checkAddress";
    //查询余额
    static final String GETBALANCE = "https://waas-gateway-prod.nbttfc365.com/coin/v1/wallet/getBalance";
    //提取资金
    static final String SENDTO = "https://waas-gateway-prod.nbttfc365.com/coin/v1/wallet/sendTo";
    //获取订阅币种信息
    static final String GETALLCOININFO = "https://waas-gateway-prod.nbttfc365.com/coin/v1/wallet/getAllCoinInfo";
    //查询指定充值记录
    static final String QUERYDEPOSITRECORD = "https://waas-gateway-prod.nbttfc365.com/coin/v1/wallet/queryDepositRecord";
    //查询指定提取记录
    static final String QUERYWITHDRAWRECORD = "https://waas-gateway-prod.nbttfc365.com/coin/v1/wallet/queryWithdrawRecord";
    //查看钱包出金记录
    static final String WALLETWITHDRAWRECORDS = "https://waas-gateway-prod.nbttfc365.com/coin/v1/wallet/walletWithdrawRecords";
    //查看钱包充值记录
    static final String WALLETDEPOSITRECORDS = "https://waas-gateway-prod.nbttfc365.com/coin/v1/wallet/walletDepositRecords";

}
