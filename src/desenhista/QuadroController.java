/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desenhista;

import desenhista.model.Circulo;
import desenhista.model.Desenho;
import desenhista.model.Figuras;
import desenhista.model.Linha;
import desenhista.model.Ponto;
import desenhista.model.Retangulo;
import java.awt.Event;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Point2D;
import javafx.scene.Cursor;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.TreeView;
import javafx.scene.effect.InnerShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
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
    @FXML
    private ComboBox<String> figura;
    @FXML
    private ColorPicker cor;

    private File file;
    private double initX;
    private double initY;
    private Ponto dragAnchor;
    private Desenho desenho;
    private ArrayList<Shape> shape;
    private int count;
    private String fileName;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        tela.setDisable(true);
        figura.setDisable(true);
        cor.setDisable(true);
        coordenada_x.setDisable(true);
        coordenada_y.setDisable(true);
        largura.setDisable(true);
        altura.setDisable(true);
        add.setDisable(true);
        remover.setDisable(true);
        salvar.setDisable(true);
        raio.setDisable(true);
        shape = new ArrayList<>();
        //count = 0;

    }

    @FXML
    public void novo() {
        desenho = new Desenho();
        novo.setDisable(true);
        figura.setDisable(false);
        carregaComboBoxFigura();
        limparTela();
    }

    @FXML
    public void add() {
//        desenho.setFigura(Double.parseDouble(raio.getText()), new Ponto(Double.parseDouble(coordenada_x.getText()), Double.parseDouble(coordenada_y.getText())), Color.ANTIQUEWHITE.toString(), Integer.toString(count));
//        raio.setText(null);
//        coordenada_x.setText(null);
//        coordenada_y.setText(null);

    }

    @FXML
    public void remover() {
        //System.out.println("Eu fui acionado");
        try {
            tela.getChildren().remove(shape.get(count - 1));
            desenho.remover(count-1);
            shape.remove(count-1);
            count--;
        } catch (Exception e) {
            mensagemErro("Erro", "Sem figuras para serem removidas.", "Adicione novas figuras.");
        }

    }

    @FXML
    public void salvar() {
        TextInputDialog dialog = new TextInputDialog("Nome Arquivo");
        dialog.setTitle("Salvar");
        dialog.setHeaderText("Salvar desenho");
        dialog.setContentText("Informe o nome do desenho:");

        // Traditional way to get the response value.
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()){
            System.out.println("Your name: " + result.get());
            desenho.salvarDesenho(result.get());
        }
        novo.setDisable(false);
        salvar.setDisable(true);
