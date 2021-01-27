package com.lala.sweetsoup.exception;

/**
 * @ProjectName: SweetSoup
 * @Package: com.lala.sweetsoup.exception
 * @ClassName: BaseException
 * @Description: 项目中所有异常类都继承于本类
 * @Author: Young
 * @CreateDate: 2021/1/24 21:50
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/1/24 21:50
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class BaseException extends Exception {

    public BaseException() {
        super();
    }

    public BaseException(String message) {
        super(message);
    }

    public String getCustomData() {
        return null;
    }

    public void outputCustomData() {
    }
}
