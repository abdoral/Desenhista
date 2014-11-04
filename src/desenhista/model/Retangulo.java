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
public class Retangulo implements Figuras {

    private double altura, largura;
    private Ponto posicao;
    private String cor, nome;

    public Retangulo(int x, int y, double altura, double largura, String cor) {
        posicao = new Ponto(x, y);
        this.altura = altura;
        this.largura = largura;
        this.cor = cor;
    }
    
    public Retangulo(double altura, double largura, Ponto posicao, String cor) {
        this.posicao = posicao;
        this.altura = altura;
        this.largura = largura;
        this.cor = cor;
    }
    
    public Retangulo(double altura, double largura, Ponto posicao, String cor, String nome) {
        this.posicao = posicao;
        this.altura = altura;
        this.largura = largura;
        this.cor = cor;
        this.nome = nome;
    }

    @Override
    public Ponto getPosicao() {
        return posicao;
    }
    
    public double getAltura() {
        return altura;
    }
    
    public double getLargura() {
        return largura;
    }

    @Override
    public void setPosicao(Ponto posicao) {
        this.posicao = posicao;
    }

    @Override
    public String getNome() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getColor() {
        return cor;
    }

}
