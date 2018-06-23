package com.test.month3.week1.activity;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.example.demonstrate.DemonstrateUtil;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.decoration.SpaceDecoration;
import com.test.month3.week1.R;
import com.test.month3.week1.adapter.EsayGirlAdapter;
import com.test.month3.week1.utils.DensityUtil;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class Test1Activity extends AppCompatActivity implements View.OnClickListener {

    public void initData() {


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_my_images);
        initView();

    }

    @Override
    public void onClick(View view) {

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


     /*   Glide
                .with(this)
                .load(file)
                .into(itemIv);*/
    }

    private void initView() {
        recyclerView = (EasyRecyclerView) findViewById(R.id.recyclerView);

      /*  listData = new ArrayList<>();*/

        esayGirlAdapter = new EsayGirlAdapter(this, getGirls());

        initLayoutManager();

        SpaceDecoration spaceDecoration
                = new SpaceDecoration(DensityUtil.dp2px(this, 2));
        recyclerView.addItemDecoration(spaceDecoration);

        recyclerView.setAdapter(esayGirlAdapter);

        esayGirlAdapter.setOnClickHolderItemListener(new EsayGirlAdapter.OnClickHolderItemListener() {
            @Override
            public void onItemClick(BaseViewHolder holder, int position) {
               /* startGirlDetail((ArrayList<File>) esayGirlAdapter.getAllData(), position, holder.itemView);*/
            }
        });
    }


    private EasyRecyclerView recyclerView;

    private EsayGirlAdapter esayGirlAdapter;

    private void initLayoutManager() {
        final StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        staggeredGridLayoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);
        recyclerView.getRecyclerView().addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                //防止第一行到顶部有空白区域
                staggeredGridLayoutManager.invalidateSpanAssignments();
            }
        });
    }

    private List<File> getGirls() {

//        String parentPath
//                = Environment
//                .getExternalStorageDirectory()
//                .getPath()
//                .concat(File.separator)
//                .concat("myglide")/**/
//                .concat(File.separator)
//                .concat("image");/**/

        String parentPath
                = Environment
                .getExternalStorageDirectory()
                .getPath()
                .concat(File.separator)
                .concat("mypics");

        DemonstrateUtil.showLogResult("parentPath:".concat(parentPath));
        File fileDir = new File(parentPath);

        if (fileDir.isDirectory()) {
            if (fileDir.exists()) {
                File[] files = fileDir.listFiles();
                List<File> fileList = Arrays.asList(files);
                return fileList;
                /*esayGirlAdapter.addAll(fileList);*/
            }
        }

        return null;
    }
}
