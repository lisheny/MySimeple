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
public class FilterListBeen {

    /**
     * errcode : 1
     * ver : 1.0
     * errmsg : 查询成功！
     * filters : [{"id":1,"filterno":"FL10454862021","filtername":"滤镜1","filtercontent":"10HBNY23D8E9RD","remark":"","creattime":"2017-07-12 13:22:22"},{"id":2,"filterno":"FL10454865431","filtername":"滤镜2","filtercontent":"10HBNY23D8E9RD","remark":"","creattime":"2017-07-12 11:14:23"},{"id":3,"filterno":"FL10454867894","filtername":"滤镜3","filtercontent":"10HBNY23D8E9RD","remark":"","creattime":"2017-07-12 11:14:23"},{"id":4,"filterno":"FL10454864211","filtername":"滤镜4","filtercontent":"10HBNY23D8E9RD","remark":"","creattime":"2017-07-12 11:14:23"}]
     * timestamp : 1500535964689
     */

    private String errcode;
    private String ver;
    private String errmsg;
    private long timestamp;
    private List<FiltersBean> filters;

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

    public List<FiltersBean> getFilters() {
        return filters;
    }

    public void setFilters(List<FiltersBean> filters) {
        this.filters = filters;
    }

    public static class FiltersBean {
        /**
         * id : 1
         * filterno : FL10454862021
         * filtername : 滤镜1
         * filtercontent : 10HBNY23D8E9RD
         * remark :
         * creattime : 2017-07-12 13:22:22
         */

        private int id;
        private String filterno;
        private String filtername;
        private String filtercontent;
        private String remark;
        private String creattime;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getFilterno() {
            return filterno;
        }

        public void setFilterno(String filterno) {
            this.filterno = filterno;
        }

        public String getFiltername() {
            return filtername;
        }

        public void setFiltername(String filtername) {
            this.filtername = filtername;
        }

        public String getFiltercontent() {
            return filtercontent;
        }

        public void setFiltercontent(String filtercontent) {
            this.filtercontent = filtercontent;
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
