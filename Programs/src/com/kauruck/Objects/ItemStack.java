package com.kauruck.Objects;

import org.jetbrains.annotations.Contract;

import java.util.Objects;

public class ItemStack {
    private int amount;
    private final Item item;

    public ItemStack(int amount, Item item) {
        this.amount = amount;
        this.item = item;
    }

    public ItemStack(ItemStack org) {
        this.amount = org.getAmount();
        this.item = org.getItem();
    }

    public void add(ItemStack b){
        if(!this.equals(b)) return;
        amount += b.getAmount();
    }

    public void remove(ItemStack b){
        if(!this.equals(b)) return;
        amount -= b.getAmount();
    }

    @Override
    @Contract(value = "null -> false", pure = false)
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if(o.getClass() == getClass()) {
            ItemStack itemStack = (ItemStack) o;
            return item.equals(itemStack.item);
        }else if(o.getClass() == Item.class){
            Item item = (Item) o;
            return this.item.equals(item);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(item);
    }

    public int getAmount() {
        return amount;
    }

    public Item getItem() {
        return item;
    }

    public float calculateVolume(){
        return amount * item.getVolume();
    }

    public String toString(){
        return amount + " " + item;
    }
}
