package com.kauruck.AS;

import com.kauruck.Graph.Edge;
import com.kauruck.Graph.Node;
import com.kauruck.Items;
import com.kauruck.Objects.World;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Colony {
    public static Colony instance;
    public List<Edge> walked = new ArrayList<>();
    public List<Ant> ants = new ArrayList<>();

    public static final float evaporation = 0.5f;
    public static final float Q = 500;

    public Colony(int nAnts){
        instance = this;
        if(World.currentWorld == null)
            throw new NullPointerException("There must be a current world");
        Random r = new Random();
        for(int i = 0; i < nAnts; i++){
            int rNode = r.nextInt(World.currentWorld.getNodes().size());
            Ant current = new Ant(World.currentWorld.getNodes().get(rNode), Items.LOG);
            ants.add(current);
        }
    }

    public void update(){
        //Reset list of walked pathes
        walked.clear();
        //Ant moving
        for(Ant current : ants){
            current.move();
        }
        //Trail evaporation
        for(Node<?> current : World.currentWorld.getNodes()){
            for(Edge currentEdge : current.getEdges()){
                if(currentEdge.getA() == current){
                    currentEdge.setAttractiveness(currentEdge.getAttractiveness() * evaporation);
                }
            }
        }
        for(Edge currentEdge : walked){
            float d = (float)Math.sqrt((currentEdge.getA().getX() - currentEdge.getB().getX()) * (currentEdge.getA().getX() - currentEdge.getB().getX()));
            currentEdge.setAttractiveness(currentEdge.getAttractiveness() + Q/d);
        }
    }
}
