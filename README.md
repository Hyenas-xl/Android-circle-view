CircleView
=========
#��չ�������ԣ�
    <attr name="circle_radius" format="dimension"/>
    <attr name="circle_border_width" format="dimension"/>
    
#ʹ��ʱ
##����
    <hyenas.xl.circleview.CircleView
        android:layout_width="match_parent"
        android:layout_height="0dp"
        android:layout_weight="6"
        android:background="@drawable/index_background"
        app:circle_radius="70dp"                //Բ�뾶
        app:circle_border_width="10dp"          //Բ���
        android:id="@+id/circle_view"/>
        
    
##����
    CircleView circleView = (CircleView) findViewById(R.id.circle_view);
    circleView.setWeatherText("���� ����������");
    circleView.setViewTitle("���ջ");
    TextValueObject [] textValueObjects = new TextValueObject[]{
            new TextValueObject("��ǰ�ܲ���",false), new TextValueObject("11157",true) , new TextValueObject("7���� | 300ǧ��",false)};
    circleView.setCenterTextValueObjects(textValueObjects);         //ԲȦ�м��ı�
    circleView.setProgressValue(40);        //���� ʵ�ֶ�̬����
    
#Ч��
![image](https://github.com/Hyenas-xl/Android-circle-view/raw/master/icon/view.png)