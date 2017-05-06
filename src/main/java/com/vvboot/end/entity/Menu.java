package com.vvboot.end.entity;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by LONG on 2017/4/18.
 */
public class Menu {
    private Long menuId;
    @NotNull(message = "请指明菜单名称")
    private String name;
    private String className;
    private String uisref;
    private String attrLink;
    @NotNull(message = "请指明所属模块")
    private Long moduleId;
    private String moduleName;
    @NotNull(message = "请指明排序序号")
    private Integer sort;
    private Integer progress = 0;
    private Integer locked = 0;
    private Date createTime;
    private Date updateTime;

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getModuleId() {
        return moduleId;
    }

    public void setModuleId(Long moduleId) {
        this.moduleId = moduleId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getUisref() {
        return uisref;
    }

    public void setUisref(String uisref) {
        this.uisref = uisref;
    }

    public String getAttrLink() {
        return attrLink;
    }

    public void setAttrLink(String attrLink) {
        this.attrLink = attrLink;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getProgress() {
        return progress;
    }

    public void setProgress(Integer progress) {
        this.progress = progress;
    }

    public Integer getLocked() {
        return locked;
    }

    public void setLocked(Integer locked) {
        this.locked = locked;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }
}
