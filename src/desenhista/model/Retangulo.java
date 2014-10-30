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
public class Retangulo implements Figuras {

    private double altura, largura;
    private Point2D posicao;

    public Retangulo(int x, int y, double altura, double largura) {
        posicao = new Point2D(x, y);
        this.altura = altura;
        this.largura = largura;
    }
    
    public Retangulo(double altura, double largura, Point2D posicao) {
        this.posicao = posicao;
        this.altura = altura;
        this.largura = largura;
    }

    @Override
    public Point2D getPosicao() {
        return posicao;
    }

}
