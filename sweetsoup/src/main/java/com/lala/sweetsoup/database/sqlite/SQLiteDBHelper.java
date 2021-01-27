package com.lala.sweetsoup.database.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ProjectName: SweetSoup
 * @Package: com.lala.sweetsoup.database.sqlite
 * @ClassName: SQLiteDBHelper
 * @Description: 扩展SQLiteOpenHelper
 * @Author: Young
 * @CreateDate: 2021/1/25 21:57
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/1/25 21:57
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class SQLiteDBHelper extends SQLiteOpenHelper {
    /**
     * 维护多个dbHelper
     */
    private static Map<String, SQLiteDBHelper> dbHelperMap = new HashMap<>();

    /**
     * 建表语句
     */
    private List<String> createTableSql;

    /**
     * 数据库名
     */
    private String dbName;

    /**
     * 获取DBHelper对象
     *
     * @param context   上下文
     * @param dbName    数据库名
     * @param dbVersion 数据库版本号
     * @param sql       建表语句
     * @return DBHelper
     */
    public static SQLiteDBHelper getInstance(Context context, String dbName, int dbVersion, List<String> sql) {
        SQLiteDBHelper sqLiteDBHelper = null;
        if (!dbHelperMap.containsKey(dbName)) {
            sqLiteDBHelper = new SQLiteDBHelper(context, dbName, dbVersion, sql);
            dbHelperMap.put(dbName, sqLiteDBHelper);
        } else {
            sqLiteDBHelper = dbHelperMap.get(dbName);
        }
        return sqLiteDBHelper;
    }

    /**
     * 适用于所有不返回结果的 SQL 语句
     *
     * @param sql sql语句
     */
    public void execSQL(String sql) {
        SQLiteDBHelper sqLiteDBHelper = dbHelperMap.get(dbName);
        if (sqLiteDBHelper != null) {
            synchronized (sqLiteDBHelper) {
                SQLiteDatabase sqLiteDatabase = sqLiteDBHelper.getWritableDatabase();
                sqLiteDatabase.execSQL(sql);
            }
        }
    }

    /**
     * 适用于所有不返回结果的 SQL 语句
     *
     * @param sql      sql语句
     * @param bindArgs 占位符
     */
    public void execSQL(String sql, Object[] bindArgs) {
        SQLiteDBHelper sqLiteDBHelper = dbHelperMap.get(dbName);
        if (sqLiteDBHelper != null) {
            synchronized (sqLiteDBHelper) {
                SQLiteDatabase sqLiteDatabase = sqLiteDBHelper.getWritableDatabase();
                sqLiteDatabase.execSQL(sql, bindArgs);
            }
        }
    }

    /**
     * SQL 查询
     *
     * @param sql sql语句
     * @return 游标
     */
    public Cursor rawQuery(String sql) {
        SQLiteDBHelper sqLiteDBHelper = dbHelperMap.get(dbName);
        if (sqLiteDBHelper != null) {
            synchronized (sqLiteDBHelper) {
                SQLiteDatabase sqLiteDatabase = sqLiteDBHelper.getReadableDatabase();
                return sqLiteDatabase.rawQuery(sql, null);
            }
        }
        return null;
    }

    /**
     * SQL 查询
     *
     * @param sql      sql语句
     * @param bindArgs 占位符
     * @return 游标
     */
    public Cursor rawQuery(String sql, String[] bindArgs) {
        SQLiteDBHelper sqLiteDBHelper = dbHelperMap.get(dbName);
        if (sqLiteDBHelper != null) {
            synchronized (sqLiteDBHelper) {
                SQLiteDatabase sqLiteDatabase = sqLiteDBHelper.getReadableDatabase();
                return sqLiteDatabase.rawQuery(sql, bindArgs);
            }
        }
        return null;
    }

    /**
     * 插入记录
     *
     * @param table         表名
     * @param contentValues 类似字典数据结构
     */
    public void insert(String table, ContentValues contentValues) {
        SQLiteDBHelper sqLiteDBHelper = dbHelperMap.get(dbName);
        if (sqLiteDBHelper != null) {
            synchronized (sqLiteDBHelper) {
                SQLiteDatabase sqLiteDatabase = sqLiteDBHelper.getWritableDatabase();
                sqLiteDatabase.insert(table, null, contentValues);
            }
        }
    }

    /**
     * 删除记录
     *
     * @param table     表名
     * @param where     条件语句
     * @param whereArgs 条件参数
     */
    public void delete(String table, String where, String[] whereArgs) {
        SQLiteDBHelper sqLiteDBHelper = dbHelperMap.get(dbName);
        if (sqLiteDBHelper != null) {
            synchronized (sqLiteDBHelper) {
                SQLiteDatabase sqLiteDatabase = sqLiteDBHelper.getWritableDatabase();
                sqLiteDatabase.delete(table, where, whereArgs);
            }
        }
    }

    /**
     * 修改记录
     *
     * @param table       表名
     * @param values      新值
     * @param whereClause 条件语句
     * @param whereArgs   条件参数
     */
    public void update(String table, ContentValues values, String whereClause, String[] whereArgs) {
        SQLiteDBHelper sqLiteDBHelper = dbHelperMap.get(dbName);
        if (sqLiteDBHelper != null) {
            synchronized (sqLiteDBHelper) {
                SQLiteDatabase sqLiteDatabase = sqLiteDBHelper.getWritableDatabase();
                sqLiteDatabase.update(table, values, whereClause, whereArgs);
            }
        }
    }

    /**
     * 查询记录
     *
     * @param table         表名
     * @param columns       字段
     * @param selection     条件语句
     * @param selectionArgs 条件参数
     * @param groupBy       分组
     * @param having        与group一起使用分组
     * @param orderBy       排序
     * @param limit         条数限制
     * @return 游标对象
     */
    public Cursor query(String table, String[] columns, String selection, String[] selectionArgs, String groupBy, String having,
                        String orderBy, String limit) {
        SQLiteDBHelper sqLiteDBHelper = dbHelperMap.get(dbName);
        if (sqLiteDBHelper != null) {
            synchronized (sqLiteDBHelper) {
                SQLiteDatabase sqLiteDatabase = sqLiteDBHelper.getReadableDatabase();
                return sqLiteDatabase.query(table, columns, selection, selectionArgs, groupBy, having, orderBy, limit);
            }
        }
        return null;
    }

    /**
     * 回收SQLiteDBHelper对象
     */
    public void clear() {
        SQLiteDBHelper sqLiteDBHelper = dbHelperMap.get(dbName);
        if (sqLiteDBHelper != null) {
            sqLiteDBHelper.close();
        }
        dbHelperMap.remove(sqLiteDBHelper);
    }

    /**
     * 构造
     *
     * @param context        上下文对象
     * @param dbName         数据库名
     * @param dbVersion      数据库版本
     * @param createTableSql 建表语句
     */
    public SQLiteDBHelper(Context context, String dbName, int dbVersion, List<String> createTableSql) {
        super(context, dbName, null, dbVersion);
        this.dbName = dbName;
        this.createTableSql = new ArrayList<>();
        this.createTableSql.addAll(createTableSql);
    }

    /**
     * 初始化数据库
     *
     * @param db 当前数据库
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        if (createTableSql != null)
            for (String sql : createTableSql) {
                db.execSQL(sql);
            }
    }

    /**
     * 数据库版本更新
     *
     * @param db         数据库对象
     * @param oldVersion 旧版本号
     * @param newVersion 新版本号
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
