package com.way.viewmodel;

public class ReturnObject{

    private boolean success;
    private boolean session;
    private boolean logout;

    public ReturnObject(boolean success, boolean session, boolean logout) {
        this.success = success;
        this.session = session;
        this.logout = logout;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public boolean isSession() {
        return session;
    }

    public void setSession(boolean session) {
        this.session = session;
    }

    public boolean isLogout() {
        return logout;
    }

    public void setLogout(boolean logout) {
        this.logout = logout;
    }
}