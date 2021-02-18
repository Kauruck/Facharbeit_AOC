package com.kauruck.Objects;

import com.kauruck.Main;
import com.kauruck.UI.MainFrame;

public class WorldThread implements Runnable{

    private long deltaTime = 0;
    private boolean running = true;
    private boolean stopped = false;
    private final long targetTime = ((long)1e9)/(Main.WORLDMAXFPS*2);

    public WorldThread(){
    }

    @Override
    public void run() {
        long frameStartTime = 0;
        while (running){
            deltaTime = System.nanoTime() - frameStartTime;

            //Do world update
            World.update(deltaTime);
            frameStartTime = System.nanoTime();
        }
        stopped = true;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public boolean isRunning() {
        return running;
    }

    public boolean isStopped() {
        return stopped;
    }

    public long getDeltaTime() {
        return deltaTime;
    }

    public int getFPS(){
        return (int) Math.round((1e9)/deltaTime);
    }
}
