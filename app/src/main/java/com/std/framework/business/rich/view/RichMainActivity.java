package com.std.framework.business.rich.view;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;

import com.library.imageloader.core.ImageLoader;
import com.library.util.BitmapUtil;
import com.std.framework.R;
import com.std.framework.basic.BaseTitleActivity;
import com.std.framework.business.rich.adapter.ListViewAdapter;
import com.std.framework.business.rich.dslv.DragSortListView;
import com.std.framework.business.rich.gallery.GalleryActivity;
import com.std.framework.core.NavigationBar;
import com.std.framework.util.DimenUtil;

import java.util.ArrayList;
import java.util.List;

public class RichMainActivity extends BaseTitleActivity implements View.OnClickListener, MenuPopupView.OperateClickListener,CompoundButton.OnCheckedChangeListener{
    private View v_tool;
//    private RecyclerView recyclerView;
    //    private RecyclerViewAdapter adapter;
    private ListViewAdapter listViewAdapter;
    private DragSortListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullscreen);
        initView();
    }

    @Override
    public void onNavigationBar(NavigationBar navigation) {
        navigation.setTitle("富文本编辑器");
    }

    private void initView() {
        View v_add = findViewById(R.id.btn_add);
        v_tool = findViewById(R.id.ll_tool_bar);
        CheckBox editCheckBox = (CheckBox) findViewById(R.id.cbx_edit);
        editCheckBox.setOnCheckedChangeListener(this);
//        recyclerView = (RecyclerView) findViewById(R.id.rv_content);
        listView = (DragSortListView) findViewById(R.id.lsv_content);
        listView.setDragEnabled(false);
//        adapter = new RecyclerViewAdapter(this);
//        recyclerView.setAdapter(adapter);
        listViewAdapter = new ListViewAdapter(this);
        listView.setAdapter(listViewAdapter);

        v_add.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_add:
                MenuPopupView popupView = new MenuPopupView(this);
                popupView.setOperateClickListener(this);
                popupView.showAsDropDown(v, 0, -300);
                break;
        }
    }


    @Override
    public void onClickText() {
        EditText editText = new EditText(this);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        editText.setLayoutParams(params);
        editText.setText("编辑内容");
        listViewAdapter.addEdit(editText);
        listView.setSelection(listViewAdapter.getCount() - 1);
    }

    @Override
    public void onClickImage() {
        GalleryActivity.openActivity(this, false, 5, 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            ArrayList<String> list = data.getStringArrayListExtra("PHOTOS");
            addImageView(list);
            listView.setSelection(listViewAdapter.getCount() - 1);
        }
    }

    private void addImageView(List<String> file) {
        if (file != null && file.size() != 0) {
            List<ImageView> imageViews = new ArrayList<>();
            for (String path : file) {
                ImageView imageView = new ImageView(this);
                ViewGroup.LayoutParams params = new ViewGroup.LayoutParams((int) DimenUtil.dpToPx(200), (int)DimenUtil.dpToPx(200));
                imageView.setLayoutParams(params);
                Bitmap bitmap = BitmapUtil.decodeBitmapFromFile(path,(int)DimenUtil.dpToPx(200),(int)DimenUtil.dpToPx(200));
                listViewAdapter.addImage(imageView);
//                ImageLoader.getInstance().displayImage("\"file://\""+path,imageView);
                imageView.setImageBitmap(bitmap);
//                imageViews.add(imageView);
            }
//            listViewAdapter.addImage(imageViews);
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        listView.setDragEnabled(isChecked);
    }
}
