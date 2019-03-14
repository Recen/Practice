package com.recen.learn;

public enum Action {
    NEW_ORDER("新订单",1),GREEN("绿色",2),BLANK("白色", 3),YELLO("黄色",4);
    private String actionName;
    private int actionCode;
    private Action(String actionName, int actionCode) {
        this.actionName = actionName;
        this.actionCode = actionCode;
    }
}
