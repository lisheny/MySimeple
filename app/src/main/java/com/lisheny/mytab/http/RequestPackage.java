package com.lisheny.mytab.http;

/**
 * author：kang
 * time:  2016-11-21
 *
 * 请求包数据接口
 */
public interface RequestPackage {

    // ============ 请求包命令 ============

    /** 登录 */
    String LOGIN_COMMAND = "1002";
    /** 注册 */
    String GETCODE_COMMAND = "1003";
    String REGISTER_COMMAND = "1001";
    String EMAIL_REGISTER_COMMAND = "1021";
    //项目升级
    String UPDATE_COMMAND = "1008";
    //获取滤镜
    String GET_FILTER_COMMAND = "1009";
    //获取表情
    String GET_FACE_COMMAND = "1013";
    //获取拓展应用列表
    String GET_EXPAND_LIST_COMMAND = "1011";
    //获取我的滤镜列表
    String GET_MYFILTERS_COMMAND = "1010";
    //获取用户协议 url
    String GET_URL = "1020";
    //添加/删除我的滤镜
    String ADD_DEL_FILTER = "1016";
    //修改用户信息
    String CHANGE_COMMAND = "1019";
    //修改密码
    String CHANGE_PWD_COMMAND = "1006";
    //绑定蓝牙
    String BANDLE_BLE_COMMAND = "1015";
    //忘记密码————请求验证码
    String FORGET_CODE_COMMAND = "1004";
    //忘记密码————重置密码
    String FORGET_RESET_COMMAND = "1005";

    // ============ 请求包字段 ============
    String packmd5 = "packmd5";
    String command = "command";
    String timestamp = "timestamp";
    String ver = "ver";
    String versioncode = "versioncode";
    String errcode = "error";
    String apptype = "apptype";
    String errmsg = "errmsg";
    String language = "language";

    //注册
    String alias = "nickname";
    String registertype = "registertype";

    //修改用户信息
    String edittype = "edittype";
    String mobile = "mobile";

    //绑定蓝牙
    String devid = "devid";

    //添加删除我的滤镜
    String filterno = "filterno";  //滤镜编号
    String optype = "optype";      //1--增 2--删除

    String field1 = "field1";
    String field2 = "field2";
    String field3 = "field3";
    String field4 = "field4";
    String field5 = "field5";

    String getPackmd5();
    void setPackmd5(String packmd5);

    String getCommand();
    void setCommand(String command);

    String getTimestamp();
    void setTimestamp(String timestamp);

    String getVer();
    void setVer(String ver);

    String getErrcode();
    void setErrcode(String errcode);

    String getApptype();
    void setApptype(String apptype);

    String getLanguage();
    void setLanguage(String language);

    String getField1();
    void setField1(String field1);

    String getField2();
    void setField2(String field2);

    String getField3();
    void setField3(String field3);

    String getField4();
    void setField4(String field4);

    String getField5();
    void setField5(String field5);
}
