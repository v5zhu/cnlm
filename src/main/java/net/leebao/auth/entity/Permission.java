package net.leebao.auth.entity;

/**
 * Created by LONG on 2017/6/27.
 */
public class Permission {
    private Long id;
    private String name;
    private String desc;
    private String symbol;
    private Permission parent;
//    http://blog.csdn.net/chexlong/article/details/37697555/
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Permission getParent() {
        return parent;
    }

    public void setParent(Permission parent) {
        this.parent = parent;
    }
}