//        desenho.salvarDesenho(desenho.getNome());

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
        //System.out.println(file.getName());
        //System.out.println(file.getName());
        carregaCenario(file.getName());
        //System.out.println(file.getName());
        //labelFile.setText(file.getPath());
        //labelFile.getText();
    }

    private Circle createCircle(final String name, final Color color, double radius) {
        //create a circle with desired name,  color and radius
        Circle circle = new Circle(radius, new RadialGradient(0, 0, 0.2, 0.3, 1, true, CycleMethod.NO_CYCLE, new Stop[]{
            new Stop(0, Color.rgb(250, 250, 255)),
            new Stop(1, color)
        }));
        circle.setId(Integer.toString(count));
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
                if ((newXPosition >= circle.getRadius()) && (newXPosition <= tela.getHeight() - circle.getRadius())) {
                    circle.setTranslateX(newXPosition);
                }
                if ((newYPosition >= circle.getRadius()) && (newYPosition <= tela.getWidth() - circle.getRadius())) {
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
                salvar.setDisable(false);
                desenho.atualizaPosicao(Integer.parseInt(circle.getId()), new Ponto(me.getSceneX(), me.getSceneY()));
            }
        });
        count++;
        System.out.println("Cheguei aqui tbm");
        return circle;
    }

    private Rectangle createRectangle(final String name, final Color color, double largura, double altura) {

        Rectangle rectangle = new Rectangle(largura, altura, new RadialGradient(0, 0, 0.2, 0.3, 1, true, CycleMethod.NO_CYCLE, new Stop[]{
            new Stop(0, Color.rgb(250, 250, 255)),
            new Stop(1, color)
        }));
        
        rectangle.setId(Integer.toString(count));
        //add a shadow effect
        rectangle.setEffect(new InnerShadow(7, color.darker().darker()));
        //change a cursor when it is over circle
        rectangle.setCursor(Cursor.HAND);
        //add a mouse listeners
        rectangle.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                //the event will be passed only to the circle which is on front
                me.consume();
            }
        });
        rectangle.setOnMouseDragged(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                double dragX = me.getSceneX() - dragAnchor.getX();
                double dragY = me.getSceneY() - dragAnchor.getY();
                //calculate new position of the circle
                double newXPosition = initX + dragX;
                double newYPosition = initY + dragY;
                //if new position do not exceeds borders of the rectangle, translate to this position
                if ((newXPosition >= rectangle.getArcHeight()) && (newXPosition <= 500 - rectangle.getArcWidth())) {
                    rectangle.setTranslateX(newXPosition);
                }
                if ((newYPosition >= rectangle.getArcHeight()) && (newYPosition <= 300 - rectangle.getArcWidth())) {
                    rectangle.setTranslateY(newYPosition);
                }
            }
        });
        rectangle.setOnMouseEntered(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                //change the z-coordinate of the circle
                rectangle.toFront();
            }
        });
        rectangle.setOnMouseExited(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
            }
        });
        rectangle.setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                //when mouse is pressed, store initial position
                initX = rectangle.getTranslateX();
                initY = rectangle.getTranslateY();
                dragAnchor = new Ponto(me.getSceneX(), me.getSceneY());
                //showOnConsole("Mouse pressed above " + name);
            }
        });
        rectangle.setOnMouseReleased(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                //showOnConsole("Mouse released above " +name);
                salvar.setDisable(false);
                desenho.atualizaPosicao(Integer.parseInt(rectangle.getId()), new Ponto(me.getSceneX(), me.getSceneY()));
               
            }
        });
        count++;
        return rectangle;

    }

    private Line createLine(Ponto inicio, Ponto fim) {
        Line linha = new Line(inicio.getX(), inicio.getY(), fim.getX(), fim.getY());

        linha.setCursor(Cursor.HAND);

        linha.setOnMouseEntered(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                //change the z-coordinate of the circle
                linha.toFront();
            }
        });
        count++;
        return linha;
    }

    @FXML
    private void criaFigura(MouseEvent event) {

        if (figura.getValue() == "Círculo") {
            
            try {
                if(Double.parseDouble(raio.getText()) <= 0) {
                    mensagemErro("Erro", "Valor negativo para o raio.", "Informe um valor real positivo para o raio.");
                    return;
                } 
            } catch (Exception e) {
                mensagemErro("Erro", "Valor inválido para o raio.", "Informe um valor real positivo para o raio.");
            }
            Circle circulo = createCircle("Blue circle", cor.getValue(),
                    Double.parseDouble(raio.getText()));
            circulo.setTranslateX(event.getX());
            circulo.setTranslateY(event.getY());
            tela.getChildren().addAll(circulo);
            desenho.setFigura(Double.parseDouble(raio.getText()), new Ponto(event.getX(), event.getY()), cor.getValue().toString());
            shape.add(circulo);
            Double.parseDouble(raio.getText());

//            System.out.println(cor.getValue());
//            System.out.println(circulo.getFill());
        } else if (figura.getValue() == "Retângulo") {
            try {
                if(Double.parseDouble(altura.getText()) <= 0 || Double.parseDouble(largura.getText()) <= 0) {
                    mensagemErro("Erro", "Valor negativo para dimensões.", "Informe um valor real positivo para altura e largura.");
                    return;
                } 
            } catch (Exception e) {
                mensagemErro("Erro", "Valor inválido para as dimensões.", "Informe um valor real positivo para altura e largura.");
            }
            Rectangle retangulo = createRectangle("Blue rectangle", cor.getValue(), Double.parseDouble(largura.getText()), Double.parseDouble(altura.getText()));
            retangulo.setTranslateX(event.getX());
            retangulo.setTranslateY(event.getY());
            tela.getChildren().addAll(retangulo);
            desenho.setFigura(Double.parseDouble(altura.getText()), Double.parseDouble(altura.getText()), new Ponto(event.getX(), event.getY()), cor.getValue().toString());
            shape.add(retangulo);

        } else if (figura.getValue() == "Linha") {
            try {
                if(Double.parseDouble(coordenada_x.getText()) <= 0 || Double.parseDouble(coordenada_y.getText()) <= 0) {
                    mensagemErro("Erro", "Valor negativo para as coordenadas.", "Informe um valor real positivo para X e Y.");
                    return;
                } 
            } catch (Exception e) {
                mensagemErro("Erro", "Valor inválido para as coordenadas.", "Informe um valor real positivo para X e Y.");
            }
            Ponto inicio, fim;
            inicio = new Ponto(Double.parseDouble(coordenada_x.getText()), Double.parseDouble(coordenada_y.getText()));
            fim = new Ponto(event.getX(), event.getY());
            Line linha = createLine(inicio, fim);
            tela.getChildren().addAll(linha);
            desenho.setFigura(inicio, fim);
            shape.add(linha);
        }

        salvar.setDisable(false);
        remover.setDisable(false);
        //raio.setText(null);
        //coordenada_x.setText(null);
        //coordenada_y.setText(null);
        System.out.println("Adicionei um " + figura.getValue());
    }

    @FXML
    private void desabilaParams(MouseEvent event) {
        coordenada_x.setDisable(true);
        coordenada_y.setDisable(true);
        if (figura.getValue() == "Círculo") {
            raio.setDisable(false);
            altura.setDisable(true);
            largura.setDisable(true);
        } else if (figura.getValue() == "Retângulo") {
            raio.setDisable(true);
            altura.setDisable(false);
            largura.setDisable(false);
        }
        System.out.println(figura.getValue());
    }

    @FXML
    private void habilitaParams(MouseEvent event) {
        coordenada_x.setDisable(false);
        coordenada_y.setDisable(false);
        if (figura.getValue() == "Círculo") {
            raio.setDisable(false);
            altura.setDisable(true);
            largura.setDisable(true);
        } else if (figura.getValue() == "Retângulo") {
            raio.setDisable(true);
            altura.setDisable(false);
            largura.setDisable(false);
        }
    }

    private void carregaCenario(String filename) {
        
//        if(count > 0)
//           limparTela();
        //count = 0;
        ArrayList<Figuras> figs = Desenho.abrirDesenho(filename);

        desenho = new Desenho(filename);

        for (int i = 0; i < figs.size(); i++) {
            if (figs.get(i) instanceof Circulo) {
                Circle circulo = createCircle("Blue circle", Color.valueOf(figs.get(i).getColor()),
                        ((Circulo) figs.get(i)).getRaio());
//                System.out.println(figs.get(i).getColor());
                circulo.setTranslateX(figs.get(i).getPosicao().getX());
                circulo.setTranslateY(figs.get(i).getPosicao().getY());
                System.out.println(circulo.toString());
                tela.getChildren().addAll(circulo);
                desenho.setFigura(((Circulo) figs.get(i)).getRaio(), new Ponto(figs.get(i).getPosicao().getX(), figs.get(i).getPosicao().getY()), figs.get(i).getColor());
                shape.add(circulo);
            } else if (figs.get(i) instanceof Retangulo) {
                Rectangle retangulo = createRectangle("Blue rectangle", Color.valueOf(figs.get(i).getColor()), ((Retangulo) figs.get(i)).getAltura(), ((Retangulo) figs.get(i)).getLargura());
                retangulo.setTranslateX(figs.get(i).getPosicao().getX());
                retangulo.setTranslateY(figs.get(i).getPosicao().getY());
                tela.getChildren().addAll(retangulo);
                desenho.setFigura(((Retangulo) figs.get(i)).getAltura(), ((Retangulo) figs.get(i)).getLargura(), new Ponto(figs.get(i).getPosicao().getX(), figs.get(i).getPosicao().getY()), figs.get(i).getColor());
                shape.add(retangulo);
            } else if (figs.get(i) instanceof Linha) {
                Ponto inicio, fim;
                inicio = ((Linha) figs.get(i)).getInicio();
                fim = ((Linha) figs.get(i)).getFim();
                Line linha = createLine(inicio, fim);
                tela.getChildren().addAll(linha);
                desenho.setFigura(inicio, fim);
                shape.add(linha);
            } else {
                System.out.println("Entrei aqui errado.");
            }
        }

        tela.setDisable(false);
        remover.setDisable(false);
        novo.setDisable(false);
        figura.setDisable(false);
        carregaComboBoxFigura();
    }

    private void carregaComboBoxFigura() {
        figura.getItems().addAll(
                "Círculo",
                "Retângulo",
                "Linha"
        );
    }

    @FXML
    private void selecionaFigura() {
        cor.setDisable(false);
        coordenada_x.setDisable(false);
        coordenada_y.setDisable(false);
        tela.setDisable(false);
        if (figura.getValue() == "Círculo") {
            raio.setDisable(false);
            altura.setDisable(true);
            largura.setDisable(true);
        } else if (figura.getValue() == "Retângulo") {
            raio.setDisable(true);
            altura.setDisable(false);
            largura.setDisable(false);
        } else {
            coordenada_x.setDisable(true);
            coordenada_y.setDisable(true);
            raio.setDisable(true);
            altura.setDisable(true);
            largura.setDisable(true);
        }
    }

    @FXML
    private void criaLinha(MouseEvent event) {
        if (figura.getValue() == "Linha") {
            //Line linha = new Line();
            final double endX = event.getX();
            final double endY = event.getY();
            tela.setOnMousePressed(new EventHandler<MouseEvent>() {
                public void handle(MouseEvent me) {
                    //when mouse is pressed, store initial position

                    final double startX = event.getX();
                    final double startY = event.getY();
                    tela.getChildren().addAll(createLine(new Ponto(startX, startY), new Ponto(endX, endY)));
                    //linha.setEndY(event.getY());
                    //tela.getChildren().addAll(linha);
                    //showOnConsole("Mouse pressed above " + name);
                }
            });
//            final double endX = event.getX();
//            final double endY = event.getY();
//            linha.setStartX(event.getX());
//            linha.setStartY(event.getY());
//            tela.getChildren().addAll(linha);
            System.out.println("Fiz uma linha nova");
        }
    }

    private void limparTela() {
        for (int i = 0; i <= count; i++) {
            remover();
        }
    }

    private void mensagemErro(String titulo, String tipo_erro, String solucao) {

        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setHeaderText(tipo_erro);
        alert.setContentText(solucao);
        alert.showAndWait();
    }
    
    private void mensagemAlerta(String titulo, String tipo_erro, String solucao) {

        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle(titulo);
        alert.setHeaderText(tipo_erro);
        alert.setContentText(solucao);
        alert.showAndWait();
    }
    

}
