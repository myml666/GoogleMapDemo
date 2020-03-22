package com.itfitness.googlemapdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * @ProjectName: GoogleMapDemo
 * @Package: com.itfitness.googlemapdemo
 * @ClassName: MainActivity
 * @Description: java类作用描述
 * @Author: 作者名
 * @CreateDate: 2020/3/22 16:38
 * @UpdateUser: 更新者：itfitness
 * @UpdateDate: 2020/3/22 16:38
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public class MainActivity extends AppCompatActivity {
    private Button buttonOne,buttonTwo;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonOne = findViewById(R.id.bt_one);
        buttonTwo = findViewById(R.id.bt_two);
        buttonOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,MapsActivity.class));
            }
        });
        buttonTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,MapListActivity.class));
            }
        });
    }
}
