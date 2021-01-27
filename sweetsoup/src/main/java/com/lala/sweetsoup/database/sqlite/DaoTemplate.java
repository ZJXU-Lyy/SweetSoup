package com.lala.sweetsoup.database.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.lala.sweetsoup.exception.SQLException;

import java.util.ArrayList;
import java.util.List;

/**
 * @param <T> 与dao层相对应的实体类
 * @ProjectName: SweetSoup
 * @Package: com.lala.sweetsoup.database.sqlite
 * @ClassName: DaoTemplate
 * @Description: dao层模板类
 * @Author: Young
 * @CreateDate: 2021/1/27 11:50
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/1/27 11:50
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public abstract class DaoTemplate<T> {
    private SQLiteDBHelper sqLiteDBHelper;

    private String tableName;

    protected abstract ContentValues beanToContentValues(T bean);

    protected abstract T cursorToBean(Cursor cursor);

    public void insert(T bean) {
        try {
            if (null == bean)
                throw new SQLException("param is Null!", tableName, "insert one");
            ContentValues contentValues = beanToContentValues(bean);
            sqLiteDBHelper.insert(tableName, contentValues);
        } catch (SQLException e) {
            e.outputCustomData();
            e.printStackTrace();
        }
    }

    public void insertAll(List<T> beans) {
        for (T bean : beans) {
            insert(bean);
        }
    }

    public void delete(String where, String[] whereArgs) {
        sqLiteDBHelper.delete(tableName, where, whereArgs);
    }

    public void update(T bean, String where, String[] whereArgs) {
        ContentValues contentValues = beanToContentValues(bean);
        sqLiteDBHelper.update(tableName, contentValues, where, whereArgs);
    }

    public List<T> select(String[] columns, String selection, String[] selectionArgs, String groupBy, String having,
                          String orderBy, String limit) {
        List<T> result = new ArrayList<>();
        Cursor cursor = sqLiteDBHelper.query(tableName, columns, selection, selectionArgs, groupBy, having, orderBy, limit);
        try {
            if (null == cursor)
                throw new SQLException("query no result", tableName, "query");
            cursor.moveToFirst();
            for (int i = 0; i < cursor.getCount(); i++) {
                T bean = cursorToBean(cursor);
                result.add(bean);
                cursor.moveToNext();
            }
        } catch (SQLException e) {
            e.outputCustomData();
            e.printStackTrace();
        }
        return result;
    }

    public List<T> select(String where, String[] whereArgs, String orderBy, Integer startIndex, Integer count) {
        String limit = "" + startIndex + "," + count;
        return select(null, where, whereArgs, null, null, orderBy, limit);
    }

    public List<T> select(String where, String[] whereArgs, String orderBy) {
        return select(null, where, whereArgs, null, null, orderBy, null);
    }

    public List<T> select(String where, String[] whereArgs) {
        return select(null, where, whereArgs, null, null, null, null);
    }

    public List<T> selectAll() {
        return select(null, null, null, null, null, null, null);
    }

    public SQLiteDBHelper getSqLiteDBHelper() {
        return sqLiteDBHelper;
    }

    public void setSqLiteDBHelper(SQLiteDBHelper sqLiteDBHelper) {
        this.sqLiteDBHelper = sqLiteDBHelper;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public DaoTemplate(String tableName, Context context, String dbName, int dbVersion, List<String> sql) {
        sqLiteDBHelper = SQLiteDBHelper.getInstance(context, dbName, dbVersion, sql);
        this.tableName = tableName;
    }

    public DaoTemplate(SQLiteDBHelper sqLiteDBHelper, String tableName) {
        this.sqLiteDBHelper = sqLiteDBHelper;
        this.tableName = tableName;
    }

    public DaoTemplate() {
    }
}
