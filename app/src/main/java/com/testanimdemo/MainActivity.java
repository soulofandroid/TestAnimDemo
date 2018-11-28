package com.testanimdemo;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.testanimdemo.widget.AutoTextView;
import com.testanimdemo.widget.HorizonScrollTextView;
import com.testanimdemo.widget.HorizonScrollTextView2;
import com.testanimdemo.widget.MarqueeView;
import com.testanimdemo.widget.VerticalMarqueeView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private Button mBtnNext;
    private Button mBtnPrev;
    private AutoTextView mTextView02;
    final Handler handler = new Handler();
    // 自定义信息条数
    private static int sCount = 0;
    private List<String> str = new ArrayList<String>();
    private HorizonScrollTextView tv_2;
    private HorizonScrollTextView2 tv_3;
    private MarqueeView marqueeView1;
    private MarqueeView marqueeView2;
    private LinearLayout parentView;

    private VerticalMarqueeView verticalVerticalView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        marqueeView1=findViewById(R.id.marquee_view1);
        marqueeView2=findViewById(R.id.marquee_view2);
        parentView=findViewById(R.id.marqueeLayout);
        init();
    }

    private void init() {

        // 初始化数据
        /*******************************垂直 跑马灯效果  方法1****************************************************/
        str.add("信息1");
        str.add("信息2");
        str.add("信息3");
        sCount = str.size();
        mTextView02 = (AutoTextView) findViewById(R.id.switcher02);
        mTextView02.setText(str.get(0));
        //启动计时器
        handler.postDelayed(runnable, 5000);
        //handler.removeCallbacks(runnable);// 关闭定时器处理
        /*******************************水平 跑马灯效果 ****************************************************/
        //水平滚动
        tv_2 = (HorizonScrollTextView)findViewById(R.id.tv_2);
        tv_2.setText("暂无任何预警信息!     暂无任何预警信息!2        暂无任何预警信息!3          暂无任何预警信息!4    ");
        tv_2.setTextSize(20);
        tv_2.setTextColor(Color.WHITE);
        /*******************************水平 跑马灯效果 ****************************************************/
        //水平滚动2
        tv_3= (HorizonScrollTextView2)findViewById(R.id.tv_3);
        tv_3.setText("金佛IE我就       佛i就困了睡         多久就分手快乐             大家束带结发");
        tv_3.init(getWindowManager());
        tv_3.startScroll();

        /*******************************水平 跑马灯效果 ****************************************************/
        ImageView image=new ImageView(this);
        image.setImageResource(R.mipmap.ic_launcher);
        marqueeView1.addViewInQueue(image);
        ImageView image1=new ImageView(this);
        image1.setImageResource(R.mipmap.ic_launcher);
        marqueeView1.addViewInQueue(image1);
        ImageView image2=new ImageView(this);
        image2.setImageResource(R.mipmap.ic_launcher);
        marqueeView1.addViewInQueue(image2);
        ImageView image3=new ImageView(this);
        image3.setImageResource(R.mipmap.ic_launcher);
        marqueeView1.addViewInQueue(image3);
        marqueeView1.setScrollSpeed(8);
        marqueeView1.setScrollDirection(MarqueeView.LEFT_TO_RIGHT);
        marqueeView1.setViewMargin(15);//间距
        marqueeView1.startScroll();

        /*******************************水平 跑马灯效果 自定义布局****************************************************/
        marqueeView2.setParentView(parentView);
        marqueeView2.setScrollSpeed(8);
        marqueeView2.setScrollDirection(MarqueeView.LEFT_TO_RIGHT);
        marqueeView2.setViewMargin(15);//间距
        marqueeView2.startScroll();
        /*****************************垂直 跑马灯效果  方法2******************************************************/
        verticalVerticalView= (VerticalMarqueeView) findViewById(R.id.verticalMarqueeView);
        List<String> info = new ArrayList<>();
        info.add("公告内容1");
        info.add("公告内容2");
        info.add("公告内容3");
        // xml 中 mvDirection  0 1 2 3 分别代表 动画的方式 bottom_to_top top_to_bottom right_to_left left_to_right
        verticalVerticalView.startWithList(info);
        //每一项的点击事件监听
        verticalVerticalView.setOnItemClickListener(new VerticalMarqueeView.OnItemClickListener() {
            @Override
            public void onItemClick(int position, TextView textView) {
                //执行需求的业务逻辑
                Toast.makeText(MainActivity.this, "点击了第"+position+1+"条公告", Toast.LENGTH_SHORT).show();
            }
        });
        /***********************************************************************************/
    }

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            // TODO Auto-generated method stub
            // 在此处添加执行的代码
            mTextView02.next();
            sCount++;
            if(sCount>=Integer.MAX_VALUE){
                sCount = str.size();
            }
            mTextView02.setText(str.get(sCount % (str.size())));
            if (str.size()>1) {
                handler.postDelayed(this, 5000);// 50是延时时长
            }

        }
    };


    @Override
    public void onClick(View arg0) {
        // TODO Auto-generated method stub
        switch (arg0.getId()) {
            case R.id.next:
                mTextView02.next();
                sCount++;
                break;
            case R.id.prev:
                mTextView02.previous();
                sCount--;
                break;
        }
        // mTextView02.setText(sCount%2==0 ?
        // sCount+"AAFirstAA" :
        // sCount+"BBBBBBB");
        mTextView02.setText(str.get(sCount % 3));
        System.out.println("getH: [" + mTextView02.getHeight() + "]");
    }
}
