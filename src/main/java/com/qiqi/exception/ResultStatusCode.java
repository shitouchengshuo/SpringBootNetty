package com.qiqi.exception;

/**
 * 异常状态码的枚举类
 */
public enum ResultStatusCode {

    Success("0", "Success"),
    UserNotExist("1", "User not exist"),
    InValidParameter("2","Invalid parameter"),
    DataFormatException("4", "DataFormat exception"),
    DataNotExistException("5", "DataNotExistException"),
    TimeFormatException("6","TimeFormat Exception"),
    PictureFormatException("7","PictureFormat Exception"),
    IllegalArgumentException("8","IllegalArgumentException"),
    TokenInvalidOrOverdueException("9", "Token invalid or overdue exception"),
    AuthorizationCodeError("10", "authorization code error"),
    WrongSignatureException("11","Wrong Signature Exception"),
    SystemException("50", "system Exception"),
    MissingServletRequestParameter("400","Missing servletRequest parameter"),
    TypeMismatchException("401","Request parameter Type not match"),
    RequestMethodNotAllowed("405","Request method  not Allowed"),

    NotVerifiedByOldPhone("3", "not verified by old phone"),
    OldPassWordError("4", "old pass word error"),
    InvalidToken("5", "invalid token"),
    InvalidRefreshToken("6", "invalid refresh token"),
    UserNameNotExist("7", "user not exist"),
    PasswordError("8", "password error"),
    ClientIdNotExist("9", "client id not exist"),
    ClientSecretError("10", "client secret error"),
    ParameterError("12", "parameter code error"),
    GetVerificationCodeFaliure("13", "get verification code failure"),
    AccountPhonenumberAlreadyExist("14", "account phone number already exist"),
    PwdNotSetError("15", "passwd not set"),
    GetUserDetailError("16", "get user detail error"),
    PermissionError("17", " permission error"),
    PicFormatError("18", "picture format error"),
    PicEmptyError("19", "picture is empty error"),
    AvatarNotSetError("20", "avatar not set error"),
    TokenMgrError("21", "token is error"),
    AccountNotActive("22", "account not active"),
    VerificationCodeUsed("23", "verification code used"),
    AccountNameAlreadyExist("24", "account name already exist"),
    AccountMailaddressAlreadyExist("25", "account mail address already exist"),
    AccountAlreadyLogout("26","account has log out"),
    NeedRefreshToken("27", "need refresh token"),  //这个异常不会抛向用户
    SecurityQANotSetError("28", "securityQA not set"),
    SecurityQAError("29", "securityQA is wrong"),
    AccountKickedOut("30", "this account has logged in another device"),
    EmailFormatError("31","mailaddress format wrong"),
    PasswordFormatError("32","password format wrong"),
    UserNameFormatError("33","username format wrong"),
    PhoneFormatError("34","phonenumber format wrong"),
    CaptchaNotExist("36","picture not exist"),
    CaptchaWrong("37","captcha is wrong or null"),
    VerificationCodeRequestTooFast("38","VerificationCode has been requested too fast"),
    VerificationCodeRequestTooMuch("39","VerificationCode has been requested too much"),
    VerificationCodeNeeded("40", "need to send verification code to verify"),
    AccountNotBind("41", "not bind to feixun account"),
    AccountBeenLocked("42", "this account has been locked"),
    IpBeenLocked("43", "this ip has been locked"),
    ServerError("50", "server error"),
    ServerMaintenance("51", "server maintenance error"),
    ErrorCodeIsNullError("52","error code is null"),
    MACNotExist("100", "mac address not exist"),
    MACHasRelateDevice("101", "mac address has related to device"),
    DeviceCannotCommunication("102", "Device cannot communication"),
    DeviceHasnotConnectKeepAliveServer("103", "device has not connect to keep-alive server"),
    AccountNotBindingDevice("111", "account not binding on device"),
    DeviceNotBindingAccount("112", "device not binding on account"),
    AccountNotBindingAnyDevice("113", "account not binding on any device"),
    DeviceNotBindingAnyAccount("114", "device not binding on any account"),
    APPVersionNotSupport("120", "app version not support"),
    PhonenumberHasChanged("130","user phonenumber has changed"),
    UidIsNotExists("140","uid is not exists"),
    UidAndFaceidAlreadyBind("141","uid and faceid already bind"),
    InsertResultError("150","insert result error"),
    UploadFile("160","upload file error"),
    FaceVerification("161","face verification error"),
    HasUploaded("163","has uploaded"),
    HasNotUploaded("164","has not uploaded"),
    ;

    private String code;
    private String msg;

    private ResultStatusCode(String code,String msg){
        this.code=code;
        this.msg=msg;
    }

    public String getMsg(){
        return this.msg;
    }
    public String getCode(){
        return this.code;
    }

}
