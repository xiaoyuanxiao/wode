package com.qs.qswlw.activity.PersonalCenter;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.qs.qswlw.R;

/**
 * Created by xiaoyu on 2017/4/17.
 */

public class MyInvestActivity extends BaseInfoActivity {

    private TextView tv_myinvest_choice;

    @Override
    public View setConetnView() {
        View inflate = View.inflate(this, R.layout.activity_myinvest, null);
        tv_myinvest_choice = (TextView) inflate.findViewById(R.id.tv_myinvest_choice);
        return inflate;
    }

    @Override
    public void initfindviewByid() {
        super.initfindviewByid();
        tv_titlebar_center.setText("我的投资");
        tv_titlebar_right.setText("投资记录");
    }

    @Override
    public void setOnclick() {
        super.setOnclick();
        tv_titlebar_right.setOnClickListener(this);
        tv_myinvest_choice.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.tv_titlebar_right:
                startActivity(new Intent(this,InvestRecordActivity.class));
                break;
            case R.id.tv_myinvest_choice:
                showDidlog();
                break;
        }
    }
    private void showDidlog() {

        final String[] items = {"中国好物产"};
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);//内部使用构建者的设计模式

        builder.setSingleChoiceItems(items, -1,new DialogInterface.OnClickListener() {//第二个参数是设置默认选中哪一项-1代表默认都不选


            @Override
            public void onClick(DialogInterface dialog, int which) {
                tv_myinvest_choice.setText(items[which]);
                dialog.dismiss();
            }
        });
        builder.create().setCanceledOnTouchOutside(true);
        builder.setCancelable(true);
        builder.create().show();
    }
}
