package com.studyeasy.springBlog.utils.constants;

public enum Priviledges {
    RESET_ANY_USER_PASSWORD(1,"RESET_ANY_USER_PASSWORD"),
    ACCESS_ADMIN_PANEL(2,"ACCESS_ADMIN_PANEL");

    private final long priviledgeId;
    private final String priviledgeName;
    Priviledges(long priviledgeId, String priviledgeName) {
        this.priviledgeId = priviledgeId;
        this.priviledgeName = priviledgeName;
    }
    public long getPriviledgeId() {
        return priviledgeId;
    }
    public String getPriviledgeName() {
        return priviledgeName;
    }
}
