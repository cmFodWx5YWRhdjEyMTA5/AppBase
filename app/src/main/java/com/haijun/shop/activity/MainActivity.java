package com.haijun.shop.activity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.haijun.shop.R;
import com.haijun.shop.fragment.HomeFragment;
import com.haijun.shop.fragment.MeFragment;
import com.haijun.shop.fragment.MessageFragment;
import com.haijun.shop.fragment.OrderFragment;
import com.haijun.shop.util.LogUtil;
import com.haijun.shop.util.database.BulkDatabase;
import com.haijun.shop.util.database.FileInfo;
import com.haijun.shop.util.database.MyInsertBulkDatabase;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements View.OnClickListener{

    private RadioGroup radioGroup;
    private RadioButton button_1;
    private RadioButton button_2;
    private RadioButton button_3;
    private RadioButton button_4;
    private HomeFragment homeFragment;
    private OrderFragment orderFragment;
    private MessageFragment messageFragment;
    private MeFragment meFragment;
    private List<Fragment> list;
    private FrameLayout frameLayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //初始化页面
    @Override
    protected void initView() {
        frameLayout = (FrameLayout) findViewById(R.id.framelayout);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        changeRadioGroupImageSize();

        //找到四个按钮
        button_1 = (RadioButton) findViewById(R.id.button_1);
        button_2 = (RadioButton) findViewById(R.id.button_2);
        button_3 = (RadioButton) findViewById(R.id.button_3);
        button_4 = (RadioButton) findViewById(R.id.button_4);

        //创建Fragment对象及集合
        homeFragment = new HomeFragment();
        orderFragment = new OrderFragment();
        messageFragment = new MessageFragment();
        meFragment = new MeFragment();

        //将Fragment对象添加到list中
        list = new ArrayList<>();
        list.add(homeFragment);
        list.add(orderFragment);
        list.add(messageFragment);
        list.add(meFragment);

        //设置RadioGroup开始时设置的按钮，设置第一个按钮为默认值
        radioGroup.check(R.id.button_1);
        setCenterText(button_1.getText().toString());


        //设置按钮点击监听
        button_1.setOnClickListener(this);
        button_2.setOnClickListener(this);
        button_3.setOnClickListener(this);
        button_4.setOnClickListener(this);

        //初始时向容器中添加第一个Fragment对象
        addFragment(homeFragment);





    }

    @Override
    protected void initData() {
        LogUtil.i("BulkDatabase","initData");

        ArrayList<FileInfo> list=new ArrayList<>();//这里的FileInfo是需要你修改成你需要插入的数据信息集合
        for (int i=0;i<10000;i++){
            list.add(new FileInfo("test"));
        }

        String sql = "insert into data(fileName) values (?)";
        /*String insertSQL = "insert into " + SQLHelp.GROUP_MEMBER_TABLENAME + "(" + SQLHelp.GROUP_ID
                + "," + SQLHelp.MEMBERS_SUBS_ID + "," + SQLHelp.MEMBER_NICK_NAME + ","
                + SQLHelp.MEMBER_ROLE + "," + SQLHelp.MEMBER_JID + "," + SQLHelp.MEM_UNION_KEY
                + "," + SQLHelp.CREAT_TIME + ", " + SQLHelp.UPDATE_TIME + "," + SQLHelp.ORDER_ID
                + "," + SQLHelp.EXT + ") values " + "(?,?,?,?,?,?,?,?,?,?)";*/
        BulkDatabase mInsertBulkDatabase=new MyInsertBulkDatabase(sql,list,getApplicationContext());//这里建议放入后台线程运行。
        mInsertBulkDatabase.doBulk();


    }

    private void changeRadioGroupImageSize() {
        int[] imgsID = new int[]{R.drawable.radiobutton_image1,R.drawable.radiobutton_image2,R.drawable.radiobutton_image3,R.drawable.radiobutton_image4};
        for(int i=0;i<4;i++){
            RadioButton rb=(RadioButton) radioGroup.getChildAt(i);
            if(i==0){
                rb.setChecked(true);
            }
            int width = (int) getResources().getDimension(R.dimen.x80);
            Drawable d=getResources().getDrawable(imgsID[i]);
            d.setBounds(0, 0,width, width);
            rb.setCompoundDrawables(null,d , null, null);
        }
    }

    @Override
    public void finish() {
        ViewGroup viewGroup = (ViewGroup) getWindow().getDecorView();
        viewGroup.removeAllViews();
        super.finish();
    }

    //点击事件处理
    @Override
    public void onClick(View v) {
        //我们根据参数的id区别不同按钮
        //不同按钮对应着不同的Fragment对象页面
        switch (v.getId()) {
            case R.id.button_1:
                addFragment(homeFragment);
                setCenterText(button_1.getText().toString());
                break;
            case R.id.button_2:
                addFragment(orderFragment);
                setCenterText(button_2.getText().toString());
                break;
            case R.id.button_3:
                addFragment(messageFragment);
                setCenterText(button_3.getText().toString());
                break;
            case R.id.button_4:
                addFragment(meFragment);
                setCenterText(button_4.getText().toString());
                break;
            default:
                break;
        }

    }

    //向Activity中添加Fragment的方法
    public void addFragment(Fragment fragment) {
        //获得Fragment管理器
        FragmentManager fragmentManager = getSupportFragmentManager();
        //使用管理器开启事务
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        //使用事务替换Fragment容器中Fragment对象
        fragmentTransaction.replace(R.id.framelayout,fragment);
        //提交事务，否则事务不生效
        fragmentTransaction.commit();

    }

}
