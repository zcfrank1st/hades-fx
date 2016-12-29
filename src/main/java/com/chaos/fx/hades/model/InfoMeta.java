package com.chaos.fx.hades.model;

import lombok.Data;

/**
 * Created by zcfrank1st on 28/12/2016.
 */
@Data
public class InfoMeta {
    private String privateKey;
    private String userName;
    private String projectName;
    private String projectPath;

    public InfoMeta () {}

    public InfoMeta (String userName, String privateKey, String projectName, String projectPath) {
        this.userName = userName;
        this.privateKey = privateKey;
        this.projectName = projectName;
        this.projectPath = projectPath;
    }
}
