package fr.wildcodeschool.getdiamond;

import java.util.ArrayList;
import java.util.Date;

public class UserModel {

    private long id;
    private String name;
    private String password;
    private int money;
    private int diamond;
    private int ruby;
    private int opal;
    private int emerald;
    private ArrayList<UserModel> friend;
    private Date lastMining;
    private JewelryModel indent1;
    private JewelryModel indent2;
    private JewelryModel indent3;

    public UserModel() {
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getDiamond() {
        return diamond;
    }

    public void setDiamond(int diamond) {
        this.diamond = diamond;
    }

    public int getRuby() {
        return ruby;
    }

    public void setRuby(int ruby) {
        this.ruby = ruby;
    }

    public int getOpal() {
        return opal;
    }

    public void setOpal(int opal) {
        this.opal = opal;
    }

    public int getEmerald() {
        return emerald;
    }

    public void setEmerald(int emerald) {
        this.emerald = emerald;
    }

    public ArrayList<UserModel> getFriend() {
        return friend;
    }

    public void setFriend(ArrayList<UserModel> friend) {
        this.friend = friend;
    }

    public Date getLastMining() {
        return lastMining;
    }

    public void setLastMining(Date lastmining) {
        this.lastMining = lastMining;
    }
}
