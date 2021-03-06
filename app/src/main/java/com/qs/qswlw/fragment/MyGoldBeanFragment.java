package com.qs.qswlw.fragment;

import android.view.View;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.qs.qswlw.MyApplication;
import com.qs.qswlw.R;
import com.qs.qswlw.activity.PersonalCenter.VenturegoldBeansActivity;
import com.qs.qswlw.adapter.VentureGoldBeansAdapter;
import com.qs.qswlw.bean.VenturegoldBean;
import com.qs.qswlw.okhttp.Iview.IVenturegoldBeansView;
import com.qs.qswlw.okhttp.Presenter.VenturegoldBeanPresenter;
import com.qs.qswlw.utils.ActivityManagerUtils;
import com.qs.qswlw.view.SwipeRefreshView;

import java.util.ArrayList;
import java.util.List;

import static com.qs.qswlw.R.id.tv_sub_mygoldenbean_one;
import static com.qs.qswlw.R.id.tv_sub_mygoldenbean_two;


/**
 * Created by xiaoyu on 2017/9/8.
 */

public class MyGoldBeanFragment extends BaseFragment implements IVenturegoldBeansView {
    public static String GIVE = "give";
    public static String TJJD = "tjjd";
    VenturegoldBeanPresenter venturegoldBeanPresenter = new VenturegoldBeanPresenter(this);
    ListView lv_sub_myGoldenBean, lv_sub_myGoldenBean2;
    int page=1;
    BaseAdapter ventureGoldBeansAdapter, ventureGoldBeansAdapter2;
    String ischeckmodel = "model1";
    List<VenturegoldBean.ListBean> list1 = new ArrayList<>();
    List<VenturegoldBean.ListBean> list2 = new ArrayList<>();
    private RadioButton rb_myGoldenBean_left, rb_myGoldenBean_right;
    private TextView tv_sub_mygoldenbean_topone,tv_sub_mygoldenbean_toptwo;
    private SwipeRefreshView swipeRefreshView,swipeRefreshView1;
    private String Gold_type = "";

    public static MyGoldBeanFragment newInstance(String type) {
        MyGoldBeanFragment myGoldBeanFragment = new MyGoldBeanFragment();
        myGoldBeanFragment.setGoldType(type);
        return myGoldBeanFragment;
    }

    private void setGoldType(String Gold_type) {
        this.Gold_type = Gold_type;
    }

    @Override
    View initView() {
        View inflate = View.inflate(getActivity(), R.layout.sub_mygoldenbean, null);
        rb_myGoldenBean_left = (RadioButton) inflate.findViewById(R.id.rb_myGoldenBean_left);
        rb_myGoldenBean_right = (RadioButton) inflate.findViewById(R.id.rb_myGoldenBean_right);
        tv_sub_mygoldenbean_topone = (TextView) inflate.findViewById(R.id.tv_sub_mygoldenbean_topone);
        tv_sub_mygoldenbean_toptwo = (TextView) inflate.findViewById(R.id.tv_sub_mygoldenbean_toptwo);

        return inflate;
    }

    @Override
    protected void setOnclick() {
        rb_myGoldenBean_left.setOnClickListener(this);
        rb_myGoldenBean_right.setOnClickListener(this);
        initButton();
        initList();
        checklisy(true);
    }

    public void refreshlist() {
        initListdata(ischeckmodel,page);
    }

    void initListdata(String modetype,int page) {
        venturegoldBeanPresenter.getData(MyApplication.TOKEN, page, modetype, Gold_type, ((VenturegoldBeansActivity) getActivity()).getType());
    }

    void initButton() {
        if (Gold_type.equals(TJJD)) {
            rb_myGoldenBean_left.setText("创业激励");
            rb_myGoldenBean_right.setText("创新激励");
            initListViewtitle("获奖时间");
        } else if (Gold_type.equals(GIVE)) {
            rb_myGoldenBean_left.setText("100%激励");
            rb_myGoldenBean_right.setText("20%激励");
            initListViewtitle("获赠时间");
        } else if (Gold_type.equals("")) {
            rb_myGoldenBean_left.setText("创业激励");
            rb_myGoldenBean_right.setText("创新激励");
        }
    }

    private void initListViewtitle(String nameone ) {
        view.findViewById(R.id.tv_sub_mygoldenbean_three).setVisibility(View.GONE);
        view.findViewById(R.id.tv_sub_mygoldenbean_four).setVisibility(View.GONE);
        ((TextView) view.findViewById(tv_sub_mygoldenbean_two)).setText("获得金豆");
        ((TextView) view.findViewById(tv_sub_mygoldenbean_one)).setText(nameone);
    }

