# 钱包 java-sdk
## 1.基本使用
```java
protected Integer appId = your appId;
protected String googleCode = "your googleAuth code";
protected String userPub = "your userPub ";
protected String userPrivate = "your userPrivate";
protected String appPub = "your appPub";

//创建地址
    public void createAddr() {
        Wallet wallet = new Wallet(appId, appPub, userPrivate, userPub, googleCode);
        System.out.println(wallet.createAddress("AE", 3, "memo"));
    }
    //检查地址
    public void checkAddress() {
        Wallet wallet = new Wallet(appId, appPub, userPrivate, userPub, googleCode);
        System.out.println(wallet.checkAddress("DOGE","addr"));
    }
    //出金
    public void sendTo(){
        Wallet wallet = new Wallet(appId, appPub, userPrivate, userPub, googleCode);
        System.out.println(wallet.sendTo("orderId","USDT(TRC20)","addr","1",""));
    }
    //获取余额
    public void getBalance(){
        Wallet wallet = new Wallet(appId, appPub, userPrivate, userPub, googleCode);
        System.out.println(wallet.getBalance("USDT(TRC20)","addr"));
    }
        ......
    
```