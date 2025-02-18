/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.pacman.sprites;

import java.awt.Graphics;
import main.pacman.interfaces.ISpritesMetodos;
import main.pacman.principal.Ventana;
import main.pacman.settings.Settings;

/**
 *
 * @author PC
 */
public class Fantasma implements ISpritesMetodos {

    private static Boolean sonComibles = false;
    private static Boolean estanIntermitentes = false;
    private Boolean estaComido = false;

    // {direccionX, direccionY, direccion}
    // ( 1,0 = ri | -1,0 = le | 0,-1 up | 0,1 = do )
    private int[][] direcciones = {{1, 0, 0}, {-1, 0, 1}, {0, -1, 2}, {0, 1, 3},};

    // Posibles random direcciones (excluimos la direccion actual)
    private int[][] otraDireccionRND = {{1, 2, 3}, {0, 2, 3}, {0, 1, 3}, {0, 1, 2},};

    // Puntos donde los fantasmas pueden cambiar de direccion
    private static int[][] ptosClave = {{4, 1}, {14, 1}, {4, 4}, {6, 4}, {12, 4}, {14, 4}, {4, 8},
    {6, 8}, {12, 8}, {14, 8}, {1, 11}, {4, 11}, {6, 11}, {12, 11}, {14, 11}, {17, 11},
    {4, 13}, {9, 13}, {14, 13}};

    private int id;
    private int x;
    private int y;
    private int tileX;
    private int tileY;
    private int direDefecto = 0;
    private int direccion;
    private int[] velXY = {0, 0};
    private int vel;
    private Boolean avanza;

    private Ventana ventana;
    private int[] coordPacMan;

    @Override
    public void actualizarMovimiento(Settings settings) {
    }

    @Override
    public boolean checkColisionesVelXY(int x, int y, int[] velXY, Settings settings) {
        return true;
    }

    @Override
    public void dibujar(Graphics graphics, Settings settings) {
    }

}
