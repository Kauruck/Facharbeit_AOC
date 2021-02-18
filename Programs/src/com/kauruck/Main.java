package com.kauruck;

import com.kauruck.Graph.Node;
import com.kauruck.Objects.Building;
import com.kauruck.Objects.ItemStack;
import com.kauruck.Objects.World;
import com.kauruck.Objects.WorldThread;
import com.kauruck.UI.GraphicsThread;
import com.kauruck.UI.MainFrame;

public class Main {

    private static final MainFrame frame = new MainFrame();

    public static final int MAXFPS = 60;

    public static final int WORLDMAXFPS = 500;

    public static final GraphicsThread graphicsThread = new GraphicsThread(frame);
    public static final WorldThread worldThread = new WorldThread();

    private static final Thread GT = new Thread(graphicsThread);
    private static final Thread WT = new Thread(worldThread);

    public static final int NODER = 20;
    public static final int NODEHOVERR = 25;

    public static void main(String[] args){
        GT.start();
        WT.start();
        World.currentWorld = new World();
        World.currentWorld.add(new Node<>(100,200,Buildings.LUMBERJACK));

        World.currentWorld.add(new Node<>(100,200,Buildings.LUMBERJACK));
    }

    public static void exit(){
        //Stop world thread
        worldThread.setRunning(false);
        while (!worldThread.isStopped());
        System.out.println("Stopped world updates");
        //Stop graphics thread
        graphicsThread.setRunning(false);
        while (!graphicsThread.isStopped());
        System.out.println("Stopped rendering");
        System.exit(0);
    }

}
