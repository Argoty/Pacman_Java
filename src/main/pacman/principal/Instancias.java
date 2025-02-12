/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.pacman.principal;

import java.util.ArrayList;
import main.pacman.settings.Settings;
import main.pacman.sprites.Pacman;
import main.pacman.sprites.Pared;
import main.pacman.sprites.Puntos;

/**
 *
 * @author PC
 */
public class Instancias {
    private int filas;
    private int columnas;
    private int ancho;
    private int alto;
    private Settings settings;
    private ArrayList<Pared> paredes = new ArrayList<>();
    private ArrayList<Puntos> puntos = new ArrayList<>();
    private Pacman pacman;

    
    public Instancias(Settings settings){
        this.settings = settings;
        this.filas = settings.laberinto.FILAS;
        this.columnas = settings.laberinto.COLUMNAS;
        this.ancho = settings.laberinto.TILE_X;
        this.alto = settings.laberinto.TILE_Y;
    }
    
    public ArrayList<Pared> initParedesLaberinto() {
        
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                int tile = settings.laberinto.matriz[i][j];
                if (tile == settings.laberinto.PARED) paredes.add(new Pared(j, i, ancho, alto));
            }
        }
        return paredes;
    }
    public ArrayList<Puntos> initPuntosLaberinto() {
        
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                int tile = settings.laberinto.matriz[i][j];
                if (tile == settings.laberinto.PUNTITO) {
                    puntos.add(new Puntos(j, i, ancho, alto, false));
                    int contadorPuntos = settings.laberinto.getContadorPuntitos();
                    settings.laberinto.setContadorPuntitos(contadorPuntos+1);
                }
                else if (tile == settings.laberinto.PUNTITO_GORDO) {
                    puntos.add(new Puntos(j, i, ancho, alto, true));
                    int contadorPuntosGordos = settings.laberinto.getContadorPuntitosGordos();
                    settings.laberinto.setContadorPuntitosGordos(contadorPuntosGordos+1);
                }
            }
        }
        return puntos;
    }
    public Pacman initPacman(){
        int[][] args = settings.getIniSprites();
        Pacman pacman = new Pacman(args[0][0], args[0][1], settings.laberinto.TILE_X, settings.laberinto.TILE_Y, args[0][3]);
        return pacman;
    }
}
