/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desenhista.model;

import javafx.geometry.Point2D;

/**
 *
 * @author abdoralneto
 */
public class Circulo implements Figuras {

    private double raio;
    private Point2D posicao;

    public Circulo(double raio, double x, double y) {
        posicao = new Point2D(x, y);
        this.raio = raio;
    }
    
    public Circulo(double raio, Point2D posicao) {
        posicao = posicao;
        this.raio = raio;
    }

    @Override
    public Point2D getPosicao() {
        return posicao;
    }

}
