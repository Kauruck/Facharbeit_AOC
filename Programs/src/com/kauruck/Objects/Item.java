package com.kauruck.Objects;

import java.util.Objects;

public class Item {
    private final float volume;
    private final String name;

    public Item(float volume, String name) {
        this.volume = volume;
        this.name = name;
    }

    public float getVolume() {
        return volume;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Item)) return false;
        Item item = (Item) o;
        return Float.compare(item.volume, volume) == 0 && name.equals(item.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(volume, name);
    }
}
