package com.haijun.shop.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.bigkoo.pickerview.OptionsPickerView;
import com.bigkoo.pickerview.TimePickerView;
import com.haijun.shop.R;
import com.haijun.shop.bean.JsonBean;
import com.haijun.shop.util.ScrollSelelctListUtil;
import com.haijun.shop.util.LogUtil;
import com.haijun.shop.util.ToastUtil;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class PersionDataActivity extends BaseActivity {

    private static final String TAG = PersionDataActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_persion_data);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    public void chooseAddress(View view) {
        new ScrollSelelctListUtil().parseData(this, new ScrollSelelctListUtil.OnDataParseResultListener() {
            @Override
            public void success(ArrayList<JsonBean> options1Items, ArrayList<ArrayList<String>> options2Items, ArrayList<ArrayList<ArrayList<String>>> options3Items) {
                showPickerView(options1Items,options2Items,options3Items);
            }

            @Override
            public void failed() {
                ToastUtil.showToask("城市数据解析失败");
            }
        });
    }

    private void showPickerView(final ArrayList<JsonBean> options1Items, final ArrayList<ArrayList<String>> options2Items, final ArrayList<ArrayList<ArrayList<String>>> options3Items) {
        // 弹出选择器
        OptionsPickerView pvOptions = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                String tx = options1Items.get(options1).getPickerViewText()+ options2Items.get(options1).get(options2)+
                        options3Items.get(options1).get(options2).get(options3);
                LogUtil.i(TAG,"tx:"+tx);

                ToastUtil.showToask(tx);
            }
        })
                .setTitleText("城市选择")
                .setDividerColor(Color.BLACK)
                .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
                .setContentTextSize(20)
                .build();

        /*pvOptions.setPicker(options1Items);//一级选择器
        pvOptions.setPicker(options1Items, options2Items);//二级选择器*/
        pvOptions.setPicker(options1Items, options2Items,options3Items);//三级选择器
        pvOptions.show();
    }

    public void chooseDate(View view) {
        //控制时间范围(如果不设置范围，则使用默认时间1900-2100年，此段代码可注释)
        //因为系统Calendar的月份是从0-11的,所以如果是调用Calendar的set方法来设置时间,月份的范围也要是从0-11
        Calendar selectedDate = Calendar.getInstance();
        Calendar startDate = Calendar.getInstance();
        startDate.set(2013, 0, 23);
        Calendar endDate = Calendar.getInstance();
        endDate.set(2019, 11, 28);
        //时间选择器
        TimePickerView pvTime = new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
                // 这里回调过来的v,就是show()方法里面所添加的 View 参数，如果show的时候没有添加参数，v则为null
                ToastUtil.showToask(date.toLocaleString());
            }
        })
                //年月日时分秒 的显示与否，不设置则默认全部显示
                .setType(new boolean[]{true, true, true, false, false, false})
                .setLabel("", "", "", "", "", "")
                .isCenterLabel(false)
                .setDividerColor(Color.DKGRAY)
                .setContentSize(21)
                .setDate(selectedDate)
                .setRangDate(startDate, endDate)
//                .setBackgroundId(0x00FFFFFF) //设置外部遮罩颜色
                .setDecorView(null)
                .build();

        pvTime.show();//弹出时间选择器，传递参数过去，回调的时候则可以绑定此view
    }

    public void chooseSex(View view) {
        final List<String> sexList = new ArrayList<>();
        sexList.add("男");
        sexList.add("女");

        new ScrollSelelctListUtil<String>().singleSelectByScrollList(this, "性别选择", sexList, new ScrollSelelctListUtil.OnOptionsSelectListener() {
            @Override
            public void onSelect(String result) {
                ToastUtil.showToask(result);
            }
        });

    }

    public void chooseHeight(View view) {
        final List<String> heightList = new ArrayList<>();
        for(int i=100;i<220;i++){
            heightList.add(i+"");
        }

        new ScrollSelelctListUtil<String>().singleSelectByScrollList(this, "身高选择", heightList, new ScrollSelelctListUtil.OnOptionsSelectListener() {
            @Override
            public void onSelect(String result) {
                ToastUtil.showToask(result);
            }
        });
    }


}
