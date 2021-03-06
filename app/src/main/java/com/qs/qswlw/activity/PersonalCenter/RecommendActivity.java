package com.qs.qswlw.activity.PersonalCenter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.google.zxing.WriterException;
import com.qs.qswlw.R;
import com.qs.qswlw.activity.RegisterActivity;
import com.qs.qswlw.zxing.encoding.EncodingHandler;

import static com.qs.qswlw.R.id.iv_QRCode;


/**
 * Created by xiaoyu on 2017/4/3.
 */

public class RecommendActivity extends BaseInfoActivity implements View.OnLongClickListener {

    private ImageView iv_qrCode;
    private PopupWindow popupWindow;
    private TextView tv_recommend_name, tv_recommend_id;
    private String userid,role;
    private String name;
    private TextView tv_recommend_shopname;
    private String position;
    private LinearLayout ll_recommend;


    @Override
    public View setConetnView() {
        View inflate = View.inflate(this, R.layout.recommend_activity, null);
        iv_qrCode = (ImageView) inflate.findViewById(iv_QRCode);
        tv_recommend_name = (TextView) inflate.findViewById(R.id.tv_recommend_name);
        tv_recommend_id = (TextView) inflate.findViewById(R.id.tv_recommend_id);
        tv_recommend_shopname = (TextView) inflate.findViewById(R.id.tv_recommend_shopname);
        ll_recommend = (LinearLayout) inflate.findViewById(R.id.ll_recommend);
        return inflate;
    }

    @Override
    public void initfindviewByid() {
        super.initfindviewByid();
        Intent intent = getIntent();
        role = intent.getStringExtra("role");
        position = intent.getStringExtra("position");
        if(("position").equals(position)){
            tv_titlebar_center.setText("我的商家码");
        }else{
            tv_titlebar_center.setText("推荐");
            tv_titlebar_right.setText("推荐记录");
        }


    }

    @Override
    public void initData() {
        super.initData();
        Intent intent = getIntent();
        String nickname = intent.getStringExtra("nickname");
        userid = intent.getStringExtra("userid");
        if(("position").equals(position)){
            tv_recommend_shopname.setVisibility(View.VISIBLE);
            ll_recommend.setVisibility(View.GONE);
            String shopname = intent.getStringExtra("shopname");
            tv_recommend_shopname.setText(shopname+"("+nickname+")");
        }else{
            ll_recommend.setVisibility(View.VISIBLE);
            tv_recommend_shopname.setVisibility(View.GONE);
            tv_recommend_name.setText(nickname);

            tv_recommend_id.setText(userid);
        }
        if(role!=null) {
            createQRCode();
        }

    }

    private void createQRCode() {
        try
        {
            //根据输入的文本生成对应的二维码并且显示出来
            Bitmap mBitmap = EncodingHandler.createQRCode(userid, 500);
            if (mBitmap != null) {
                iv_qrCode.setImageBitmap(mBitmap);
            }

        } catch (
                WriterException e
                )

        {
            e.printStackTrace();
        }

    }



    @Override
    public void setOnclick() {
        super.setOnclick();
        tv_titlebar_right.setOnClickListener(this);
        iv_qrCode.setOnLongClickListener(this);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.tv_titlebar_right:
                startActivity(new Intent(this, RecommendedRecords1Activity.class));
                break;

        }
    }

    @Override
    public boolean onLongClick(View view) {
        showpw(iv_qrCode);
        return false;
    }

    private void showpw(ImageView v) {
        //加载布局
        LinearLayout layout = (LinearLayout) LayoutInflater.from(this).inflate(
                R.layout.pw_recommend, null);
        // 实例化popupWindow
        popupWindow = new PopupWindow(layout, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        TextView tv_recommendpw_distinguish = (TextView) layout.findViewById(R.id.tv_recommendpw_distinguish);

        //控制键盘是否可以获得焦点
        popupWindow.setFocusable(true);
        //设置popupWindow弹出窗体的背景
        popupWindow.setBackgroundDrawable(new BitmapDrawable(null, ""));
        WindowManager manager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        //xoff,yoff基于anchor的左下角进行偏移。
        popupWindow.showAtLocation(v, Gravity.BOTTOM, 0, 0);


        tv_recommendpw_distinguish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(("position").equals(position)){
                    Intent intent = new Intent(RecommendActivity.this, RecommendConsumptionActivity .class);
                    startActivity(intent);
                }else{
                    Intent intent = new Intent(RecommendActivity.this, RegisterActivity.class);
                    intent.putExtra("userid",userid);
                    startActivity(intent);
                }
            }

            
        });

    }



}
