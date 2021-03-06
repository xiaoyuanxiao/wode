package com.qs.qswlw.Mode;

import android.content.Context;
import android.view.View;
import android.widget.ListView;

import com.qs.qswlw.R;
import com.qs.qswlw.adapter.RankingAdapter;
import com.qs.qswlw.bean.RankingBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiaoyu on 2017/4/5.
 */

public class UnionDayRankingMode extends BaseMode {
    private ListView lv_sub_uniondayranking;
    List<RankingBean.SingleLogBean> data;
    public UnionDayRankingMode(Context context) {
        super(context);
    }

    @Override
    protected View initView() {
        View inflate = View.inflate(context, R.layout.sub_uniondayranking, null);
        lv_sub_uniondayranking = (ListView)inflate.findViewById(R.id.lv_sub_uniondayranking);
        data = new ArrayList<RankingBean.SingleLogBean>();
        lv_sub_uniondayranking.setAdapter(new RankingAdapter(context, data));
        return inflate;
    }
}
