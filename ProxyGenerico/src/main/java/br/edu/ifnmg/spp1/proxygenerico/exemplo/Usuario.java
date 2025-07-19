/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifnmg.spp1.proxygenerico.exemplo;

/**
 *
 * @author SamuelParanhos
 */
public class Usuario {
    
    private String usario;
    private long ID;

    public Usuario(String usario, long ID) {
        this.usario = usario;
        this.ID = ID;
    }

    public String getUsario() {
        return usario;
    }

    public long getID() {
        return ID;
    }
    
}
