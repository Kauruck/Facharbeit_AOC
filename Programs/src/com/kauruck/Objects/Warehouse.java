package com.kauruck.Objects;

import java.awt.*;

public class Warehouse extends Building{
    public Warehouse(float capacity, Color color) {
        super(new ItemStack[0], new ItemStack[0], 0, capacity, color);
    }

    public Warehouse(Warehouse org) {
        super(new ItemStack[0], new ItemStack[0],0,org.getInventory().getCapacity(), org.getColor());
    }

    @Override
    public void update(long deltaTime) {

    }
}
