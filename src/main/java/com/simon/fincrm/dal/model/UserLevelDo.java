package com.simon.fincrm.dal.model;

public class UserLevelDo {
    private Integer id;

    private Integer userId;

    private Integer levelId;

    private String levelName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getLevelId() {
        return levelId;
    }

    public void setLevelId(Integer levelId) {
        this.levelId = levelId;
    }

    /**
     * Getter method for property <tt>levelName</tt>.
     *
     * @return property value of levelName
     */
    public String getLevelName() {
        return levelName;
    }

    /**
     * Setter method for property <tt>levelName</tt>.
     *
     * @param levelName value to be assigned to property levelName
     */
    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }
}