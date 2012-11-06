package de.schaf.space.util;

import java.util.Comparator;

import de.schaf.space.Entity;

public class ZOrderComparator implements Comparator<Entity> {

    public ZOrderComparator() {
    }

    @Override
    public int compare(Entity g1, Entity g2) {
        return 1;
    }
    
}
