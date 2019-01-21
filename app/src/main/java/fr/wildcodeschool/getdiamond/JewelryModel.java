package fr.wildcodeschool.getdiamond;

import java.util.ArrayList;

public class JewelryModel {

    private long id;
    private String Name;
    private String emplacement1;
    private String emplacement2;
    private String emplacement3;
    private String emplacement4;
    private String emplacement5;
    private String emplacement6;
    private int gain;
    private int resale;
    private boolean build = false;

    public JewelryModel() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmplacement1() {
        return emplacement1;
    }

    public void setEmplacement1(String emplacement1) {
        this.emplacement1 = emplacement1;
    }

    public String getEmplacement2() {
        return emplacement2;
    }

    public void setEmplacement2(String emplacement2) {
        this.emplacement2 = emplacement2;
    }

    public String getEmplacement3() {
        return emplacement3;
    }

    public void setEmplacement3(String emplacement3) {
        this.emplacement3 = emplacement3;
    }

    public String getEmplacement4() {
        return emplacement4;
    }

    public void setEmplacement4(String emplacement4) {
        this.emplacement4 = emplacement4;
    }

    public String getEmplacement5() {
        return emplacement5;
    }

    public void setEmplacement5(String emplacement5) {
        this.emplacement5 = emplacement5;
    }

    public String getEmplacement6() {
        return emplacement6;
    }

    public void setEmplacement6(String emplacement6) {
        this.emplacement6 = emplacement6;
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

    public boolean isBuild() {
        return build;
    }

    public void setBuild(boolean build) {
        this.build = build;
    }
}
