 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.pacman.audio;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 *
 * @author PC
 */
public class Sonidos {
    private Clip clip;
    public Sonidos() {
        
    }
    public void cargarAudio(String ruta) {
        try {
            File sonido_intermision = new File(ruta);
            AudioInputStream ais = AudioSystem.getAudioInputStream(sonido_intermision);
            clip = AudioSystem.getClip();
            clip.open(ais);
            
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    public void reproducirAudio() {
        if (clip != null) {
            clip.setFramePosition(0);
            clip.start();
        }
    }
    public void detenerAudio() {
        if (clip != null || clip.isRunning()) {
            clip.stop();
        }
    }
    public void reproducirAudioBucle() {
        if (clip != null) {
            clip.setFramePosition(0);
            clip.start();
            clip.loop(clip.LOOP_CONTINUOUSLY);
        }
    }

    public Clip getClip() {
        return clip;
    }
    public Boolean estaReproduciendo() {
        return clip.isRunning();
    }
    
}
