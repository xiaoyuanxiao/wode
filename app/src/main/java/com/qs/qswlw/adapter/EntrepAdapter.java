package com.qs.qswlw.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qs.qswlw.MyApplication;
import com.qs.qswlw.R;
import com.qs.qswlw.view.webviewpb.WebviewActivity;

import java.util.List;

/**
 * Created by 小猴子 on 2017/4/9.
 */

public class EntrepAdapter extends BaseListAdapter<String> {
    Intent intent;
    public EntrepAdapter(Context context, List<String> data) {
        super(context, data);
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = View.inflate(context, R.layout.item_globalentrepreneurshipvalue, null);
        TextView tv_twentypercent_congtv = (TextView) view.findViewById(R.id.tv_twentypercent_congtv);
        TextView tv_twentypercent_busgtv = (TextView) view.findViewById(R.id.tv_twentypercent_busgtv);
//        TextView tv_yesterdayConsumption_gtv = (TextView) view.findViewById(R.id.tv_yesterdayConsumption_gtv);
//        TextView tv_totalEntrepreneurship_gtv = (TextView) view.findViewById(R.id.tv_totalEntrepreneurship_gtv);
//        TextView tv_totalnumber_gtv = (TextView) view.findViewById(R.id.tv_totalnumber_gtv);
//        TextView tv_llianceMerchant_gtv = (TextView) view.findViewById(R.id.tv_llianceMerchant_gtv);
        LinearLayout ll_entrep_width = (LinearLayout) view.findViewById(R.id.ll_entrep_width);
        ll_entrep_width.setLayoutParams(new LinearLayout.LayoutParams(MyApplication.WIDTH, MyApplication.Height-MyApplication.ENTREPHEIGHT));
//        tv_twentypercent_congtv.setText(data.get(0)+"元");
//        tv_twentypercent_busgtv.setText(data.get(1)+"元");
//        tv_yesterdayConsumption_gtv.setText(data.get(2)+"元");
//        tv_totalEntrepreneurship_gtv.setText(data.get(3)+"元");
//        tv_totalnumber_gtv.setText(data.get(4)+"人");
//        tv_llianceMerchant_gtv.setText(data.get(5)+"家");

        tv_twentypercent_congtv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent  = new Intent(context, WebviewActivity.class);
                intent.putExtra("products",data.get(0) );
                context.startActivity(intent);
            }
        });
        tv_twentypercent_busgtv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent  = new Intent(context, WebviewActivity.class);
                intent.putExtra("ella",data.get(1) );
                context.startActivity(intent);

            }
        });


        return view;
    }

    @Override
    public int getCount() {
        return super.getCount() >= 1 ? 1 : 0;
    }

}
