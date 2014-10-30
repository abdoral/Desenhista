/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desenhista.model;

import java.io.Serializable;

/**
 *
 * @author abdoralneto
 */
public interface Figuras extends Serializable {
    
    public abstract Ponto getPosicao();
    
    public abstract void setPosicao(Ponto posicao);
    
    public abstract String getNome();
    
}
