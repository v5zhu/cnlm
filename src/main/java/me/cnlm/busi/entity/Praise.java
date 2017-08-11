package me.cnlm.busi.entity;

/**
 * Created by zhuxl@paxsz.com on 2017/8/11.
 */
public class Praise {
    private Long id;
    private Integer praise;
    private String ip;
    private String username;
    private String bless;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPraise() {
        return praise;
    }

    public void setPraise(Integer praise) {
        this.praise = praise;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getBless() {
        return bless;
    }

    public void setBless(String bless) {
        this.bless = bless;
    }
}
