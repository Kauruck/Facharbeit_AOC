package com.kauruck.Objects;

public class Building {
    private final ItemStack[] inputs;
    private final ItemStack[] outputs;
    private final float processTime;
    private float elapsedTime = 0f;

    private Inventory inventory;

    public Building(ItemStack[] inputs, ItemStack[] outputs, float processTime, float capacity) {
        this.inputs = inputs;
        this.outputs = outputs;
        this.processTime = processTime;
        inventory = new Inventory(capacity);
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

    public void update(float deltaTime){
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
}
