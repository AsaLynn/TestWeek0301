package com.test.month3.week1.activity;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.demonstrate.DemonstrateUtil;
import com.test.month3.week1.R;

import java.io.File;

public class Test5Activity extends AppCompatActivity implements View.OnClickListener {

    protected Button btnFile;
    protected ImageView itemIv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_test1);
        initView();
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_file) {
            selectedA0();
        }
    }

    private void selectedA0() {
//        File file
//                = new File(Environment.getExternalStorageDirectory(), "3.jpg");

        String path = Environment.getExternalStorageDirectory().getPath();
        DemonstrateUtil.showLogResult("getExternalStorageDirectory:".concat(path));
        String parentPath
                = path
                .concat(File.separator)
                .concat("myglide")
                .concat(File.separator)
                .concat("image");
        DemonstrateUtil.showLogResult("parentPath:".concat(parentPath));
        File fileDir = new File(parentPath);

        String s = fileDir.isDirectory() ? "文件夹" : "文件";
        DemonstrateUtil.showLogResult("isDirectory".concat(s));

        String s2 = fileDir.isFile() ? "文件" : "文件夹";
        DemonstrateUtil.showLogResult("isFile".concat(s2));

        if (fileDir.isDirectory()) {
            if (fileDir.exists()) {
                File[] files = fileDir.listFiles();
                for (File f : files) {
                    DemonstrateUtil.showLogResult(f.getName());
                }
            }
        }
        File file
                = new File(parentPath, "0.jpg");
//        DemonstrateUtil.showLogResult(Environment.getRootDirectory());
        /*File file
                = new File(Environment.getRootDirectory(), "3.jpg");*/
        //File(String pathname)
        //File(String parent, String child)
        //File(File parent, String child)


        Glide
                .with(this)
                .load(file)
                .into(itemIv);
    }

    private void initView() {
        btnFile = (Button) findViewById(R.id.btn_file);
        btnFile.setOnClickListener(Test5Activity.this);
        itemIv = (ImageView) findViewById(R.id.item_iv);
    }
}
