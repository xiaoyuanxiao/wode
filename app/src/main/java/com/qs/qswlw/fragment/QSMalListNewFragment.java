package com.qs.qswlw.fragment;

import android.view.View;
import android.widget.GridView;

import com.qs.qswlw.R;

/**
 * Created by xiaoyu on 2017/4/26.
 */

public class QSMalListNewFragment extends BaseFragment {

    private GridView gv_qsmalllist;

    public static QSMalListNewFragment newInstance() {
        return  new QSMalListNewFragment();
    }
    @Override
    View initView() {
        View inflate = View.inflate(activity, R.layout.fragment_qsmalllist, null);
        gv_qsmalllist = (GridView) inflate.findViewById(R.id.gv_qsmalllist);
        return inflate;
    }

    @Override
    protected void initData() {
        super.initData();
     //   gv_qsmalllist.setAdapter(new QSMalListNewAdapter(activity));
    }
}
