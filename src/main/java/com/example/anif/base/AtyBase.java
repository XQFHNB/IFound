package com.example.anif.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.example.anif.R;

/**
 * @author XQF
 * @created 2017/4/13
 */
public abstract class AtyBase extends AppCompatActivity {

    public abstract Fragment createFrag();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_base);
        FragmentManager fragManager = getSupportFragmentManager();
        Fragment fragment = fragManager.findFragmentById(R.id.aty_base);
        if (fragment == null) {
            fragment = createFrag();
            fragManager.beginTransaction().add(R.id.aty_base, fragment).commit();
        }
    }


    /**
     * 每个子类自带启动方法
     *
     * @param context 上下文
     * @param cls     目标
     */
    public static void start(Context context, Class<?> cls) {
        Intent intent = new Intent(context, cls);
        context.startActivity(intent);
    }


}
