package com.kauruck;

import com.kauruck.Objects.Building;
import com.kauruck.Objects.Item;
import com.kauruck.Objects.ItemStack;
import com.kauruck.Objects.Warehouse;

import java.awt.*;

public class Buildings {
    public static final Building LUMBERJACK = new Building(new ItemStack[0], new ItemStack[]{new ItemStack(1,Items.LOG)}, (long) 4e+9, 10, Color.GREEN);
    public static final Building SAWMILL = new Building(new ItemStack[]{new ItemStack(1, Items.LOG)}, new ItemStack[]{new ItemStack(2, Items.PLANK)}, (long) 2e+9, 20, Color.BLACK);
    public static final Warehouse WAREHOUSE = new Warehouse(50f, Color.ORANGE);
}
