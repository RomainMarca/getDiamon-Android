package fr.wildcodeschool.getdiamond.models;

import java.util.ArrayList;

public class JewelryModel {

    private long id;
    private String name;
    private int ruby;
    private int emerald;
    private int diamond;
    private int opal;
    private int gain;
    private int resale;
    private boolean built = false;

    public JewelryModel() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRuby() {
        return ruby;
    }

    public void setRuby(int ruby) {
        this.ruby = ruby;
    }

    public int getEmerald() {
        return emerald;
    }

    public void setEmerald(int emerald) {
        this.emerald = emerald;
    }

    public int getDiamond() {
        return diamond;
    }

    public void setDiamond(int diamond) {
        this.diamond = diamond;
    }

    public int getOpal() {
        return opal;
    }

    public void setOpal(int opal) {
        this.opal = opal;
    }

    public int getGain() {
        return gain;
    }

    public void setGain(int gain) {
        this.gain = gain;
    }

    public int getResale() {
        return resale;
    }

    public void setResale(int resale) {
        this.resale = resale;
    }

    public boolean isBuilt() {
        return built;
    }

    public void setBuilt(boolean built) {
        this.built = built;
    }
}
