package com.recen.learn;

public enum Color {
        RED("红色",1),GREEN("绿色",2),BLANK("白色", 3),YELLO("黄色",4);
        // 成员变量  
        private String name;
        private int index;
        // 构造方法  
        private Color(String name, int index){
        this.name=name;
        this.index=index;
        }
}