package com.kauruck.Graph;

public class Edge {
    private final Node<?> A;
    private final Node<?> B;
    private float attractiveness = 1;

    public Edge(Node<?> A, Node<?> B){
        this.A = A;
        this.B = B;;
    }

    public Node<?> getA() {
        return A;
    }

    public Node<?> getB() {
        return B;
    }

    public float getAttractiveness() {
        return attractiveness;
    }

    public void setAttractiveness(float attractiveness) {
        this.attractiveness = attractiveness != 0 ? attractiveness : 0.0001f;
    }
}

