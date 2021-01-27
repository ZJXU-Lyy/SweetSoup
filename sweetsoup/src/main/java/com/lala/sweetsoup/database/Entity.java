package com.lala.sweetsoup.database;

import android.database.Cursor;

import com.lala.sweetsoup.core.lang.Dict;

import java.util.ArrayList;
import java.util.Set;

/**
 * @ProjectName: SweetSoup
 * @Package: com.lala.sweetsoup.database
 * @ClassName: Entity
 * @Description: 以String->Object的形式将数据库表字段名与实际数据相对应
 * @Author: Young
 * @CreateDate: 2021/1/25 16:49
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/1/25 16:49
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class Entity extends Dict {
    /**
     * 对应表名
     */
    private String tableName;

    /**
     * 实例化操作
     *
     * @return newEntity
     */
    public static Entity create() {
        return new Entity();
    }

    /**
     * 实例化操作
     *
     * @param tableName 表名
     * @return newEntity
     */
    public static Entity create(String tableName) {
        return new Entity(tableName);
    }

    /**
     * 获取字段列表
     *
     * @return fieldNames
     */
    public ArrayList<String> getFieldNames() {
        Set<String> fieldNames = keySet();
        return new ArrayList<>(fieldNames);
    }

    /**
     * 获取字段对应属性
     *
     * @return fieldValues
     */
    public ArrayList<Object> getFieldValues() {
        return (ArrayList<Object>) values();
    }

    /**
     * 获取单条记录
     *
     * @return field
     */
    public ArrayList<Entry<String, Object>> getField() {
        Set<Entry<String, Object>> field = entrySet();
        return new ArrayList<>(field);
    }

    /**
     * 获取表名
     *
     * @return tableName
     */
    public String getTableName() {
        return tableName;
    }

    /**
     * 修改表名
     *
     * @param tableName newTableName
     */
    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    /**
     * 构造
     */
    public Entity() {
        super();
    }

    /**
     * 构造
     *
     * @param tableName 表名
     */
    public Entity(String tableName) {
        super();
        this.tableName = tableName;
    }
}
