package com.example.woops.hilinux.entity;


public class CarLogo {
    private String nameSpell;
    private String name;

    public CarLogo(String nameSpell, String name) {
        this.nameSpell = nameSpell;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameSpell() {
        return nameSpell;
    }

    public void setNameSpell(String nameSpell) {
        this.nameSpell = nameSpell;
    }
}
