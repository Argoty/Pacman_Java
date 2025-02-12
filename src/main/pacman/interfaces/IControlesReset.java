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
public interface IControlesReset {
    default void resetControles(Boolean bool,Settings sett) {
        sett.controles.setIzquierda(bool);
        sett.controles.setDerecha(bool);
        sett.controles.setArriba(bool);
        sett.controles.setAbajo(bool);
    }
}
