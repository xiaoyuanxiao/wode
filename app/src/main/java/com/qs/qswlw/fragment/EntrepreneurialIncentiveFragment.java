package com.qs.qswlw.fragment;

import android.app.Dialog;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.qs.qswlw.MyApplication;
import com.qs.qswlw.R;
import com.qs.qswlw.adapter.EntrepreneurialAdapter;
import com.qs.qswlw.bean.EntrepreneurialDialogBean;
import com.qs.qswlw.bean.EntrepreneurialIncentiveBean;
import com.qs.qswlw.bean.MainBean;
import com.qs.qswlw.mynet.HttpSubCribe;
import com.qs.qswlw.mynet.MyRetroService;
import com.qs.qswlw.mynet.ReHttpUtils;
import com.qs.qswlw.okhttp.Iview.IEntrepreneurialView;
import com.qs.qswlw.okhttp.Presenter.EntrepreneurialPresenter;
import com.qs.qswlw.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;

import static android.view.WindowManager.LayoutParams;

/**
 * Created by xiaoyu on 2017/9/6.
 */

public class EntrepreneurialIncentiveFragment extends BaseFragment implements IEntrepreneurialView {
    private ListView lv_sub_entrepreneurialseed;
    private EntrepreneurialPresenter entrepreneurialPresenter = new EntrepreneurialPresenter(this);
    private TextView tv_entrepreneurial_one, tv_entrepreneurial_two, tv_entrepreneurial_three, tv_entrepreneurial_four, tv_entrepreneurial_model;
    private List<EntrepreneurialIncentiveBean.ListModel1Bean> entrepreneurialList;
    private EntrepreneurialAdapter entrepreneurialAdapter;
    private LinearLayout ll_click;
    private String allow_silver;
    private int silver;
    private int i;
    private String exchange_love;
    private Dialog dialog;
    private List<EntrepreneurialIncentiveBean.ListModel1Bean> list_model1;
    private int love;

    public static EntrepreneurialIncentiveFragment newInstener() {
        return new EntrepreneurialIncentiveFragment();
    }

    @Override
    View initView() {
        View inflate = View.inflate(activity, R.layout.sub_entrepreneurialseed, null);
        lv_sub_entrepreneurialseed = (ListView) inflate.findViewById(R.id.lv_sub_entrepreneurialseed);
        tv_entrepreneurial_one = (TextView) inflate.findViewById(R.id.tv_entrepreneurial_one);
        tv_entrepreneurial_two = (TextView) inflate.findViewById(R.id.tv_entrepreneurial_two);
        tv_entrepreneurial_three = (TextView) inflate.findViewById(R.id.tv_entrepreneurial_three);
        tv_entrepreneurial_four = (TextView) inflate.findViewById(R.id.tv_entrepreneurial_four);
        tv_entrepreneurial_model = (TextView) inflate.findViewById(R.id.tv_entrepreneurial_model);
        ll_click = (LinearLayout) inflate.findViewById(R.id.ll_click);
        return inflate;
    }

    @Override
    protected void initData() {
        super.initData();
        entrepreneurialList = new ArrayList<>();
        entrepreneurialAdapter = new EntrepreneurialAdapter(activity, entrepreneurialList);
        lv_sub_entrepreneurialseed.setAdapter(entrepreneurialAdapter);
        entrepreneurialPresenter.getdata(MyApplication.TOKEN, 1, "model1");
    }

    @Override
    protected void setOnclick() {
        ll_click.setOnClickListener(this);
    }


    @Override
    public void setEntrepreneurialData(EntrepreneurialIncentiveBean entrepreneurialData) {
        allow_silver = entrepreneurialData.getAllow_silver();
        silver = entrepreneurialData.getSilver();
        exchange_love = entrepreneurialData.getExchange_love();
        i = Integer.parseInt(allow_silver) / (Integer.parseInt(exchange_love));
        tv_entrepreneurial_one.setText("消费银豆：" + silver + "");
        tv_entrepreneurial_two.setText("可转为创业种子消费银豆：" + allow_silver);
        love = entrepreneurialData.getLove();
        tv_entrepreneurial_three.setText("正在激励创业种子：" + love + "");
        tv_entrepreneurial_four.setText("可转为创业种子数：" + i + "");
        tv_entrepreneurial_model.setText(entrepreneurialData.getModel1() + "");
        list_model1 = entrepreneurialData.getList_model1();
        entrepreneurialList.clear();

        entrepreneurialList.addAll(list_model1);
        entrepreneurialAdapter.notifyDataSetChanged();
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_click:
                showDialog();
                break;
        }
    }

    private void showDialog() {
        View view = activity.getLayoutInflater().inflate(R.layout.dialog_sliverbeantoseed, null);
        //   Dialog dialog = new Dialog(activity, R.style.custom_dialog_style);
        dialog = new Dialog(activity);
        dialog.setContentView(view, new LayoutParams(LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT));
        Window window = dialog.getWindow();
        view.findViewById(R.id.iv_close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        TextView tv_dialog_sliverbean = (TextView) view.findViewById(R.id.tv_dialog_sliverbean);
        TextView tv_dialog_entrepreneurial = (TextView) view.findViewById(R.id.tv_dialog_entrepreneurial);
        TextView tv_describe = (TextView) view.findViewById(R.id.tv_describe);
        Button btn_dialog_determine = (Button) view.findViewById(R.id.btn_dialog_determine);
        final EditText edt_seed = (EditText) view.findViewById(R.id.edt_seed);
        tv_dialog_sliverbean.setText(silver + "");
        tv_describe.setText("注：消费银豆" + exchange_love + "=1创业种子");
        tv_dialog_entrepreneurial.setText(i + "");
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
        btn_dialog_determine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PostDialog(MyApplication.TOKEN, "model1", Integer.parseInt(edt_seed.getText().toString()));
            }
        });
    }

    private void PostDialog(final String token, final String model, final int i) {
        ReHttpUtils.instans().httpRequest(new HttpSubCribe<MainBean<EntrepreneurialDialogBean>>() {

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(MainBean<EntrepreneurialDialogBean> entrepreneurialDialogBeanMainBean) {
                dialog.dismiss();
                if (1 == entrepreneurialDialogBeanMainBean.getSucc())
                    entrepreneurialPresenter.getdata(MyApplication.TOKEN, 1, "model1");
                else
                    ToastUtils.showToast(entrepreneurialDialogBeanMainBean.getMsg());
            }

            @Override
            public Observable<MainBean<EntrepreneurialDialogBean>> getObservable(MyRetroService retrofit) {
                return retrofit.postEntrepreneurialDialog(token, model, i);
            }
        });


    }
}