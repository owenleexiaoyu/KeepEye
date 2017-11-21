package com.owenandroid.keepeye.ui;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.owenandroid.keepeye.R;
import com.owenandroid.keepeye.model.MyUser;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;

/**
 * Created by Administrator on 2017/4/10.
 */
public class InfoActivity extends BaseActivity implements View.OnClickListener {
    private EditText edName,edAge,edDesc;
    private Button btnSex,btnFinish;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        initView();
    }

    private void initView() {
        edName = (EditText) findViewById(R.id.info_ed_name);
        edAge = (EditText) findViewById(R.id.info_ed_age);
        edDesc = (EditText) findViewById(R.id.info_ed_desc);
        btnSex = (Button) findViewById(R.id.info_btn_sex);
        btnFinish = (Button) findViewById(R.id.info_btn_finish_edit);
        //让控件失去输入和点击的效果
        edName.setEnabled(false);
        edAge.setEnabled(false);
        edDesc.setEnabled(false);
        btnSex.setEnabled(false);
        //获取用户数据
        MyUser currentUser = BmobUser.getCurrentUser(MyUser.class);
        edName.setText(currentUser.getUsername());
        edAge.setText(currentUser.getAge()+"");
        edDesc.setText(currentUser.getDesc());
        btnSex.setText(currentUser.isSex()?getString(R.string.boy):getString(R.string.girl));
        //更改性别
        btnSex.setOnClickListener(this);
        //完成修改
        btnFinish.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //加载菜单
        new MenuInflater(this).inflate(R.menu.info_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.info_menu_edit_info:

                //让控件恢复输入和点击的效果
                edName.setBackgroundResource(R.drawable.edit_linear_bg);
                edAge.setBackgroundResource(R.drawable.edit_linear_bg);
                edDesc.setBackgroundResource(R.drawable.edit_linear_bg);
                edName.setEnabled(true);
                edAge.setEnabled(true);
                edDesc.setEnabled(true);
                btnSex.setEnabled(true);
                //显示完成修改的按钮
                btnFinish.setVisibility(View.VISIBLE);
                break;
        }
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.info_btn_sex:
                //弹出对话框选择性别
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("阁下是男是女？");
                builder.setSingleChoiceItems(
                        new String[]{getString(R.string.boy), getString(R.string.girl)},
                        0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        btnSex.setText((which == 0) ?
                                getString(R.string.boy):getString(R.string.girl));
                        dialog.dismiss();
                    }
                });
                builder.show();
                break;
            case R.id.info_btn_finish_edit:
                final ProgressDialog dialog = new ProgressDialog(this);
                dialog.setMessage("修改资料中...");
                dialog.show();
                String name = edName.getText().toString().trim();
                String age = edAge.getText().toString().trim();
                String desc = edDesc.getText().toString().trim();
                boolean isBoy = ("男".equals(btnSex.getText().toString()))?true:false;
                MyUser user = BmobUser.getCurrentUser(MyUser.class);
                user.setUsername(name);
                user.setAge(Integer.parseInt(age));
                user.setDesc(desc);
                user.setSex(isBoy);
                user.update(new UpdateListener() {
                    @Override
                    public void done(BmobException e) {
                        dialog.dismiss();
                        if (e == null){
                            //修改成功
                            Toast.makeText(InfoActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
                            finish();
                        }else{
                            Toast.makeText(InfoActivity.this, "修改失败："+e.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                break;
        }
    }
}
