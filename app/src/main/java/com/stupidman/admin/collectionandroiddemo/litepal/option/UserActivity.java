package com.stupidman.admin.collectionandroiddemo.litepal.option;

import android.app.Activity;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.stupidman.admin.collectionandroiddemo.R;
import com.stupidman.admin.collectionandroiddemo.litepal.bean.Users;

import org.litepal.crud.DataSupport;
import org.litepal.tablemanager.Connector;

import java.util.List;

/**
 * Created by admin on 2015/5/26.
 */
public class UserActivity extends Activity implements View.OnClickListener {

    private Button btnLitepalCreate;
    private TextView tvLitepalResult;
    private Button btnLitepalModify;
    private Button btnLitepalDelete;
    private Button btnLitepalQuery;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.litepal_activity);

        init();
    }

    private void init() {
        SQLiteDatabase database = Connector.getDatabase();
        tvLitepalResult = (TextView) findViewById(R.id.tv_litepal_result);
        btnLitepalCreate = (Button) findViewById(R.id.btn_litepal_create);
        btnLitepalModify = (Button) findViewById(R.id.btn_litepal_modify);
        btnLitepalDelete  = (Button) findViewById(R.id.btn_litepal_delete);
        btnLitepalQuery = (Button) findViewById(R.id.btn_litepal_query);
        btnLitepalCreate.setOnClickListener(this);
        btnLitepalModify.setOnClickListener(this);
        btnLitepalDelete.setOnClickListener(this);
        btnLitepalQuery.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.btn_litepal_create:
                Users users = new Users();
                users.setUsername("root");
                users.setPassword("111");
                users.save();
                if (users.isSaved()){
                    tvLitepalResult.setText("添加数据成功");
                }else{
                    tvLitepalResult.setText("添加数据失败");
                }
                break;

            case R.id.btn_litepal_modify:
                ContentValues values = new ContentValues();
                values.put("username", "stupid");
                /*//修改单条数据
                DataSupport.update(Users.class, values, 1);*/
                //修改全部数据
                DataSupport.updateAll(Users.class, values);
               /* //修改多条数据
                DataSupport.updateAll(Users.class, values, "username = ? and password = ?", "root", "111");*/
                break;

            case R.id.btn_litepal_delete:
                /*//删除一条数据，根据id
                DataSupport.delete(Users.class, 2);*/
                //删除全部数据
                DataSupport.deleteAll(Users.class);
               /* //删除多条数据
                DataSupport.deleteAll(Users.class, "username = ? and password = ?", "root", "111");*/
                break;

            case R.id.btn_litepal_query:
                /**
                 * 简单查询
                 */
                /*//根据id查询一条数据
                Users user = DataSupport.find(Users.class, 1);
                //查询表中的第一条数据
                Users userFirst = DataSupport.findFirst(Users.class);
                //查询表中最后一条数据
                Users userLast = DataSupport.findLast(Users.class);
                //根据id查询多条数据
                List<Users> usersList = DataSupport.findAll(Users.class, 1, 3, 5, 7);
                long[] ids = new long[] {1, 3, 5, 7};
                List<Users> usersList1 = DataSupport.findAll(Users.class, ids);*/
                //查询所有数据
                List<Users> userAll = DataSupport.findAll(Users.class);

                /**
                 * 连缀查询
                 */
              /*  List<Users> usersList2 = DataSupport.select("username", "password")
                        .where("username = ?", "root")
                        .order("username desc")
                        .limit(10)  //每次只查询10条数据
                        .offset(10) //实现分页查询，设置偏移量
                        .find(Users.class);*/

                /**
                 * 激进查询
                 * 可以查询关联表中的数据
                 * find()方法中最后一个可选参数isEager设置为true时即可进行激进查询
                 * 这种方法不推荐使用，可以使用下列方法：
                 * 在Users中修改getCommentsList()为--
                 * public List<Comments> getCommentsList() {
                 *     return DataSupp
                 * }
                 *
                 */
                /*Users users1 = DataSupport.find(Users.class, 1, true);
                List<Comments> commentsList = users1.getCommentsList();*/

                /**
                 * 原生查询
                 */
               // Cursor cursor = DataSupport.findBySQL("select * from users where id = ?", "1");

                if (userAll.size() > 0) {
                    tvLitepalResult.setText("查询结果为：" + userAll.get(0).getUsername() + ":" + userAll.get(0).getPassword());
                } else {
                    Toast.makeText(this, "没有需要的数据", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
