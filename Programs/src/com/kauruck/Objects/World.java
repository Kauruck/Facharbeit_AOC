package com.kauruck.Objects;

import com.kauruck.Graph.Edge;
import com.kauruck.Graph.Node;
import com.kauruck.Main;

import java.awt.*;
import java.awt.image.VolatileImage;
import java.util.ArrayList;
import java.util.List;

//https://www.codota.com/code/java/classes/java.awt.GraphicsConfiguration[17.02.2021]

public class World {

    public static World currentWorld = null;

    private List<Node<?>> nodes = new ArrayList<>();

    public static void update(long deltaTime){
        if(currentWorld == null)
            return;

        for(int i = 0; i < currentWorld.nodes.size(); i++){
            currentWorld.nodes.get(i).getContent().update(deltaTime);
        }
    }

    public void add(Node<?> node){
        nodes.add(node);
    }

    public void remove(Node<?> node){
        nodes.remove(node);
    }

    public void connect(Node<?> A, Node<?> B){
        if(!nodes.contains(A) || !nodes.contains(B))
            throw new IllegalArgumentException("A & B must be added to the world first!");
        for(Node<?> current : nodes){
            if(current == A){
                current.connectTo(B);
            }
        }
    }

    public Node<?> getAt(int x, int y, int r){
        for(Node<?> current : nodes){
            if(current.getX() > x - r && current.getX() < x + r){
                if(current.getY() > y - r && current.getY() < y + r){
                    return current;
                }
            }
        }
        return null;
    }


    //https://math.stackexchange.com/questions/198764/how-to-know-if-a-point-is-inside-a-circle[17.02.2021]
    public int[] onLine(int x1, int y1, int x2, int y2, int testX, int testY){
        int m = (y2 - y1)/(x2 - x1);
        int movedY = testY - y1;
        int y = m * (testX-x1);
        return new int[]{(testX-x1),y};
    }

    public static VolatileImage render(int width, int height){
        VolatileImage out = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration().createCompatibleVolatileImage(width,height);
        Graphics g = out.createGraphics();
        if(currentWorld == null)
            return out;

        g.setColor(Color.BLACK);
        for(Node<?> current : currentWorld.nodes){
            for(Edge currentEdge : current.getEdges()){
                if(currentEdge.getA() == current){
                    g.drawLine(current.getX(), current.getY(),currentEdge.getB().getX(),currentEdge.getB().getY());
                }
            }

            g.drawOval(current.getX() - 10, current.getY() - 10, 20,20);
        }

        for(Node<?> current : currentWorld.nodes){
            g.setColor(current.getContent().getColor());
            g.drawOval(current.getX() - (Main.NODER/2), current.getY() - (Main.NODER/2), Main.NODER,Main.NODER);
            drawString(g, current.getContent().getInventory().toString(), current.getX(), current.getY() + Main.NODER);
        }
        g.dispose();
        return out;
    }

    //From: https://stackoverflow.com/questions/4413132/problems-with-newline-in-graphics2d-drawstring[18.02.2021]
    private static void drawString(Graphics g, String text, int x, int y) {
        for (String line : text.split("\n"))
            g.drawString(line, x, y += g.getFontMetrics().getHeight());
    }
}
