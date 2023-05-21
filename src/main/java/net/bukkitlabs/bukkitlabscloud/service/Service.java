package net.bukkitlabs.bukkitlabscloud.service;

public abstract class Service implements Runnable {
    protected boolean running;

    public Service() {
        running = false;
    }

    public void stop() {
        running = false;
    }
}