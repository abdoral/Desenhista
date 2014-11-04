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
    private String nome;
    private String cor;

    public Circulo(String nome, double raio, double x, double y, String cor) {
        posicao = new Ponto(x, y);
        this.nome = nome;
        this.raio = raio;
        this.cor = cor;
    }
    
    public Circulo(String nome, double raio, Ponto posicao, String cor) {
        this.posicao = posicao;
        this.nome = nome;
        this.raio = raio;
        this.cor = cor;
    }
    
    public Circulo(double raio, Ponto posicao, String cor) {
        this.posicao = posicao;
        this.raio = raio;
        this.cor = cor;
    }

    @Override
    public Ponto getPosicao() {
        return posicao;
    }
    
    public double getRaio() {
        return raio;
    }

    @Override
    public void setPosicao(Ponto posicao) {
        this.posicao = posicao;
    }

    @Override
    public String getNome() {
        return nome;
    }

    @Override
    public String getColor() {
        return cor;
    }

}
