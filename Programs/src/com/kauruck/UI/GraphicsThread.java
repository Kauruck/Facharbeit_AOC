package com.kauruck.UI;

import com.kauruck.Main;

import java.util.concurrent.TimeUnit;

//https://stackoverflow.com/questions/10560167/putting-runnable-to-sleep[17.02.2021]
//https://www.journaldev.com/1020/thread-sleep-java[17.02.2021]
//https://www.java-forum.org/thema/von-fps-und-framelimitern.98384/[17.02.2021]
//https://stackoverflow.com/questions/771206/how-do-i-cap-my-framerate-at-60-fps-in-java[17.02.2021]

public class GraphicsThread implements Runnable{
    private long deltaTime = 0;
    private boolean running = true;
    private boolean stopped = false;
    private final MainFrame target;
    private final long targetTime = ((long)1e9)/(Main.MAXFPS*2);

    public GraphicsThread(MainFrame target){
        this.target = target;
    }

    @Override
    public void run() {

        while (running){
            long frameStartTime = System.nanoTime();
            //Do graphics
            target.repaint();
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

                    if (waitTime > 0) {
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
