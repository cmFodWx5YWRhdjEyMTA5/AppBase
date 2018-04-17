package com.haijun.shop.util.database;

import android.content.Context;
import android.database.sqlite.SQLiteStatement;

import java.util.ArrayList;

/**
 * @anthor haijun
 * @project name: AppBase
 * @class name：com.haijun.shop.util.database
 * @time 2018-04-16 4:57 PM
 * @describe
 */
public class MyInsertBulkDatabase extends BulkDatabase<FileInfo>{


    public MyInsertBulkDatabase(String sql, ArrayList<FileInfo> list, Context applicationContext) {
        super(sql,list,applicationContext);
    }

    @Override
    public void setStatement(SQLiteStatement stat){
        int number = list.size();
        for(int i = 0; i < number; i++){
            FileInfo fileInfo = list.get(i);//这里的FileInfo是需要你修改成你需要插入的数据信息集合
            if(isBulking){
                stat.bindString(1, fileInfo.fileName);//这里绑定数据
                stat.executeInsert();
            } else{
                break;
            }
        }
    }

    @Override
    protected void rollBack() {
        //回滚后实现你需要的操作。
    }

};