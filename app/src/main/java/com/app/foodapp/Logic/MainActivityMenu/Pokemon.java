package com.app.foodapp.Logic.MainActivityMenu;

public class Pokemon {
    private String Name;
    private int IconId;
    private int Id;
    private int price;

    public Pokemon(String name, int iconId, int id, int price) {
        Name = name;
        IconId = iconId;
        Id = id;
        this.price = price;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getIconId() {
        return IconId;
    }

    public void setIconId(int iconId) {
        IconId = iconId;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}