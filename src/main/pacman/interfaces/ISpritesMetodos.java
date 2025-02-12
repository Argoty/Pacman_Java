/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package main.pacman.interfaces;

import main.pacman.settings.Settings;

/**
 *
 * @author PC
 */
public interface ISpritesMetodos extends ISpritesDibujo {
    // La velocidad seria (TILE / DIVISOR_TILE)
    static final int DIVISOR_TILE = 10;
    
    void actualizarMovimiento(Settings settings);
    
    default boolean checkColisionesVelXY(int x, int y, int[] velXY, Settings settings) {
        // Revisa si esta a las afueras del mapa en ancho, esto porque existen escapatorias
        if (velXY[0] + x < 0 || velXY[0] + x >= settings.laberinto.COLUMNAS) return false;
        
        return settings.laberinto.matriz[velXY[1] + y][velXY[0] + x] == settings.laberinto.PARED;
    }
    default int fugas(int x, int laberintoLength, int tileX, int direccion) {
        return 0;
    }
}
