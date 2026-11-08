package org.example.interfaces;

public record MusicBox(String music,boolean isPaused) implements MusicPlayer {

    @Override
    public void playMusic() {
        System.out.println("Playing music: " + music);
    }

    @Override
    public void pauseMusic() {
        System.out.println("Pausing music: " + music);
    }

    @Override
    public void stopMusic() {
        System.out.println("Stopping music: " + music);
    }
}
