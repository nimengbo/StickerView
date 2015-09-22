package com.example.abner.stickerdemo;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.abner.stickerdemo.utils.FileUtils;
import com.example.abner.stickerdemo.view.StickerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //当前处于编辑状态的贴纸
    private StickerView mCurrentView;

    //存储贴纸列表
    private ArrayList<View> mStickers;

    private RelativeLayout mContentRootView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContentRootView = (RelativeLayout) findViewById(R.id.rl_content_root);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addStickerView();
            }
        });
        mStickers = new ArrayList<>();

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
        if (id == R.id.action_complete) {
            mCurrentView.setInEdit(false);
            generateBitmap();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //添加表情
    private void addStickerView() {
        final StickerView stickerView = new StickerView(this);
        stickerView.setImageResource(R.mipmap.ic_cat);
        stickerView.setOperationListener(new StickerView.OperationListener() {
            @Override
            public void onDeleteClick() {
                mStickers.remove(stickerView);
                mContentRootView.removeView(stickerView);
            }

            @Override
            public void onEdit(StickerView stickerView) {
                mCurrentView.setInEdit(false);
                mCurrentView = stickerView;
                mCurrentView.setInEdit(true);
            }

            @Override
            public void onTop(StickerView stickerView) {
                int position = mStickers.indexOf(stickerView);
                if (position == mStickers.size() - 1) {
                    return;
                }
                StickerView stickerTemp = (StickerView) mStickers.remove(position);
                mStickers.add(mStickers.size(), stickerTemp);
            }
        });
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        mContentRootView.addView(stickerView, lp);
        mStickers.add(stickerView);
        setCurrentEdit(stickerView);
    }

    /**
     * 设置当前处于编辑模式的贴纸
     */
    private void setCurrentEdit(StickerView stickerView) {
        if (mCurrentView != null) {
            mCurrentView.setInEdit(false);
        }
        mCurrentView = stickerView;
        stickerView.setInEdit(true);
    }

    private void generateBitmap() {

        Bitmap bitmap = Bitmap.createBitmap(mContentRootView.getWidth(),
                mContentRootView.getHeight()
                , Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        mContentRootView.draw(canvas);

        String iamgePath = FileUtils.saveBitmapToLocal(bitmap, this);
        Intent intent = new Intent(this, DisplayActivity.class);
        intent.putExtra("image", iamgePath);
        startActivity(intent);
    }

}
