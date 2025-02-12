/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.pacman.sprites;

import java.awt.Color;
import java.awt.Graphics;
import main.pacman.interfaces.ISpritesDibujo;
import main.pacman.settings.Settings;

/**
 *
 * @author PC
 */
public class Puntos implements ISpritesDibujo{
    public static final int INI_TAMANO_GRANDES = 30;
    public static final int TAMANO_NORMAL = 10;
    
    private int x;
    private int y;
    private int tileX;
    private int tileY;
    private int matrizX;
    private int matrizY;
    private boolean grande;
    private int tamano;
    private int reDimension = -1;

    public Puntos(int x, int y, int tileX, int tileY, boolean grande) {
        this.matrizX = x;
        this.matrizY = y;
        this.x = x * tileX;
        this.y = y * tileY;
        this.tileX = tileX;
        this.tileY = tileY;
        this.grande = grande;
        this.tamano = grande ? INI_TAMANO_GRANDES : TAMANO_NORMAL;
    }

    @Override
    public void dibujar(Graphics graphics, Settings settings) {
        int[] rgb = {220, 220, 220, 200, 255, 9};
        final int RADIO = (int) (tamano /2);
        final int CENTRO_X = (int) (tileX / 2);
        final int CENTRO_Y = (int) (tileY / 2);
        
        if (grande) {
            graphics.setColor(new Color(rgb[3], rgb[4], rgb[5]));
            this.tamano += cambiarSizeGrandes();
        } else {
            graphics.setColor(new Color(rgb[0], rgb[1], rgb[2]));
        }
        
        graphics.fillOval(x + CENTRO_X - RADIO, y + CENTRO_Y - RADIO, tamano, tamano);
    }
    private int cambiarSizeGrandes() {
		
		if (reDimension > 0 && tamano >= INI_TAMANO_GRANDES) {
			
			reDimension = -1;
			
		} else if (reDimension < 0 && tamano <= 1) {
			
			reDimension = 1;
		}
		
		return reDimension;
	}
    
}
