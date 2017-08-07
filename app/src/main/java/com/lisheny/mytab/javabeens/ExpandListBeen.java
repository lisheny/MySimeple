package com.lisheny.mytab.javabeens;

import java.util.List;

/**
 * <pre>
 *     author : lisheny
 *     e-mail : 1020044519@qq.com
 *     time   : 2017/07/19
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class ExpandListBeen {

    /**
     * errcode : 1
     * ver : 1.0
     * errmsg : 查询成功！
     * timestamp : 1500536461606
     * apps : [{"id":1,"appno":"AP10111244","appname":"应用程序1","appcontent":"12312312ad","creattime":"2017-07-12 13:22:22"},{"id":2,"appno":"AP10115417","appname":"应用程序2","appcontent":"84a5s4s4d414","creattime":"2017-07-12 13:22:22"},{"id":3,"appno":"AP10111111","appname":"应用程序3","appcontent":"wd121212312","creattime":"2017-07-12 13:22:22"}]
     */

    private String errcode;
    private String ver;
    private String errmsg;
    private long timestamp;
    private List<AppsBean> apps;

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

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public List<AppsBean> getApps() {
        return apps;
    }

    public void setApps(List<AppsBean> apps) {
        this.apps = apps;
    }

    public static class AppsBean {
        /**
         * id : 1
         * appno : AP10111244
         * appname : 应用程序1
         * appcontent : 12312312ad
         * creattime : 2017-07-12 13:22:22
         */

        private int id;
        private String appno;
        private String appname;
        private String appcontent;
        private String creattime;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getAppno() {
            return appno;
        }

        public void setAppno(String appno) {
            this.appno = appno;
        }

        public String getAppname() {
            return appname;
        }

        public void setAppname(String appname) {
            this.appname = appname;
        }

        public String getAppcontent() {
            return appcontent;
        }

        public void setAppcontent(String appcontent) {
            this.appcontent = appcontent;
        }

        public String getCreattime() {
            return creattime;
        }

        public void setCreattime(String creattime) {
            this.creattime = creattime;
        }
    }
}
