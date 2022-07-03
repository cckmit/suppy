package com.zjjzfy.exchange.pojo;

public class Param {

    private String cs;
    private String jym;

    public String getCs() {
        return cs;
    }

    public void setCs(String cs) {
        this.cs = cs;
    }

    public String getJym() {
        return jym;
    }

    public void setJym(String jym) {
        this.jym = jym;
    }

    @Override
    public String toString() {
        return "Param{" +
                "cs='" + cs + '\'' +
                ", jym='" + jym + '\'' +
                '}';
    }
}
