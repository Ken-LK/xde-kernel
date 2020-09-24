package com.xde.kernel.constant;

/**
 * @author <a href="mailto:liukailk.ken@gmail.com"> Ken </a>
 * @date 2020/9/23 9:45 上午
 **/
public interface MongoConstant {

    /**
     * 默认操作失败返回值（修改、删除和统计功能中作为操作失败的返回记录值）
     */
    long ERRORCODE = -1;


    /**
     * 本地bean包部分路径
     */
    String PACKAGEURL = "xde";

    /**
     * java 字符串和基本类型 的包部分路径
     */
    String BASETYPE = "class java.lang";

    /**
     * java Date对象的包路径
     */
    String DATETYPE = "class java.util.Date";

    /**
     * java Map对象的包路径
     */
    String MAPTYPE = "java.util.Map";

    /**
     * 包路径中的 关键词
     */
    String CLASS = "class";

    /**
     * BigDecimal 包路径中的 关键词
     */
    String BIGDECIMALTYPE = "java.math.BigDecimal";

    /**
     * 序列化字段名称
     */
    String SERIALFILED = "serialVersionUID";

    /**
     * mongodb查询条件常量
     * <p>
     * /** and
     */
    String AND = "$and";
    /**
     * or
     */
    String OR = "$or";
    /**
     * ==
     */
    String EQ = "$eq";
    /*** != */
    String NE = "$ne";
    /*** > */
    String GT = "$gt";
    /*** >= */
    String GTE = "$gte";
    /*** < */
    String LT = "$lt";
    /*** <= */
    String LTE = "$lte";
    /*** in */
    String IN = "$in";
    /*** not in */
    String NIN = "$nin";

    String SORT = "$sort";
    String SET = "$set";

    String INC = "$inc";
    /*** -1 倒序 */
    int DESC = -1;
    /*** 1 升序 */
    int ASC = 1;
}
