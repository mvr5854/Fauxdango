package edu.psu.fauxdango.Model;

public class Advertisement extends DataStoreObj {
    private int id;
    private String text;

    public Advertisement(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
