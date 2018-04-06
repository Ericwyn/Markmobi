package com.ericwyn.markmobi.tool;

/**
 * Created by Ericwyn on 18-4-6.
 */
public class BuildInfo {
    private boolean buildSuccess;
    private String msg;
    private String path;

    public BuildInfo() {
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public boolean isBuildSuccess() {
        return buildSuccess;
    }

    public void setBuildSuccess(boolean buildSuccess) {
        this.buildSuccess = buildSuccess;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
