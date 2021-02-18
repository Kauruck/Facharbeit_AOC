package com.kauruck.Graph;

public class Edge {
    private final Node<?> A;
    private final Node<?> B;

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
}
