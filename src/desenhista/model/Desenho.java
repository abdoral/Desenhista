/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desenhista.model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author abdoralneto
 */
public class Desenho implements Serializable {

    ArrayList<Figuras> listFigs;
    private String nome;

    public Desenho(String nome) {
        listFigs = new ArrayList<>();
        this.nome = nome;
    }
    
    public String getNome() {
        return nome;
    }

    /*Adiciona as informações da figura criada a uma lista*/
    public void setFigura(double raio, Ponto posicao) {
        listFigs.add(new Circulo(raio, posicao));
    }

    public void setFigura(double altura, double largura, Ponto posicao) {
        listFigs.add(new Retangulo(altura, largura, posicao));
    }
    
    public void salvarDesenho(String filename) {

        FileOutputStream fos = null;
        ObjectOutputStream out = null;
        try {
            fos = new FileOutputStream(filename+".des");
            out = new ObjectOutputStream(fos);
            out.writeObject(listFigs);
            out.close();
            System.out.println("Desenho Salvo");
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    public static ArrayList<Figuras> abrirDesenho(String filename) {

        ArrayList<Figuras> figs = null;
        FileInputStream fis = null;
        ObjectInputStream in = null;

        try {
            fis = new FileInputStream(filename);
            in = new ObjectInputStream(fis);
            figs = (ArrayList<Figuras>) in.readObject();
            in.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return figs;

    }

}
