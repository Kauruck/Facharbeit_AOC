package com.kauruck.Objects;

import java.awt.*;

public class Building {
    private final ItemStack[] inputs;
    private final ItemStack[] outputs;
    private final long processTime;
    private long elapsedTime = 0;
    private final Color color;

    private final Inventory inventory;

    public Building(ItemStack[] inputs, ItemStack[] outputs, long processTime, float capacity, Color color) {
        this.inputs = inputs;
        this.outputs = outputs;
        this.processTime = processTime;
        inventory = new Inventory(capacity);
        this.color = color;
    }

    public ItemStack[] getInputs() {
        return inputs;
    }

    public ItemStack[] getOutputs() {
        return outputs;
    }

    public float getProcessTime() {
        return processTime;
    }

    public void update(long deltaTime){
        elapsedTime += deltaTime;
        if(elapsedTime > processTime){
            if(hasInputs() && canOutput()){
                for(ItemStack current : inputs){
                    inventory.remove(current);
                }
                for(ItemStack current : outputs){
                    inventory.add(current);
                }
                elapsedTime = 0;
            }
        }
    }

    public boolean canOutput(){
        float out = 0;
        for(ItemStack current : outputs){
            out += current.calculateVolume();
        }
        return inventory.hasSpaceFor(out);
    }

    public boolean hasInputs(){
        for(ItemStack current : inputs){
            if(!inventory.containsAtLeast(current))
                return false;
        }
        return true;
    }

    public Color getColor() {
        return color;
    }
}