    void initList() {

        ventureGoldBeansAdapter = new VentureGoldBeansAdapter(getActivity(), list1, Gold_type);
        ventureGoldBeansAdapter2 = new VentureGoldBeansAdapter(getActivity(), list2, Gold_type);
        View inflate = View.inflate(getActivity(), R.layout.item_foot_businesstonover, null);
        lv_sub_myGoldenBean = (ListView) view.findViewById(R.id.lv_sub_myGoldenBean);
        swipeRefreshView = (SwipeRefreshView) view.findViewById(R.id.lv_sub_myGoldenBean_sw);
        swipeRefreshView1 = (SwipeRefreshView) view.findViewById(R.id.lv_sub_myGoldenBean_sw1);
        lv_sub_myGoldenBean.setAdapter(ventureGoldBeansAdapter);
        lv_sub_myGoldenBean2 = (ListView) view.findViewById(R.id.lv_sub_myGoldenBean1);
//        lv_sub_myGoldenBean.addFooterView(inflate);
//        lv_sub_myGoldenBean2.addFooterView(inflate);
        lv_sub_myGoldenBean2.setAdapter(ventureGoldBeansAdapter2);
        //上拉监听

        swipeRefreshView.setOnLoadListener(new SwipeRefreshView.OnLoadListener() {
            @Override
            public void onLoad() {
                initListdata("model1",page);
            }
        });
        swipeRefreshView1.setOnLoadListener(new SwipeRefreshView.OnLoadListener() {
            @Override
            public void onLoad() {
                initListdata("model2",page);
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rb_myGoldenBean_left:
                page=1;
                checklisy(true);
                break;
            case R.id.rb_myGoldenBean_right:
                page=1;
                checklisy(false);
                break;
        }
    }

    void checklisy(boolean checked) {
        if (checked) {
            initListdata("model1",page);
            lv_sub_myGoldenBean.setVisibility(View.VISIBLE);
            lv_sub_myGoldenBean2.setVisibility(View.GONE);
        } else {
            lv_sub_myGoldenBean.setVisibility(View.GONE);
            lv_sub_myGoldenBean2.setVisibility(View.VISIBLE);
            initListdata("model2",page);
        }
    }

    @Override
    public void setVenturegoldBeanData(VenturegoldBean venturegoldBeanData, String modeltype) {
        swipeRefreshView.setLoading(false);
        swipeRefreshView1.setLoading(false);
        List<VenturegoldBean.ListBean> list = venturegoldBeanData.getList();
        ischeckmodel = modeltype;
        if (modeltype.equals("model1")) {
            if(page==1&&(list == null || list.size() == 0)){
                return;
            }else if(page>1&&(list == null || list.size() == 0)){
                swipeRefreshView.setLoadingEnd();
                return;
            }else if(page==1&&list!=null){
                list1.clear();
            }
            list1.addAll(list);
            ventureGoldBeansAdapter.notifyDataSetChanged();
            page++;
        } else {
            if(page==1&&(list == null || list.size() == 0)){
                return;
            }else if(page>1&&(list == null || list.size() == 0)){
                swipeRefreshView1.setLoadingEnd();
                return;
            }else if(page==1&&list!=null){
                list2.clear();
            }
            list2.addAll(list);
            ventureGoldBeansAdapter2.notifyDataSetChanged();
            page++;
        }
       if( Gold_type.equals(TJJD)){
           tv_sub_mygoldenbean_topone.setText("累计创业金豆："+venturegoldBeanData.getTjjd().getGold());
           tv_sub_mygoldenbean_toptwo.setText("累计消费金豆："+venturegoldBeanData.getTjjd().getTaxgold());
        } else if (Gold_type.equals(GIVE)) {
           tv_sub_mygoldenbean_topone.setText("累计创业金豆："+venturegoldBeanData.getGive_gold().getGold());
           tv_sub_mygoldenbean_toptwo.setText("累计消费金豆："+venturegoldBeanData.getGive_gold().getTaxgold());
       } else if (Gold_type.equals("")) {
           tv_sub_mygoldenbean_topone.setText("累计创业金豆："+venturegoldBeanData.getModel().getGold());
           tv_sub_mygoldenbean_toptwo.setText("累计消费金豆："+venturegoldBeanData.getModel().getTaxgold());
       }

    }

    @Override
    public void setTokenFail() {
        ActivityManagerUtils.getInstance().tokenfailfg(getActivity());
    }
}
