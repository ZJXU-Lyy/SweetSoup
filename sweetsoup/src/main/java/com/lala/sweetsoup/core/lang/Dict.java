package com.lala.sweetsoup.core.lang;

import com.lala.sweetsoup.exception.BaseException;

import java.util.LinkedHashMap;

/**
 * @ProjectName: SweetSoup
 * @Package: com.lala.sweetsoup.core.lang
 * @ClassName: Dict
 * @Description: 提供类似python中dict(字典)数据结构的支持
 * @Author: Young
 * @CreateDate: 2021/1/25 13:40
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/1/25 13:40
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class Dict extends LinkedHashMap<String, Object> {

    /**
     * 新建Dict对象
     *
     * @return newDict
     */
    public static Dict create() {
        return new Dict();
    }

    /**
     * 对实现了IParseDict接口的Bean对象提供Dict构造方法
     *
     * @param bean 提供数据的bean对象
     * @param <T>  IParseDict子类
     * @return 与bean对象数据对应的dict对象
     */
    public static <T extends IParseDict> Dict parseBean(T bean) {
        return bean.beanToDict(create());
    }

    /**
     * 使用参数中的dict对象对bean赋值
     *
     * @param bean 被赋值对象需要实现IParseDict接口
     * @param dict 提供源数据
     * @param <T>  IParseDict子类
     */
    public static <T extends IParseDict> void writeBean(T bean, Dict dict) {
        try {
            if (bean != null && dict != null) {
                bean.dictToBean(dict);
            } else {
                throw new BaseException("param must not null!");
            }
        } catch (BaseException e) {
            e.printStackTrace();
        }
    }

    /**
     * javaBean 与 Dict 的转换接口
     */
    public interface IParseDict {
        /**
         * 从javaBean转换为Dict
         *
         * @param dict 非null dict对象
         * @return 修改数据后的dict对象
         */
        Dict beanToDict(Dict dict);

        /**
         * 从Dict转换为javaBean
         *
         * @param dict 需要对Bean对象写入的Dict数据
         */
        void dictToBean(Dict dict);
    }
}
