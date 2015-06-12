CircleView
=========
扩展两个属性：
--------------
    <attr name="circle_radius" format="dimension"/>
    <attr name="circle_border_width" format="dimension"/>
    
使用时
--------------
    <hyenas.xl.circleview.CircleView
        android:layout_width="match_parent"
        android:layout_height="0dp"
        android:layout_weight="6"
        android:background="@drawable/index_background"
        app:circle_radius="70dp"                //圆半径
        app:circle_border_width="10dp"          //圆宽度
        android:id="@+id/circle_view"/>
        

效果
------------
![image](https://github.com/Hyenas-xl/Android-circle-view/raw/master/icon/view.png)