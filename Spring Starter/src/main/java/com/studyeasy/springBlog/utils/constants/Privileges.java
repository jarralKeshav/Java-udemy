package com.studyeasy.springBlog.utils.constants;

import lombok.Getter;

@Getter
public enum Privileges {
    RESET_ANY_USER_PASSWORD(1,"RESET_ANY_USER_PASSWORD"),
    ACCESS_ADMIN_PANEL(2,"ACCESS_ADMIN_PANEL");

    private final long privilegeId;
    private final String privilegeName;

    Privileges(long privilegeId, String privilegeName) {
        this.privilegeId = privilegeId;
        this.privilegeName = privilegeName;
    }
}
