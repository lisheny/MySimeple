package com.lisheny.mytab.javabeens;

/**
 * <pre>
 *     author : lisheny
 *     e-mail : 1020044519@qq.com
 *     time   : 2017/07/20
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class ResponseBeen {

    /**
     * errcode : 1
     * ver : 1.0
     * errmsg : 获取成功
     * url : http://192.168.1.22:8080/jt/UserURL.html
     * timestamp : 1500881760074
     */

    private String errcode;
    private String ver;
    private String errmsg;
    private String url;
    private long timestamp;

    public String getErrcode() {
        return errcode;
    }

    public void setErrcode(String errcode) {
        this.errcode = errcode;
    }

    public String getVer() {
        return ver;
    }

    public void setVer(String ver) {
        this.ver = ver;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
