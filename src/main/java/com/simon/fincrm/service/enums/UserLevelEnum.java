/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.simon.fincrm.service.enums;

/**
 * @author jinshihao
 * @version $Id: UserLevelEnum.java, v 0.1 2016-09-13 17:14 jinshihao Exp $$
 */
public enum UserLevelEnum {

    ROLE_SALESMAN(1, "销售人员"),

    ROLE_MANAGER(9,"经理");

    /** 等级id*/
    private int leveId;

    /** 等级名称*/
    private String levelName;

    UserLevelEnum(Integer levelId, String levelName){
        this.leveId = levelId;
        this.levelName = levelName;
    }

    public static UserLevelEnum getByLevelId(int levelId) {

        // 按照code查找枚举
        for (UserLevelEnum eachValue : UserLevelEnum.values()) {
            if (levelId == eachValue.getLeveId()) {
                return eachValue;
            }
        }

        // 如果找不到，则返回null
        return null;
    }

    /**
     * Getter method for property <tt>leveId</tt>.
     *
     * @return property value of leveId
     */
    public int getLeveId() {
        return leveId;
    }

    /**
     * Getter method for property <tt>levelName</tt>.
     *
     * @return property value of levelName
     */
    public String getLevelName() {
        return levelName;
    }
}
