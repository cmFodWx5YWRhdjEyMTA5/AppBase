package com.haijun.shop.util.database;

/**
 * @anthor haijun
 * @project name: AppBase
 * @class name：com.haijun.shop.util.database
 * @time 2018-04-16 4:49 PM
 * @describe
 */
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import java.util.ArrayList;


public abstract class BulkDatabase<T> {
    private final String TAG = BulkDatabase.class.getSimpleName();
    private String sql;
    protected boolean isBulking = false;
    protected ArrayList<T> list;//这里采用ArrayList而不是采用List避免向上转型影响性能，如果需要则可以改为List或其他数据结构
    private Context mContext;

    public BulkDatabase() {
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }


    public ArrayList<T> getList() {
        return list;
    }

    public void setList(ArrayList<T> list) {
        this.list = list;
    }

    //这里建议传入Application的Context避免内存泄露问题。
    public BulkDatabase(String sql, ArrayList<T> list,Context context) {
        this.sql = sql;
        this.list = list;
        mContext=context;
    }

    /**
     * 使用批量插入前一定要实现。
     *
     * @param stat
     */
    public abstract void setStatement(SQLiteStatement stat);

    /**
     * 执行批量插入，请在调用钱复写setStatement方法,然后实现循环插入/删除...
     */
    public synchronized void doBulk() {
        Long startTime = System.currentTimeMillis();
        isBulking = true;
        SqliteOpenHelper helper = new SqliteOpenHelper(mContext);//这里的传入你的数据库名称和版本。
        SQLiteDatabase database = helper.getReadableDatabase();
        try {
            database = helper.getReadableDatabase();
            helper.onCreate(database);
            SQLiteStatement stat = database.compileStatement(sql);
            database.beginTransaction();
            setStatement(stat);
            if (isBulking) {
                database.setTransactionSuccessful();// 这里设置是否完成数据库的批量操作，如果不设置则自动回滚，也就是放弃批量操作。
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.i(TAG, "doBulk error:" + e.getMessage());
        }
        finally {
            database.endTransaction();//处理完成
        }

        if(!isBulking){
            rollBack();
        }
        Long endTime = System.currentTimeMillis();
        Log.i(TAG, "bulk use time:" + (endTime - startTime));
    }

    /**
     *回滚之后需要进行的操作。
     */
    protected abstract void rollBack();
    /**
     *取消批量处理操作
     */
    public void cancelBulk() {
        isBulking = false;
    }
    public boolean isCancel(){
        return !isBulking;
    }
}
