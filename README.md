[CircleView][1]
=========
#扩展两个属性：
    <attr name="circle_radius" format="dimension"/>
    <attr name="circle_border_width" format="dimension"/>
    
#使用时
##配置
    <hyenas.xl.circleview.CircleView
        android:layout_width="match_parent"
        android:layout_height="0dp"
        android:layout_weight="6"
        android:background="@drawable/index_background"
        app:circle_radius="70dp"                //圆半径
        app:circle_border_width="10dp"          //圆宽度
        android:id="@+id/circle_view"/>
        
    
##数据
    CircleView circleView = (CircleView) findViewById(R.id.circle_view);
    circleView.setWeatherText("多云 空气质量良");
    circleView.setViewTitle("今日活动");
    TextValueObject [] textValueObjects = new TextValueObject[]{
            new TextValueObject("当前总步数",false), new TextValueObject("11157",true) , new TextValueObject("7公里 | 300千卡",false)};
    circleView.setCenterTextValueObjects(textValueObjects);         //圆圈中间文本
    circleView.setProgressValue(40);        //进度 实现动态加载
    
#效果
![image](https://github.com/Hyenas-xl/Android-circle-view/raw/master/icon/view.png)
[1]:https://github.com/Hyenas-xl/Android-circle-view
