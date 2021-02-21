package com.kauruck.Objects;

import com.kauruck.Graph.Edge;
import com.kauruck.Graph.Node;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class Warehouse extends Building{
    private Node<? extends Warehouse> holder = null;

    public Warehouse(float capacity, Color color) {
        super(new ItemStack[0], new ItemStack[0], 0, capacity, color);
    }

    public Warehouse(Warehouse org) {
        super(new ItemStack[0], new ItemStack[0],0,org.getInventory().getCapacity(), org.getColor());
        holder = null;
    }

    public void setHolder(Node<? extends Warehouse> holder){
        this.holder = holder;
    }

    @Override
    public void update(long deltaTime) {
        for(int i = 0; i < holder.getEdges().size(); i++) {
            Edge currentEdge = holder.getEdges().get(i);
            if (currentEdge.getA() == holder) {
                Node<?> target = currentEdge.getB();
                int nEdges = target.getEdges().size() > 0 ? target.getEdges().size() : 1;
                for (ItemStack current : target.getContent().getOutputs()) {
                    ItemStack toGet = new ItemStack(current.getAmount()/nEdges, current.getItem());
                    if (target.getContent().getInventory().containsAtLeast(toGet)) {
                        this.inventory.add(target.getContent().getInventory().remove(toGet));
                    }
                }
            }else{
                Node<?> target = currentEdge.getA();
                int nEdges = target.getEdges().size() > 0 ? target.getEdges().size() : 1;
                for (ItemStack current : target.getContent().getOutputs()) {
                    ItemStack toGet = new ItemStack(current.getAmount()/nEdges, current.getItem());
                    if (target.getContent().getInventory().containsAtLeast(toGet)) {
                        this.inventory.add(target.getContent().getInventory().remove(toGet));
                    }
                }
            }
        }
        for(int i = 0; i < holder.getEdges().size(); i++) {
            Edge currentEdge = holder.getEdges().get(i);
            if (currentEdge.getA() == holder) {
                Node<?> target = currentEdge.getB();
                int nEdges = target.getEdges().size() > 0 ? target.getEdges().size() : 1;
                for (ItemStack current : target.getContent().getInputs()) {
                    ItemStack toDeliver = new ItemStack(current.getAmount()/nEdges, current.getItem());
                    if (this.getInventory().containsAtLeast(toDeliver)) {
                        target.getContent().inventory.add(this.getInventory().remove(toDeliver));
                    }
                }
            }else{
                Node<?> target = currentEdge.getA();
                int nEdges = target.getEdges().size() > 0 ? target.getEdges().size() : 1;
                for (ItemStack current : target.getContent().getInputs()) {
                    ItemStack toDeliver = new ItemStack(current.getAmount()/nEdges, current.getItem());
                    if (this.getInventory().containsAtLeast(toDeliver)) {
                        target.getContent().inventory.add(this.getInventory().remove(toDeliver));
                    }
                }
            }
        }
    }

    public Map<Item, Float> balance(){
        HashMap<Item, Float> out = new HashMap<>();
        for(Edge currentEdge : holder.getEdges()){
            if(currentEdge.getA() == holder){
                Node<?> target = currentEdge.getB();
                int nEdges = target.getEdges().size();
                for(ItemStack current : target.getContent().getInputs()){
                    float n = (float) (current.getAmount()/(nEdges*1f)*(3e+10/target.getContent().getProcessTime())) * -1f;
                    if(out.containsKey(current.getItem())){
                        float oldN = out.get(current.getItem());
                        out.replace(current.getItem(), oldN + n);
                    }else{
                        out.put(current.getItem(), n);
                    }
                }

                for(ItemStack current : target.getContent().getOutputs()){
                    float n = (float) (current.getAmount()/(nEdges*1f)*(3e+10/target.getContent().getProcessTime()));
                    if(out.containsKey(current.getItem())){
                        float oldN = out.get(current.getItem());
                        out.replace(current.getItem(), oldN + n);
                    }else{
                        out.put(current.getItem(), n);
                    }
                }
            }else{
                Node<?> target = currentEdge.getA();
                int nEdges = target.getEdges().size();
                for(ItemStack current : target.getContent().getInputs()){
                    float n = (float) (current.getAmount()/(nEdges*1f)*(3e+10/target.getContent().getProcessTime())) * -1f;
                    if(out.containsKey(current.getItem())){
                        float oldN = out.get(current.getItem());
                        out.replace(current.getItem(), oldN + n);
                    }else{
                        out.put(current.getItem(), n);
                    }
                }

                for(ItemStack current : target.getContent().getOutputs()){
                    float n = (float) (current.getAmount()/(nEdges*1f)*(3e+10/target.getContent().getProcessTime()));
                    if(out.containsKey(current.getItem())){
                        float oldN = out.get(current.getItem());
                        out.replace(current.getItem(), oldN + n);
                    }else{
                        out.put(current.getItem(), n);
                    }
                }
            }
        }

        return out;
    }
}
