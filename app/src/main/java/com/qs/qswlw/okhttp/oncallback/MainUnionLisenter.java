package com.qs.qswlw.okhttp.oncallback;

import com.qs.qswlw.okhttp.Moudle.UnionBean;

import java.util.List;

/**
 * Created by 小猴子 on 2017/4/10.
 */

public interface MainUnionLisenter extends BaseOnlistener {
    void onSuccess3(List<UnionBean> e);
}