/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.pacman.sprites;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author PC
 */
public class Pared {
    private int x;
    private int y;
    private int tileX;
    private int tileY;
    
    public Pared(int x, int y, int tileX, int tileY) {
        this.tileX = tileX;
        this.tileY = tileY;
        this.x = x * tileX;
        this.y = y * tileY;
    }
    public void dibujar(Graphics graphics) {
        int[] rgb = {157, 157, 98, 128, 128, 82};
        // Ponemos color y lo posicionamos en la ventana
        graphics.setColor(new Color(rgb[0], rgb[1], rgb[2]));
        graphics.fillRect(x, y, tileX - 1, tileY -1);
        
        // Para dar mas forma 3d
        graphics.setColor(new Color(rgb[3], rgb[4], rgb[5]));
        graphics.fillRect(x, y, tileX - 3, tileY -3);
    }
}
