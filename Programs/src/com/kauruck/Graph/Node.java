package com.kauruck.Graph;

import com.kauruck.Objects.Building;
import com.kauruck.Objects.Warehouse;

import java.util.ArrayList;
import java.util.List;

public class Node<T extends Building> {

    private int x;
    private int y;
    private final T content;

    private final List<Edge> edges = new ArrayList<>();

    public Node(int x, int y, T content){
        this.x = x;
        this.y = y;
        this.content = content;
        if(content instanceof Warehouse){
            ((Warehouse) content).setHolder((Node<Warehouse>) this);
        }
    }

    public void connectTo(Node<?> node){
        Edge edge = new Edge(this,node);
        this.edges.add(edge);
        node.addEdge(edge);
    }

    private void addEdge(Edge edge){
        edges.add(edge);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public T getContent() {
        return content;
    }

    public List<Edge> getEdges() {
        return edges;
    }
}
