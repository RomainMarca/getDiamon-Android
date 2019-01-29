package fr.wildcodeschool.getdiamond.models;

import java.util.Date;

public class ExchangeModel {

    private Long id;
    private Date createDate;
    private boolean accepted;
    private UserModel asker;
    private  int opalAsker;
    private  int emeraldAsker;
    private  int diamondAsker;
    private  int rubyAsker;
    private UserModel receiver;
    private  int opalReceiver;
    private  int emeraldReceiver;
    private  int diamondReceiver;
    private  int rubyReceiver;

    public ExchangeModel() {
    }

    public ExchangeModel(Date createDate, boolean accepted,
                         UserModel asker, int opalAsker, int emeraldAsker,
                         int diamondAsker, int rubyAsker, UserModel receiver,
                         int opalReceiver, int emeraldReceiver, int diamondReceiver,
                         int rubyReceiver) {
        this.createDate = createDate;
        this.accepted = accepted;
        this.asker = asker;
        this.opalAsker = opalAsker;
        this.emeraldAsker = emeraldAsker;
        this.diamondAsker = diamondAsker;
        this.rubyAsker = rubyAsker;
        this.receiver = receiver;
        this.opalReceiver = opalReceiver;
        this.emeraldReceiver = emeraldReceiver;
        this.diamondReceiver = diamondReceiver;
        this.rubyReceiver = rubyReceiver;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }

    public UserModel getAsker() {
        return asker;
    }

    public void setAsker(UserModel asker) {
        this.asker = asker;
    }

    public int getOpalAsker() {
        return opalAsker;
    }

    public void setOpalAsker(int opalAsker) {
        this.opalAsker = opalAsker;
    }

    public int getEmeraldAsker() {
        return emeraldAsker;
    }

    public void setEmeraldAsker(int emeraldAsker) {
        this.emeraldAsker = emeraldAsker;
    }

    public int getDiamondAsker() {
        return diamondAsker;
    }

    public void setDiamondAsker(int diamondAsker) {
        this.diamondAsker = diamondAsker;
    }

    public int getRubyAsker() {
        return rubyAsker;
    }

    public void setRubyAsker(int rubyAsker) {
        this.rubyAsker = rubyAsker;
    }

    public UserModel getReceiver() {
        return receiver;
    }

    public void setReceiver(UserModel receiver) {
        this.receiver = receiver;
    }

    public int getOpalReceiver() {
        return opalReceiver;
    }

    public void setOpalReceiver(int opalReceiver) {
        this.opalReceiver = opalReceiver;
    }

    public int getEmeraldReceiver() {
        return emeraldReceiver;
    }

    public void setEmeraldReceiver(int emeraldReceiver) {
        this.emeraldReceiver = emeraldReceiver;
    }

    public int getDiamondReceiver() {
        return diamondReceiver;
    }

    public void setDiamondReceiver(int diamondReceiver) {
        this.diamondReceiver = diamondReceiver;
    }

    public int getRubyReceiver() {
        return rubyReceiver;
    }

    public void setRubyReceiver(int rubyReceiver) {
        this.rubyReceiver = rubyReceiver;
    }
}