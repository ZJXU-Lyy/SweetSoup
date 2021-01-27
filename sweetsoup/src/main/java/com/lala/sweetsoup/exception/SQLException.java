package com.lala.sweetsoup.exception;

/**
 * @ProjectName: SweetSoup
 * @Package: com.lala.sweetsoup.exception
 * @ClassName: SQLException
 * @Description: 数据库操作错误
 * @Author: Young
 * @CreateDate: 2021/1/24 22:16
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/1/24 22:16
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class SQLException extends BaseException {

    private String errorTable;

    private String errorOperate;

    public SQLException() {
        super();
    }

    public SQLException(String message) {
        super(message);
    }

    public SQLException(String message, String errorTable, String errorOperate) {
        super(message);
        this.errorTable = errorTable;
        this.errorOperate = errorOperate;
    }

    public void setErrorTable(String errorTable) {
        this.errorTable = errorTable;
    }

    public void setErrorOperate(String errorOperate) {
        this.errorOperate = errorOperate;
    }

    @Override
    public String getCustomData() {
        if (errorTable == null)
            errorTable = "errorTable";
        if (errorOperate == null)
            errorOperate = "errorOperate";
        return errorOperate + "error from" + errorTable;
    }

    @Override
    public void outputCustomData() {
        System.err.println(getCustomData());
    }

}
