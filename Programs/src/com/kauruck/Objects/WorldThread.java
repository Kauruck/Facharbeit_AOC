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

        while (running){
            long frameStartTime = System.nanoTime();
            //Do world update
            World.update(deltaTime);
            long currentTime = System.nanoTime();
            long frameDeltaTime = currentTime - frameStartTime;
            if(frameDeltaTime == 0){
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    stopped = true;
                    return;
                }
            }else {
                long waitTime = targetTime - frameDeltaTime;
                if (waitTime > 0) {
                    long delayMS = 0;
                    if (waitTime > 999999) {
                        delayMS = waitTime / 1000000;
                        waitTime = waitTime % 1000000;
                    }

                    if (waitTime > 999999) {
                        try {
                            Thread.sleep(delayMS, (int) waitTime);
                        } catch (InterruptedException e) {
                            stopped = true;
                            return;
                        }
                    }
                }

                deltaTime = System.nanoTime() - frameStartTime;
            }
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
