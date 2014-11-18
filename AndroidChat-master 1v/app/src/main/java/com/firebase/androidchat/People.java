package com.firebase.androidchat;

import java.io.Serializable;
import java.util.Random;
/**
 * Created by igormacedo on 11/16/14.
 */
public class People implements Serializable{

    private int me, other;
    private boolean classification;

    public People (boolean c) {
        Random r = new Random();
        this.me = r.nextInt(100000);
        this.other = 0;
        this.classification = c;
    }

    public int whoAmI() {
        return this.me;
    }

    public int whoAreYou() {
        return this.other;
    }

    // Helped: false
    // Helper: true
    public boolean getClassification() {
        return this.classification;
    }
}
