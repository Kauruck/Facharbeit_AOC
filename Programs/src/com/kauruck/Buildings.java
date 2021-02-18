package com.kauruck;

import com.kauruck.Objects.Building;
import com.kauruck.Objects.ItemStack;

import java.awt.*;

public class Buildings {
    public static final Building LUMBERJACK = new Building(new ItemStack[0], new ItemStack[]{new ItemStack(1,Items.LOG)}, (long) 6e+10, 10, Color.GREEN);
}
