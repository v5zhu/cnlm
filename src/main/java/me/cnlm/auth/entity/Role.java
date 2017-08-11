package me.cnlm.auth.entity;

import me.cnlm.auth.enums.RoleType;

import java.util.Date;

/**
 * Created by LONG on 2017/6/27.
 */
public class Role {
    private Long id;
    private Site site;
    private RoleType type=RoleType.NORMAL;
    private String name;
    private Organization organization;
    private Long createUserid;
    private String createUsername;
    private Date createTime;
    private Long modifiyUserid;
    private String modifiyUsername;
    private Date modifyTime;
    private String reserve1;
    private String reserve2;
    private String reserve3;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RoleType getType() {
        return type;
    }

    public void setType(RoleType type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getReserve1() {
        return reserve1;
    }

    public void setReserve1(String reserve1) {
        this.reserve1 = reserve1;
    }

    public String getReserve2() {
        return reserve2;
    }

    public void setReserve2(String reserve2) {
        this.reserve2 = reserve2;
    }

    public String getReserve3() {
        return reserve3;
    }

    public void setReserve3(String reserve3) {
        this.reserve3 = reserve3;
    }

    public Long getCreateUserid() {
        return createUserid;
    }

    public void setCreateUserid(Long createUserid) {
        this.createUserid = createUserid;
    }

    public String getCreateUsername() {
        return createUsername;
    }

    public void setCreateUsername(String createUsername) {
        this.createUsername = createUsername;
    }

    public Long getModifiyUserid() {
        return modifiyUserid;
    }

    public void setModifiyUserid(Long modifiyUserid) {
        this.modifiyUserid = modifiyUserid;
    }

    public String getModifiyUsername() {
        return modifiyUsername;
    }

    public void setModifiyUsername(String modifiyUsername) {
        this.modifiyUsername = modifiyUsername;
    }
}
