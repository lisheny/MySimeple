package com.lisheny.mytab.javabeens;

import java.util.List;

/**
 * <pre>
 *     author : lisheny
 *     e-mail : 1020044519@qq.com
 *     time   : 2017/07/18
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class FaceListBeen {

    /**
     * errcode : 1
     * ver : 1.0
     * errmsg : 查询成功！
     * exps : [{"id":1,"expno":"EX1425545112","expname":"表情1","expcontent":"http://www.zclear.com/download/1.exp","remark":"","creattime":"2017-07-12 13:22:22"},{"id":2,"expno":"EX1425546541","expname":"表情2","expcontent":"http://www.zclear.com/download/2.exp","creattime":"2017-07-12 13:22:22"},{"id":3,"expno":"EX1425546321","expname":"表情3","expcontent":"http://www.zclear.com/download/3.exp","creattime":"2017-07-12 13:22:22"}]
     * timestamp : 1500536102006
     */

    private String errcode;
    private String ver;
    private String errmsg;
    private long timestamp;
    private List<ExpsBean> exps;

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

    public List<ExpsBean> getExps() {
        return exps;
    }

    public void setExps(List<ExpsBean> exps) {
        this.exps = exps;
    }

    public static class ExpsBean {
        /**
         * id : 1
         * expno : EX1425545112
         * expname : 表情1
         * expcontent : http://www.zclear.com/download/1.exp
         * remark :
         * creattime : 2017-07-12 13:22:22
         */

        private int id;
        private String expno;
        private String expname;
        private String expcontent;
        private String remark;
        private String creattime;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getExpno() {
            return expno;
        }

        public void setExpno(String expno) {
            this.expno = expno;
        }

        public String getExpname() {
            return expname;
        }

        public void setExpname(String expname) {
            this.expname = expname;
        }

        public String getExpcontent() {
            return expcontent;
        }

        public void setExpcontent(String expcontent) {
            this.expcontent = expcontent;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getCreattime() {
            return creattime;
        }

        public void setCreattime(String creattime) {
            this.creattime = creattime;
        }
    }
}
