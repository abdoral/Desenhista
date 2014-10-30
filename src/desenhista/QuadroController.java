/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desenhista;

import desenhista.model.Desenho;
import desenhista.model.Figuras;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Point2D;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeView;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author abdoralneto
 */
public class QuadroController implements Initializable {

    @FXML
    private TextField coordenada_x;
    @FXML
    private TextField coordenada_y;
    @FXML
    private TextField raio;
    @FXML
    private TextField altura;
    @FXML
    private TextField largura;
    @FXML
    private AnchorPane desenhos;
    @FXML
    private AnchorPane tela;

    @FXML
    private Button add;
    @FXML
    private Button remover;
    @FXML
    private Button salvar;
    @FXML
    private Button novo;
    @FXML
    private TreeView<?> projetos;

    private Desenho desenho;
    private int count;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

    }

    @FXML
    public void novo() {
        desenho = new Desenho("Projeto" + (++count));
        novo.setDisable(true);
    }

    @FXML
    public void add() {
        desenho.setFigura(Double.parseDouble(raio.getText()), new Point2D(Double.parseDouble(coordenada_x.getText()), Double.parseDouble(coordenada_y.getText())));
        raio.setText(null);
        coordenada_x.setText(null);
        coordenada_y.setText(null);

    }

    @FXML
    public void remover() {
        System.out.println("Eu fui acionado");
        ArrayList<Figuras> figs = Desenho.abrirDesenho("Projeto1");
        System.out.println("Total de figuras:" + figs.size());
        for (int i = 0; i < figs.size(); i++) {
            System.out.println("Posição X:" + figs.get(i).getPosicao().getX());
            //System.out.println("Posição Y:" + figs.get(i).getPosicao().getY());
            System.out.println("-------------------------------------------------");
        }

    }

    @FXML
    public void salvar() {
        desenho.salvarDesenho(desenho.getNome());

    }

}
