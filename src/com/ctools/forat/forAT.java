package com.ctools.forat;

import com.ctools.forat.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.IOException;

public class forAT extends Activity {
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button bn_start = (Button)findViewById(R.id.start);
        Button bn_result = (Button)findViewById(R.id.result);
        bn_start.setOnClickListener(new startListener());
        bn_result.setOnClickListener(new resultListener());
    }
    public class startListener implements View.OnClickListener{

		@Override
		public void onClick(View v) {
			Button bn_start = (Button)findViewById(R.id.start);
			bn_start.setText("请稍后...");
			try {
				Runtime run = Runtime.getRuntime();
				if(Build.VERSION.SDK_INT<17){
					run.exec("am instrument -w ctrip.android.view.test/android.test.InstrumentationTestRunner");
				}else{
					run.exec("am instrument --user 0 -w ctrip.android.view.test/android.test.InstrumentationTestRunner");
				}
				
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
    }
    public class resultListener implements View.OnClickListener{
		@Override
		public void onClick(View v){
			Intent intent = new Intent(forAT.this,testResultActivity.class);
			startActivity(intent);
		}
	}

    
}
