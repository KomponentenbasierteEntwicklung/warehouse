package com.example.warehouse.helper;

public class ProductAsString {
    String gameName;
    String[] dlcs = null;

    public ProductAsString(String gameName, String[] dlcs) {
        this.gameName = gameName;
        this.dlcs = dlcs;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public String[] getDlcs() {
        return dlcs;
    }

    public void setDlcs(String[] dlcs) {
        this.dlcs = dlcs;
    }
}
