/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.pacman.sprites;

import java.awt.Color;
import java.awt.Graphics;
import main.pacman.interfaces.ISpritesMetodos;
import main.pacman.settings.Settings;

/**
 *
 * @author PC
 */
public class Pacman implements ISpritesMetodos {

    public static final int NRO_ANIMAC_BOCA = 10;
    private int x;
    private int y;
    private int tileX;
    private int tileY;
    private int direDefecto;
    
    // Presionada seria el indice de la fila de la matriz de direcciones (es decir si es derecha, izquierda, abajo o arriba)
    // Derecha = 0, izquierda =1, arriba = 2, abajo = 3
    private int presionada;
    private int presionadaConfirmada;
    private int[][] direcciones = {
        {1, 0, 80, 190, 45, 270},
        {-1, 0, 260, 190, 225, 270},
        {0, -1, 170, 190, 135, 270},
        {0, 1, 350, 190, 315, 270}
    };
    
    private int contadorAnima;
    private int paso;
    private int radIni;
    
    // Esta seria cual es la velocidad segun las direcciones, es decir si es derecha pues {1, 0}...
    private int[] velXY = {0,0};
    private int vel;
    private boolean avanza;

    public Pacman(int x, int y, int tileX, int tileY, int direDefecto) {
        this.x = x * tileX;
        this.y = y * tileY;
        this.tileX = tileX;
        this.tileY = tileY;
        this.direDefecto = direDefecto;
        
        this.presionada = this.direDefecto;
        this.presionadaConfirmada = this.presionada;
        this.radIni = direcciones[this.presionada][2];
        
        this.contadorAnima = NRO_ANIMAC_BOCA;
        this.paso = (int) ((85 + 5) / this.contadorAnima);
        
        this.velXY[0] = direcciones[this.presionada][0];
        this.velXY[1] = direcciones[this.presionada][1];
        
        this.vel = (int) (this.tileY / DIVISOR_TILE);
        this.avanza = true;
    }  

    @Override
    public void dibujar(Graphics graphics, Settings settings) {
        int[] rgb = {245, 215, 5};
        
        // Revisa si se dio a alguna tecla indicada del teclado y devuelve la direccion segun el index
        // de la matriz direcciones
        this.presionada = actualizarTeclado(settings);
        actualizarMovimiento(settings);
        
        // Esto es para hacer efectos de la boca
        this.contadorAnima--;
        if (this.contadorAnima <= 1) this.contadorAnima = NRO_ANIMAC_BOCA;
        
        
        int iniRad = radIni - contadorAnima * this.paso;
        int finRad = this.direcciones[0][3] + (contadorAnima * this.paso)*2;
        
        // Dibuja el pacman
        graphics.setColor(new Color(rgb[0], rgb[1], rgb[2]));
        graphics.fillArc(x, y, tileX, tileY, iniRad, finRad);
    }
 
    @Override
    public void actualizarMovimiento(Settings settings) {
        // Revisa si es el pacman esta en un cuadro o tile exacto para moverse
        if (x % tileX == 0 && y % tileY == 0) {
            // Obtiene las posiciones en matriz, es decir su indice
            int xMatriz = (int) (this.x / tileX);
            int yMatriz = (int) (this.y / tileY);
            
            Boolean colisionPresionada = checkColisionLaberintoPresionada(xMatriz, yMatriz, settings);
            Boolean colisionVelXY = checkColisionesVelXY(xMatriz, yMatriz, velXY, settings);
            checkColisionPuntos(xMatriz, yMatriz, settings);
            
            if (!colisionPresionada) {
                avanza = true;
                this.presionadaConfirmada = this.presionada;
                // Obtiene las velocidades segun la tecla presionada segun direcciones, esto ya se analizo en actualizaTeclado
                this.velXY[0] = this.direcciones[this.presionada][0];
                this.velXY[1] = this.direcciones[this.presionada][1];
                this.radIni = this.direcciones[this.presionada][2];
            } else if (!colisionVelXY) {
                avanza = true;
            }
            else avanza = false;
        }
        
        if (this.avanza) {
            // Corre un espacio determinado en "x" y en "y"
            this.x += velXY[0] * vel;
            this.y += velXY[1] * vel;
        }
    }
    
    private int actualizarTeclado(Settings settings) {
        if (settings.controles.isDerecha()) return 0;
                else if (settings.controles.isIzquierda()) return 1;
                else if(settings.controles.isArriba()) return 2;
                else if(settings.controles.isAbajo()) return 3;
        return 0;
    }
    
    private Boolean checkColisionPuntos(int xMatriz, int yMatriz, Settings sett) {
        // Revisa si esta a las afueras del mapa en ancho, esto porque existen escapatorias
        if (xMatriz < 0 || xMatriz >= sett.laberinto.COLUMNAS) return false;
        
        if (sett.laberinto.matriz[yMatriz][xMatriz] == sett.laberinto.PUNTITO) {
            sett.laberinto.setContadorPuntitos(sett.laberinto.getContadorPuntitos() - 1);
            sett.laberinto.matriz[yMatriz][xMatriz] = sett.laberinto.VACIO;
            sett.setPuntos(sett.getPuntos() + sett.SUMAR_PTOS_COME_PUNTITO);
            return true;
        }
        return false;
    }
    
    private Boolean checkColisionLaberintoPresionada(int xMatriz, int yMatriz, Settings sett) {
        int velX = this.direcciones[this.presionada][0];
        int velY = this.direcciones[this.presionada][1];
        
        // Revisa si esta a las afueras del mapa en ancho, esto porque existen escapatorias
        if (velX + xMatriz < 0 || velX + xMatriz >= sett.laberinto.COLUMNAS) return false;
        
        return sett.laberinto.matriz[velY + yMatriz][velX + xMatriz] == sett.laberinto.PARED;
    }
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getTileX() {
        return tileX;
    }


    public int getTileY() {
        return tileY;
    }
}
