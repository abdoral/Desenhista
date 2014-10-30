/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desenhista.model;


/**
 *
 * @author abdoralneto
 */
public class Circulo implements Figuras {

    private double raio;
    private Ponto posicao;

    public Circulo(double raio, double x, double y) {
        posicao = new Ponto(x, y);
        this.raio = raio;
    }
    
    public Circulo(double raio, Ponto posicao) {
        this.posicao = posicao;
        this.raio = raio;
    }

    @Override
    public Ponto getPosicao() {
        return posicao;
    }
    
    public double getRaio() {
        return raio;
    }

}
