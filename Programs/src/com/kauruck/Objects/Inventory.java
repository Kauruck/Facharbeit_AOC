package com.kauruck.Objects;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class Inventory {
    private final float capacity;
    private List<ItemStack> inventory = new ArrayList<>();

    public Inventory(float capacity) {
        this.capacity = capacity;
    }

    public boolean hasSpaceFor(@NotNull ItemStack itemStack){
        return itemStack.calculateVolume() <= (capacity - calculateUsedVolume());
    }

    public boolean hasSpaceFor(float volume){
        return volume <= (capacity - calculateUsedVolume());
    }

    public float calculateUsedVolume(){
        float out = 0;
        for(ItemStack current : inventory){
            out += current.calculateVolume();
        }
        return out;
    }

    public ItemStack add(ItemStack what){
        if(calculateUsedVolume() == capacity) return what;
        if(hasSpaceFor(what)){
            if(inventory.contains(what)){
                getItemStackFromItem(what.getItem()).add(what);
            }
            else {
                inventory.add(what);
            }
            return null;
        }else{
            float freeSpace = capacity - calculateUsedVolume();
            int toAdd = (int)Math.floor(freeSpace/what.getItem().getVolume());
            int res = what.getAmount() - toAdd;
            if(inventory.contains(what)){
                getItemStackFromItem(what.getItem()).add(new ItemStack(toAdd, what.getItem()));
            }
            else {
                inventory.add(new ItemStack(toAdd, what.getItem()));
            }
            return new ItemStack(res, what.getItem());
        }

    }

    public ItemStack remove(ItemStack target){
        if(containsAtLeast(target)){
            getItemStackFromItem(target.getItem()).remove(target);
            return target;
        }
        else if(containsItem(target.getItem())){
            int amount = getAmountOf(target.getItem());
            return this.remove(new ItemStack(amount, target.getItem()));
        }
        return null;
    }

    public int getSize(){
        return inventory.size();
    }

    public ItemStack itemStackAtIndex(int index){
        return inventory.get(index);
    }

    public int indexOf(ItemStack stack){
        for(int i = 0; i < inventory.size(); i++){
            if(inventory.get(i).equals(stack))
                return i;
        }
        return -1;
    }

    public int indexOf(Item item){
        for(int i = 0; i < inventory.size(); i++){
            if(inventory.get(i).equals(item))
                return i;
        }
        return -1;
    }

    public ItemStack getItemStackFromItem(Item item){
        for (ItemStack itemStack : inventory) {
            if (itemStack.equals(item))
                return itemStack;
        }
        return null;
    }

    public boolean containsItem(Item item){
        for (ItemStack itemStack : inventory) {
            if (itemStack.equals(item))
                return true;
        }
        return false;
    }

    public boolean containsAtLeast(ItemStack item){
        for (ItemStack itemStack : inventory) {
            if (itemStack.equals(item) && itemStack.getAmount() >= item.getAmount())
                return true;
        }
        return false;
    }

    public int getAmountOf(Item item){
        return getItemStackFromItem(item).getAmount();
    }
}
