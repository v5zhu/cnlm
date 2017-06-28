package net.leebao.auth.entity;

/**
 * Created by LONG on 2017/6/28.
 */
public class MapFunctionPermission {
    private Long functionId;
    private Long permissionId;

    public Long getFunctionId() {
        return functionId;
    }

    public void setFunctionId(Long functionId) {
        this.functionId = functionId;
    }

    public Long getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Long permissionId) {
        this.permissionId = permissionId;
    }
}
