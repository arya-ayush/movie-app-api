package com.om.movieapp.model;





import javax.persistence.*;

@Entity
@Table(name = "products", schema = "shorts")
public class PlayProducts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "coin_label", nullable = false)
    private String coinLabel;

    @Column(name = "coin_code", nullable = false, unique = true)
    private String coinCode;

    @Column(name = "coin_value", nullable = false)
    private int coinValue;

    // Getters and Setters
    public int getId() {
        return id;
    }

    public String getCoinLabel() {
        return coinLabel;
    }

    public void setCoinLabel(String coinLabel) {
        this.coinLabel = coinLabel;
    }

    public String getCoinCode() {
        return coinCode;
    }

    public void setCoinCode(String coinCode) {
        this.coinCode = coinCode;
    }
    public void setID(int id) {
        this.id =  id;
    }

    public int getCoinValue() {
        return coinValue;
    }

    public void setCoinValue(int coinValue) {
        this.coinValue = coinValue;
    }
}