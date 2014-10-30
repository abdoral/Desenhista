/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desenhista;

import desenhista.model.Circulo;
import desenhista.model.Desenho;
import desenhista.model.Figuras;
import desenhista.model.Ponto;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Point2D;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeView;
import javafx.scene.effect.InnerShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;

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
    private Button arquivo;

    private File file;
    private double initX;
    private double initY;
    private Ponto dragAnchor;
    private Desenho desenho;
    private int count;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        tela.setDisable(true);

    }

    @FXML
    public void novo() {
        desenho = new Desenho("Projeto" + (++count));
        novo.setDisable(true);
        tela.setDisable(false);
    }

    @FXML
    public void add() {
        desenho.setFigura(Double.parseDouble(raio.getText()), new Ponto(Double.parseDouble(coordenada_x.getText()), Double.parseDouble(coordenada_y.getText())));
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
            //System.out.println(figs.get(i));
            System.out.println("Posição X:" + figs.get(i).getPosicao().getX());
            System.out.println("Posição Y:" + figs.get(i).getPosicao().getY());
            System.out.println("Raio:" + ((Circulo) figs.get(i)).getRaio());
            System.out.println("-------------------------------------------------");
        }

    }

    @FXML
    public void salvar() {
        desenho.salvarDesenho(desenho.getNome());

    }

    @FXML
    private void abrirArquivo(ActionEvent event) {
        final Label labelFile = new Label();

        FileChooser fileChooser = new FileChooser();

        //Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("DES files (*.des)", "*.des");
        fileChooser.getExtensionFilters().add(extFilter);

        //Show open file dialog
        file = fileChooser.showOpenDialog(null);

        //System.out.println(file.getPath());
        carregaCenario(file.getName());
        //labelFile.setText(file.getPath());
        //labelFile.getText();
    }

    private Circle createCircle(final String name, final Color color, double radius) {
        //create a circle with desired name,  color and radius
        final Circle circle = new Circle(radius, new RadialGradient(0, 0, 0.2, 0.3, 1, true, CycleMethod.NO_CYCLE, new Stop[]{
            new Stop(0, Color.rgb(250, 250, 255)),
            new Stop(1, color)
        }));
        circle.setId("circulo"+count);
        //add a shadow effect
        circle.setEffect(new InnerShadow(7, color.darker().darker()));
        //change a cursor when it is over circle
        circle.setCursor(Cursor.HAND);
        //add a mouse listeners
        circle.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                //showOnConsole("Clicked on" + name + ", " + me.getClickCount() + "times");
                //the event will be passed only to the circle which is on front
                me.consume();
            }
        });
        circle.setOnMouseDragged(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                double dragX = me.getSceneX() - dragAnchor.getX();
                double dragY = me.getSceneY() - dragAnchor.getY();
                //calculate new position of the circle
                double newXPosition = initX + dragX;
                double newYPosition = initY + dragY;
                //if new position do not exceeds borders of the rectangle, translate to this position
                if ((newXPosition >= circle.getRadius()) && (newXPosition <= 500 - circle.getRadius())) {
                    circle.setTranslateX(newXPosition);
                }
                if ((newYPosition >= circle.getRadius()) && (newYPosition <= 300 - circle.getRadius())) {
                    circle.setTranslateY(newYPosition);
                }
                //showOnConsole(name + " was dragged (x:" + dragX + ", y:" + dragY +")");
            }
        });
        circle.setOnMouseEntered(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                //change the z-coordinate of the circle
                circle.toFront();
                //showOnConsole("Mouse entered " + name);
            }
        });
        circle.setOnMouseExited(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                //showOnConsole("Mouse exited " +name);
            }
        });
        circle.setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                //when mouse is pressed, store initial position
                initX = circle.getTranslateX();
                initY = circle.getTranslateY();
                dragAnchor = new Ponto(me.getSceneX(), me.getSceneY());
                //showOnConsole("Mouse pressed above " + name);
            }
        });
        circle.setOnMouseReleased(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                //showOnConsole("Mouse released above " +name);
                //int i  = circle.getId();
            }
        });

        return circle;
    }

    @FXML
    private void criaFigura(MouseEvent event) {

        final Circle circulo = createCircle("Blue circle", Color.DODGERBLUE,
                                            Double.parseDouble(raio.getText()));
        circulo.setTranslateX(event.getX());
        circulo.setTranslateY(event.getY());
        tela.getChildren().addAll(circulo);
        desenho.setFigura(Double.parseDouble(raio.getText()), new Ponto(event.getX(), event.getY()));
        //raio.setText(null);
        //coordenada_x.setText(null);
        //coordenada_y.setText(null);
        //System.out.println("Eu fui acionado");
    }

    @FXML
    private void desabilaParams(MouseEvent event) {
        coordenada_x.setDisable(true);
        coordenada_y.setDisable(true);
        altura.setDisable(true);
        largura.setDisable(true);
    }

    @FXML
    private void habilitaParams(MouseEvent event) {
        coordenada_x.setDisable(false);
        coordenada_y.setDisable(false);
        altura.setDisable(false);
        largura.setDisable(false);
    }
    
    private void carregaCenario(String filename) {
        
        ArrayList<Figuras> figs = Desenho.abrirDesenho(filename);
        
        desenho  = new Desenho(filename);
        
        for(int i = 0; i < figs.size(); i++) {
            Circle circulo = createCircle("Blue circle", Color.DODGERBLUE,
                                            ((Circulo)figs.get(i)).getRaio());
            circulo.setTranslateX(figs.get(i).getPosicao().getX());
            circulo.setTranslateY(figs.get(i).getPosicao().getY());
            tela.getChildren().addAll(circulo);
            desenho.setFigura(((Circulo)figs.get(i)).getRaio(), new Ponto(figs.get(i).getPosicao().getX(), figs.get(i).getPosicao().getY()));
        }
        
        tela.setDisable(false);
    }

}
