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
public class Ponto implements Serializable {
	double x,y;
	Ponto pa;
	
	public Ponto(double x, double y){
		this.x = x;
		this.y = y;
		pa = null;
	}
	
	public Ponto(double x, double y, Ponto pa){
		this(x,y);
		this.pa = pa;
	}	
	
	public void move(double x, double y){
		this.pa = new Ponto(this.x, this.y,this.pa);
		this.x = x; this.y = y;
	}
	
	public double getX() {return x;}
	public double getY() {return y;}
	public Ponto getPa() {return pa;}
	

}
