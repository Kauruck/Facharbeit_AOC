package com.kauruck.UI;

import com.kauruck.Buildings;
import com.kauruck.Graph.Node;
import com.kauruck.Main;
import com.kauruck.Objects.World;

import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.*;

public class MainPanel extends JPanel implements KeyListener, MouseListener, MouseMotionListener, MouseInputListener {

    private Node<?> hover = null;
    private Node<?> select = null;
    int dragX = -100;
    int dragY = -100;


    public MainPanel(){
        this.addKeyListener(this);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }


    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(World.render(this.getWidth(),this.getHeight()),0,0,null);
        g.setColor(Color.BLACK);
        g.drawString(Main.graphicsThread.getFPS() + "", 20,20);
        g.setColor(Color.RED);
        g.drawString(Main.worldThread.getFPS() + "", 20,40);
        if(hover != null){
            g.setColor(Color.YELLOW);
            g.drawOval(hover.getX() - (Main.NODEHOVERR / 2), hover.getY() - (Main.NODEHOVERR / 2), Main.NODEHOVERR, Main.NODEHOVERR );
        }
        if(dragY != -100 && dragX != -100 && select != null){
            g.setColor(Color.CYAN);
            g.drawLine(select.getX(),select.getY(),dragX,dragY);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(SwingUtilities.isLeftMouseButton(e)){
            Node<?> target = World.currentWorld.getAt(e.getX(),e.getY(),Main.NODEHOVERR);
            if(target == null){
                World.currentWorld.add(new Node<>(e.getX(),e.getY(), Buildings.LUMBERJACK));
            }
            else{
                select = target;
            }
        }
        else if(SwingUtilities.isRightMouseButton(e)){
            select = World.currentWorld.getAt(e.getX(),e.getY(),Main.NODEHOVERR);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

        if(SwingUtilities.isRightMouseButton(e) && dragY != - 100 && dragX != -100){
            Node<?> target = World.currentWorld.getAt(dragX,dragY,Main.NODEHOVERR);
            if(target != null) {
                World.currentWorld.connect(select, target);
            }
        }

        dragY = -100;
        dragX = -100;

        select = null;
    }


    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if(SwingUtilities.isRightMouseButton(e)) {
            dragX = e.getX();
            dragY = e.getY();
        }
        else if(SwingUtilities.isLeftMouseButton(e)){
            if(select != null){
                select.setX(e.getX());
                select.setY(e.getY());
            }
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        hover = World.currentWorld.getAt(x,y,Main.NODEHOVERR);
    }
}
