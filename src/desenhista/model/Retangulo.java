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

    public Retangulo(int x, int y, double altura, double largura) {
        posicao = new Ponto(x, y);
        this.altura = altura;
        this.largura = largura;
    }
    
    public Retangulo(double altura, double largura, Ponto posicao) {
        this.posicao = posicao;
        this.altura = altura;
        this.largura = largura;
    }

    @Override
    public Ponto getPosicao() {
        return posicao;
    }

    @Override
    public void setPosicao(Ponto posicao) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getNome() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
