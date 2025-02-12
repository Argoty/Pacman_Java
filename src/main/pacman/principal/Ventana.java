/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.pacman.principal;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.Timer;
import main.pacman.interfaces.IControlesReset;
import main.pacman.settings.Settings;
import main.pacman.sprites.Pacman;
import main.pacman.sprites.Pared;
import main.pacman.sprites.Puntos;

/**
 *
 * @author PC
 */
public class Ventana extends JPanel implements ActionListener, IControlesReset {
    private Settings settings;
    private Instancias instancias;
    private ArrayList<Pared> paredes;
    private ArrayList<Puntos> puntos;
    private Pacman pacman;
    private Timer timer;
    
    public Ventana() {
        init();
    }
    private void init() {
        settings = Settings.getInstancia();
        
        addKeyListener(new Controles());
        
        int[] rgb = settings.getColorFondos();
        setBackground(new Color(rgb[3], rgb[4], rgb[5]));
        setFocusable(true);
        setPreferredSize(new Dimension(settings.laberinto.RES_X, settings.laberinto.RES_Y));
        
        empezar();
        
    }
    private void empezar() {
        instancias = new Instancias(settings);
        paredes = instancias.initParedesLaberinto();
        puntos = instancias.initPuntosLaberinto();
        pacman = instancias.initPacman();
        
        timer = new Timer((int) (1000/60), this);
        timer.start();
        timer.setRepeats(true);
    }
    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        renderizar(graphics);
    }
    
    private void renderizar(Graphics graphics){
        int[][] matriz = settings.laberinto.matriz;
        for(Pared pared: paredes) {
            pared.dibujar(graphics);
        }
        for(Puntos punto: puntos) {
            punto.dibujar(graphics, settings);
        }
        
        if (pacman != null) pacman.dibujar(graphics, settings);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }
    private class Controles extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e){
            int key = e.getKeyCode();
            if (key == KeyEvent.VK_RIGHT) {
                resetControles(false, settings);
                settings.controles.setDerecha(true);
            }
            else if (key == KeyEvent.VK_LEFT) {
                resetControles(false, settings);
                settings.controles.setIzquierda(true);
            }
            else if (key == KeyEvent.VK_UP) {
                resetControles(false, settings);
                settings.controles.setArriba(true);
            }
            else if (key == KeyEvent.VK_DOWN) {
                resetControles(false, settings);
                settings.controles.setAbajo(true);
            }
            
            if (key == KeyEvent.VK_ESCAPE) {
                Toolkit.getDefaultToolkit().beep();
                System.exit(0);
            }
        }
    }
}
