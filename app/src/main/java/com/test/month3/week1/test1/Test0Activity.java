package com.test.month3.week1.test1;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.test.month3.week1.R;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;

public class Test0Activity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer_layout2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("侧拉框的使用!");
        setSupportActionBar(toolbar);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        initLeftMenu();

        initRightMenu();

        initMain();
    }

    private void initMain() {
        RecyclerView rv_content = findViewById(R.id.rv_content);
        rv_content.setHasFixedSize(true);
        rv_content.setLayoutManager(new LinearLayoutManager(this) {
            {
                setOrientation(LinearLayoutManager.VERTICAL);
            }
        });
        rv_content.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        rv_content.setAdapter(new CommonAdapter<MenuBean>(this, R.layout.item_recycler_menu, new ArrayList<MenuBean>() {
            {
                for (int i = 0; i < 20; i++) {
                    final int x = i;
                    this.add(new MenuBean() {
                        {
                            this.menuName = "大白菜来了啊" + x;
                        }
                    });
                }
            }
        }) {

            {
                this.setOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                        Toast.makeText(mContext, "亲,选择了" + getDatas().get(position), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                        return false;
                    }
                });
            }

            @Override
            protected void convert(ViewHolder holder, MenuBean menuBean, int position) {
                holder.setText(R.id.tv_title_menu, menuBean.menuName);
                holder.setVisible(R.id.iv_enter, false);
            }
        });
    }

    private void initRightMenu() {
        LinearLayout ll_right = (LinearLayout) findViewById(R.id.ll_right);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        ViewGroup.LayoutParams layoutParams = ll_right.getLayoutParams();
        layoutParams.width = displayMetrics.widthPixels;
        layoutParams.height = displayMetrics.heightPixels;
        ll_right.setLayoutParams(layoutParams);

        findViewById(R.id.iv_back_menu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer.closeDrawer(GravityCompat.END);
            }
        });

    }

    private void initLeftMenu() {
        RecyclerView rv_menu = (RecyclerView) findViewById(R.id.rv_menu);
        rv_menu.setLayoutManager(new LinearLayoutManager(this) {
            {
                this.setOrientation(LinearLayoutManager.VERTICAL);
            }
        });
        rv_menu.setHasFixedSize(true);
        rv_menu.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        rv_menu.setAdapter(new CommonAdapter<MenuBean>(this, R.layout.item_recycler_menu, new ArrayList<MenuBean>() {
            {
                for (int i = 0; i < 15; i++) {
                    final int finalI = i;
                    this.add(new MenuBean() {
                        {
                            this.menuName = "娃哈哈哈" + finalI;
                            if (finalI == 1 || finalI == 3) {
                                this.hasMsg = true;
                            }
                            if (finalI == 4) {
                                this.isMall = true;
                            }

                        }
                    });
                }

            }
        }) {
            {
                this.setOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                        Toast.makeText(mContext, "选择了" + getDatas().get(position).menuName, Toast.LENGTH_SHORT).show();
                        drawer.closeDrawer(GravityCompat.START);
                    }

                    @Override
                    public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                        return false;
                    }
                });
            }

            @Override
            protected void convert(ViewHolder holder, MenuBean menuBean, int position) {
                holder.setText(R.id.tv_title_menu, menuBean.menuName);
                holder.setVisible(R.id.iv_msg, menuBean.hasMsg);
                holder.setVisible(R.id.iv_mall, menuBean.isMall);
            }


        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.drawer_layout1, menu);
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
            drawer.openDrawer(GravityCompat.END);
            return true;
        } else if (id == R.id.action_msg) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
