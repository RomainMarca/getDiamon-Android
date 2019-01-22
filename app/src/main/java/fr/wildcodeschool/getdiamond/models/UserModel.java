package fr.wildcodeschool.getdiamond.models;

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
    private Date lastMining;
    private JewelryModel indent1;
    private JewelryModel indent2;
    private JewelryModel indent3;
    private int totalExchange;
    private int totalBuilt;

    public UserModel() {}

    public UserModel(long id, String name, String password,
                     int money, int diamond, int ruby, int opal,
                     int emerald, Date lastMining, JewelryModel indent1,
                     JewelryModel indent2, JewelryModel indent3,
                     int totalExchange, int totalBuilt) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.money = money;
        this.diamond = diamond;
        this.ruby = ruby;
        this.opal = opal;
        this.emerald = emerald;
        this.lastMining = lastMining;
        this.indent1 = indent1;
        this.indent2 = indent2;
        this.indent3 = indent3;
        this.totalExchange = totalExchange;
        this.totalBuilt = totalBuilt;
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

    public Date getLastMining() {
        return lastMining;
    }

    public void setLastMining(Date lastMining) {
        this.lastMining = lastMining;
    }

    public JewelryModel getIndent1() {
        return indent1;
    }

    public void setIndent1(JewelryModel indent1) {
        this.indent1 = indent1;
    }

    public JewelryModel getIndent2() {
        return indent2;
    }

    public void setIndent2(JewelryModel indent2) {
        this.indent2 = indent2;
    }

    public JewelryModel getIndent3() {
        return indent3;
    }

    public void setIndent3(JewelryModel indent3) {
        this.indent3 = indent3;
    }

    public int getTotalExchange() {
        return totalExchange;
    }

    public void setTotalExchange(int totalExchange) {
        this.totalExchange = totalExchange;
    }

    public int getTotalBuilt() {
        return totalBuilt;
    }

    public void setTotalBuilt(int totalBuilt) {
        this.totalBuilt = totalBuilt;
    }
}
