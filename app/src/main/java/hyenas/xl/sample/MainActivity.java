package hyenas.xl.sample;

import android.os.PersistableBundle;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.util.Timer;
import java.util.TimerTask;

import hyenas.xl.circleview.CircleView;
import hyenas.xl.circleview.model.TextValueObject;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onStart();
        CircleView circleView = (CircleView) findViewById(R.id.circle_view);
        circleView.setProgressValue(40);
        circleView.setWeatherText("多云 空气质量良");
        circleView.setViewTitle("今日活动");
        TextValueObject [] textValueObjects = new TextValueObject[]{
                new TextValueObject("当前总步数",false), new TextValueObject("11157",true) , new TextValueObject("7公里 | 300千卡",false)};
        circleView.setCenterTextValueObjects(textValueObjects);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
