/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desenhista.model;

import java.io.Serializable;
import javafx.geometry.Point2D;

/**
 *
 * @author abdoralneto
 */
public interface Figuras extends Serializable {
    
    public abstract Point2D getPosicao();
    
}
