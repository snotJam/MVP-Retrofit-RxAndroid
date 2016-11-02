package net;

import java.util.List;

/**
 * Created by NewNet on 2016/9/19.
 */
public class HomeFragmentResponse {
    /**
     * rcode : 1
     * ldata : [{"limageurl":"http://news.bwlc.net/uploads/uploads/allimg/160622/QQ截图20160725105335-lp.png","lskipurl":"http://news.bwlc.net/html/zixun/caishi/2016/0818/476.html"},{"limageurl":"http://news.bwlc.net/uploads/uploads/allimg/160622/QQ截图20160725105302-lp.png","lskipurl":"http://news.bwlc.net/html/zixun/beijingfucai/2016/0818/475.html"},{"limageurl":"http://news.bwlc.net/uploads/uploads/allimg/234989-140R10P44129-lp-lp-lp-lp.jpg","lskipurl":"http://news.bwlc.net/html/zixun/shipin/2016/0818/474.html"},{"limageurl":"http://news.bwlc.net/uploads/uploads/allimg/234989-140R10P44129-lp.jpg","lskipurl":"http://news.bwlc.net/html/zixun/caishi/2016/0818/473.html"},{"limageurl":"http://news.bwlc.net/uploads/uploads/allimg/234989-140R10P44129-lp-lp-lp.jpg","lskipurl":"http://news.bwlc.net/html/zixun/2016/0818/472.html"}]
     * lotjtdata : [{"jttitle":"","jturl":""}]
     */

    private int rcode;
    /**
     * limageurl : http://news.bwlc.net/uploads/uploads/allimg/160622/QQ截图20160725105335-lp.png
     * lskipurl : http://news.bwlc.net/html/zixun/caishi/2016/0818/476.html
     */

    private List<LdataBean> ldata;
    /**
     * jttitle :
     * jturl :
     */

    private List<LotjtdataBean> lotjtdata;

    public int getRcode() {
        return rcode;
    }

    public void setRcode(int rcode) {
        this.rcode = rcode;
    }

    public List<LdataBean> getLdata() {
        return ldata;
    }

    public void setLdata(List<LdataBean> ldata) {
        this.ldata = ldata;
    }

    public List<LotjtdataBean> getLotjtdata() {
        return lotjtdata;
    }

    public void setLotjtdata(List<LotjtdataBean> lotjtdata) {
        this.lotjtdata = lotjtdata;
    }

    public static class LdataBean {
        private String limageurl;
        private String lskipurl;

        public String getLimageurl() {
            return limageurl;
        }

        public void setLimageurl(String limageurl) {
            this.limageurl = limageurl;
        }

        public String getLskipurl() {
            return lskipurl;
        }

        public void setLskipurl(String lskipurl) {
            this.lskipurl = lskipurl;
        }
    }

    public static class LotjtdataBean {
        private String jttitle;
        private String jturl;

        public String getJttitle() {
            return jttitle;
        }

        public void setJttitle(String jttitle) {
            this.jttitle = jttitle;
        }

        public String getJturl() {
            return jturl;
        }

        public void setJturl(String jturl) {
            this.jturl = jturl;
        }
    }

}
