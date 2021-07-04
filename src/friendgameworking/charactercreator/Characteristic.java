/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package friendgameworking.charactercreator;

import java.awt.Color;

/**
 *
 * @author julian
 */
public class Characteristic {
    
    private int id = 0;
    private Color color = Color.WHITE;
    private int maxValue;
    
    public int incrementId() {
        id++;
        if (id == maxValue) {
            id = 0;
        }
        return id;
    }
    
    public int decrementId() {
        id--;
        if (id < 0) {
            id = maxValue-1;
        }
        return id;
    }
    
    public Characteristic(int maxValue) {
        this.maxValue = maxValue;
    }
    
    public int getId() {
        return id;
    }
    
    public void setColor(Color col) {
        color = col;
    }
    
    public Color getColor() {
        return color;
    }
    
}
