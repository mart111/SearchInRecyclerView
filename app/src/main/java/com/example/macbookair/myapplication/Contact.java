package com.example.macbookair.myapplication;

import android.media.Image;

/**
 * Created by macbookair on 1/15/18.
 */

public class Contact {

    private String name;
    private String socialNetwork;

    public Contact(String name,String social) {
        this.name = name;
        this.socialNetwork=social;
    }

    public String getName() {
        return name;
    }

    public String getSocialNetwork() {
        return socialNetwork;
    }
}
