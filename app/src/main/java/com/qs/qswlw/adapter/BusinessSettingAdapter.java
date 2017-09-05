package com.qs.qswlw.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.qs.qswlw.R;

/**
 * Created by xiaoyu on 2017/4/17.
 */

/**
 * 设置中心的adapter
 */
public class BusinessSettingAdapter extends BaseAdapter {

    private Context context;

    public BusinessSettingAdapter(Context context) {
        this.context = context;
    }

    public String[] contents = {"我要推荐", "商家二维码", "商家扫码审核", "线下消费录单",
            "录单记录", "我的管理费", "消费银豆", "创业种子","我的金豆",
            "我要提现","商家营业额","消费金豆额度","商家完善资料","商品管理","我的金币","我要转赠",
            "创业直捐","获赠记录","我是老会员","促销抽奖记录","收货地址","商家销售审核","我的购物",
            "我的商城","我的商城设置","商城消费金豆专区","线下门店扫码","选送商品记录","消费日值","我的角色","我的消费额度"};
    public int[] ids = {R.mipmap.wytj, R.mipmap.wytj, R.mipmap.smsh,
            R.mipmap.user_09, R.mipmap.jilu, R.mipmap.user_11, R.mipmap.xfyd,
            R.mipmap.cyzz,R.mipmap.beans,R.mipmap.tx,R.mipmap.yye,
            R.mipmap.edu,R.mipmap.bianji,R.mipmap.spgl,R.mipmap.wdjb,R.mipmap.user08,
            R.mipmap.cyzj,R.mipmap.shouh,R.mipmap.wslhy,R.mipmap.cj,R.mipmap.user_address,
            R.mipmap.dingdan,R.mipmap.daizi,R.mipmap.lingdang,R.mipmap.citie,R.mipmap.jinbi,
            R.mipmap.saoma,R.mipmap.paper,R.mipmap.gift_box,R.mipmap.user_ren,R.mipmap.consume_quota};
    @Override
    public int getCount() {
        return contents.length;
    }

    @Override
    public Object getItem(int i) {
        return ids[ i];
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = View.inflate(context,R.layout.item_setting,null);
        ImageView iv_item_setting = (ImageView) view.findViewById(R.id.iv_item_setting);
        TextView tv_item_setting = (TextView) view.findViewById(R.id.tv_item_setting);
        iv_item_setting.setImageResource(ids[i]);
        tv_item_setting.setText(contents[i]);
        return view;
    }
}