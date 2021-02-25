package com.kauruck.AS;

import com.kauruck.Graph.Edge;
import com.kauruck.Graph.Node;
import com.kauruck.Objects.Item;
import com.kauruck.Objects.ItemStack;
import com.kauruck.Objects.Warehouse;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Ant {
    public static final float alpha = 1f;
    public static final float beta = 5f;
    public static final float theta = 5;
    public static final int takeItemMax = 10;
    public static final float rLeaveItems = 0.3f;

    private Node<?> currentPosition;
    private List<Node<?>> visited = new ArrayList<>();

    private ItemStack currentItem = null;

    public boolean visited(Node<?> node){
        return visited.contains(node);
    }

    public Ant(Node<?> startPos, Item transporting){
        this.currentPosition = startPos;
        Random r = new Random();
        int n = r.nextInt(takeItemMax + 1);
    }

    private float calcEfficiency(Edge edge){
        Node<?> to = edge.getA() == currentPosition ? edge.getB() : edge.getA();
        float d = 1;
        if(to.getContent() instanceof Warehouse && ((Warehouse)to.getContent()).balance().containsKey(currentItem.getItem()))
            d = (float)Math.sqrt((edge.getA().getX() - edge.getB().getX()) * (edge.getA().getX() - edge.getB().getX()) + (edge.getA().getY() - edge.getB().getY()) * (edge.getA().getY() - edge.getB().getY())) + theta * ((Warehouse)to.getContent()).balance().get(currentItem.getItem());
        else
            d = (float)Math.sqrt((edge.getA().getX() - edge.getB().getX()) * (edge.getA().getX() - edge.getB().getX()) + (edge.getA().getY() - edge.getB().getY()) * (edge.getA().getY() - edge.getB().getY()));
        return 1f/d;
    }

    private float otherEdges(Edge notToInclude){
        float out = 0f;
        for(Edge current : currentPosition.getEdges()){
            if(current != notToInclude){
                out += (current.getAttractiveness()*alpha)*(calcEfficiency(current)*beta);
            }
        }

        return out;
    }

    private float probability(Edge edge){
        return ((edge.getAttractiveness()*alpha)*(calcEfficiency(edge) * beta))/(otherEdges(edge));
    }

    public Edge findWay(){
        float[] props = new float[currentPosition.getEdges().size()];
        int i = 0;
        for(Edge current : currentPosition.getEdges()){
            if(currentPosition == current.getA())
                props[i] = visited(current.getB())? 0 : probability(current);
            else if(currentPosition == current.getB())
                props[i] = visited(current.getA())? 0 : probability(current);
            i++;
        }

        //https://stackoverflow.com/questions/40431966/what-is-the-best-way-to-generate-a-random-float-value-included-into-a-specified[20.02.2021]
        Random r = new Random();
        float guard = r.nextFloat();
        float total = 0f;
        for(int j = 0; j < props.length; j++){
            total += props[j];
            if(total >= guard)
                return currentPosition.getEdges().get(j);
        }

        return null;
    }

    public void move(){
        Random r = new Random();
        float leaveItemP = r.nextFloat();
        if(leaveItemP <= rLeaveItems && currentPosition.getContent() instanceof Warehouse && ((Warehouse)currentPosition.getContent()).getInventory().hasSpaceFor(currentItem)){
            ((Warehouse)currentPosition.getContent()).getInventory().add(currentItem);
        }else {
            Edge toMove = findWay();
            Colony.instance.walked.add(toMove);
            visited.add(currentPosition);
            if (toMove.getA() == currentPosition)
                currentPosition = toMove.getB();
            else if (toMove.getB() == currentPosition)
                currentPosition = toMove.getA();
            System.out.println("Moved to: " + currentPosition.getX() + ", " + currentPosition.getY());
        }
    }

    public Node<?> getCurrentPosition() {
        return currentPosition;
    }
}
