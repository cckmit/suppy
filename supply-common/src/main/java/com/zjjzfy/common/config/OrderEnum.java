package com.zjjzfy.common.config;

public enum  OrderEnum {
    orderOne("create_date desc"),
    orderTwo("salesVolume desc"),
    orderThree("salesVolume"),
    orderFour("PRICE"),
    orderFive("PRICE desc")
    ;

    private String order;

    OrderEnum(String order) {
        this.order = order;
    }

    public String getOrder() {
        return order;
    }
}
