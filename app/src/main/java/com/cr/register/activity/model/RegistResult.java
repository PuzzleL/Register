package com.cr.register.activity.model;

/**
 * Created by Blacktea on 2016/5/17.
 */
public class RegistResult {
    private boolean ok;
    private Reason reason;

    public boolean getOk() {
        return ok;
    }

    public Reason getReason() {
        return reason;
    }
}

class Reason {
    private String desc;
    private int err;

    public String getDesc() {
        return desc;
    }

    public int getErr() {
        return err;
    }
}
