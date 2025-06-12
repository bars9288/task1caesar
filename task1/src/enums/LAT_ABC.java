package enums;

import java.util.*;

public enum LAT_ABC {
    LATINIC("abcdefghijklmnopqrstuvwxyz".chars().toArray()),
    LATINIC_UPPER("abcdefghijklmnopqrstuvwxyz".toUpperCase().chars().toArray());

    int[] abc;

    LAT_ABC(int[] abcLine) {
        this.abc = abcLine;
    }

    public int[] get() {
        return abc;
    }
}

