package com.mycompany.taller4;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.*;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {

    Lista lista = new Lista().LeerDoc();
    TableView table = new TableView();
    PieChart circ = new PieChart();

    @Override
    public void start(Stage stage) {
        var canva = new VBox();
        var Sect1 = new HBox();
        var menu = new MenuBar();
        var m1 = new Menu("Agregar");
        var m2 = new Menu("Informes");
        var m3 = new Menu("Eliminar");
        var m4 = new Menu("Guardar");
        var mi1 = new MenuItem("Agregar Al Final");
        var mi2 = new MenuItem("Agregar Al Principio");
        var mi3 = new MenuItem("Agregar Al Despues de Id");
        var mi4 = new MenuItem("Eliminar niño");
        var mi5 = new MenuItem("Matriculados en la Institucion");
        var mi6 = new MenuItem("Matriculados en Prejardin");
        var mi7 = new MenuItem("Matriculados en Jardin");
        var mi8 = new MenuItem("Promedio de edades");
        var mi9 = new MenuItem("Guardar Avance");
        m1.getItems().addAll(mi1, mi2, mi3);
        m2.getItems().addAll(mi5, mi6, mi7, mi8);
        m3.getItems().addAll(mi4);
        m4.getItems().addAll(mi9);
        menu.getMenus().addAll(m1, m2, m3, m4);
        Sect1.getChildren().addAll(circ, table);
        canva.getChildren().addAll(menu, Sect1);
        mi6.setOnAction(e -> {
            this.informe(lista.ListasPorGrado()[0]);
        });
        mi4.setOnAction(e -> {
            this.eliminar();
        });
        mi1.setOnAction(e -> {
            this.agregar(0);
        });
        mi2.setOnAction(e -> {
            this.agregar(1);
        });
        mi3.setOnAction(e -> {
            this.agregar(2);
        });

//        lista.addFinal("111","Adrian", "HOMBRE", 4, "jardin");
//        lista.addFinal("222","Danna", "Mujer", 7, "jardin");
//        lista.addFinal("333","Jose", "Hombre", 5, "Prejardin");
//        lista.guardarRegistro(lista.InfoLista());
        System.out.println(lista.getnChil());
//        lista=lista.LeerDoc();
//        System.out.println(lista.InfoLista());
//        lista.guardarRegistro(lista.InfoLista());
        this.initable();
        this.initCirc();
        this.actualizarCircu();
        var scene = new Scene(canva);
        stage.setScene(scene);
        stage.show();

    }

    public void initable() {
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        VBox.setVgrow(table, Priority.ALWAYS);

        TableColumn<Children, String> col1 = new TableColumn<>("id");
        col1.setCellValueFactory(new PropertyValueFactory<>("id"));
        TableColumn<Children, String> col2 = new TableColumn<>("Nombre");
        col2.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        TableColumn<Children, String> col3 = new TableColumn<>("Sexo");
        col3.setCellValueFactory(new PropertyValueFactory<>("sexo"));
        TableColumn<Children, String> col4 = new TableColumn<>("Edad");
        col4.setCellValueFactory(new PropertyValueFactory<>("edad"));
        TableColumn<Children, String> col5 = new TableColumn<>("Grado");
        col5.setCellValueFactory(new PropertyValueFactory<>("grado"));

        table.getColumns().addAll(col1, col2, col3, col4, col5);

        this.actualizarTable();
        this.actualizarCircu();
    }

    public static void main(String[] args) {
        launch();
    }

    private void actualizarTable() {
        if (lista.nChil != 0) {
            for (int i = 0; i < table.getItems().size(); i++) {
                table.getItems().clear();
            }
            if (lista.nChil == 1) {
                table.getItems().add(lista.getCab());
            } else {
                Children i = lista.getCab();
                while (!i.sig.getId().equals(lista.cab.getId())) {
                    table.getItems().add(i);
//                    System.out.println(i.getNombre());
                    i = i.getSig();
                }
                table.getItems().add(i);
            }
        }
        this.actualizarCircu();
    }

    private void initCirc() {

        int[] cant;
//        System.out.println("Hombre " + lista.ListasPorGenero(lista)[0].getnChil());
        var a = new PieChart.Data("Hombres" + lista.ListasPorGenero(lista)[0].getnChil(), lista.ListasPorGenero(lista)[0].getnChil());
        var b = new PieChart.Data("Mujeres" + lista.ListasPorGenero(lista)[1].getnChil(), lista.ListasPorGenero(lista)[1].getnChil());
        circ.getData().addAll(a, b);
//        circ.setMinWidth(50);
//        circ.setMinHeight(50);
    }

    private void actualizarCircu() {

        var a = new PieChart.Data("Hombres" + lista.ListasPorGenero(lista)[0].getnChil(), lista.ListasPorGenero(lista)[0].getnChil());
        var b = new PieChart.Data("Mujeres" + lista.ListasPorGenero(lista)[1].getnChil(), lista.ListasPorGenero(lista)[1].getnChil());
        ObservableList<PieChart.Data> res = FXCollections.observableArrayList(a, b);
        circ.setData(res);
//        System.out.println(res.toString());
    }

    private void agregar(int num) {
        var idr = new TextInputDialog();
        if (num == 2) {
            idr.setContentText("ID de Referencia: ");
            idr.showAndWait();
            if (idr.getResult().isEmpty()) {
                return;
            }
        }
        var sg = new Stage();
        var canva = new VBox();
//        va
        canva.setSpacing(10);
        canva.setPadding(new Insets(10));
        var lb1 = new Label("id");
        var lb2 = new Label("Nombre");
        var lb3 = new Label("Sexo");
        var lb4 = new Label("Edad");
        var lb5 = new Label("Grado");
        //inputs
        var tf1 = new TextField();
        var tf2 = new TextField();
        var tf3 = new ComboBox();
        var tf4 = new TextField();
        var tf5 = new ComboBox();
        ObservableList<String> Items = FXCollections.observableArrayList();
        Items.addAll("HOMBRE", "MUJER");
        tf3.setValue("HOMBRE");
        tf3.setItems(Items);
        Items = FXCollections.observableArrayList();
        Items.addAll("JARDIN", "PREJARDIN");
        tf5.setItems(Items);
        tf5.setValue("JARDIN");

        var btn1 = new Button("Agregar");
        var btn2 = new Button("Cancelar");
        btn2.setOnAction(e -> {
            sg.close();
        });
        btn1.setOnAction(e -> {
            try {
                if (num == 0) {
                    lista.addFinal(tf1.getText(), tf2.getText(), (String) tf3.getValue(), Integer.valueOf(tf4.getText()), (String) tf5.getValue());
                    this.actualizarTable();
                    tf1.setText("");
                    tf2.setText("");
                    tf4.setText("");
                } else if (num == 1) {
                    lista.addPrin(tf1.getText(), tf2.getText(), (String) tf3.getValue(), Integer.valueOf(tf4.getText()), (String) tf5.getValue());
                    this.actualizarTable();
                    tf1.setText("");
                    tf2.setText("");
                    tf4.setText("");
                } else if (num == 2) {
                    lista.addDespid(tf1.getText(), tf2.getText(), (String) tf3.getValue(), Integer.valueOf(tf4.getText()), (String) tf5.getValue(), (String) idr.getResult());
                    this.actualizarTable();
                    tf1.setText("");
                    tf2.setText("");
                    tf4.setText("");
                }
            } catch (Exception ex) {
                var a = new Alert(Alert.AlertType.ERROR);
                a.setContentText("Asegurese que los datos son permitidos");
                a.show();
            }

        });
        canva.getChildren().addAll(lb1, tf1, lb2, tf2, lb3, tf3, lb4, tf4, lb5, tf5, btn1, btn2);
        Scene sc = new Scene(canva);
        sg.setScene(sc);
        sg.show();

    }

    private void eliminar() {
        var res = new TextInputDialog();
        res.setContentText("Digita el  ID de referencia");
        res.setTitle("Eliminar Niño");
        res.showAndWait();
        try {
            lista.Eliminar((String) res.getResult());
            this.actualizarTable();
        } catch (Exception e) {

        }

    }

    private void informe(Lista li) {
        Stage sg = new Stage();
        var canva = new HBox();
        var graf= new PieChart();
        System.out.println(1);
        var a = new PieChart.Data("Hombres "+lista.ListasPorGenero(li)[0].getnChil(), lista.ListasPorGenero(li)[0].getnChil());
            System.out.println(2);
        var b = new PieChart.Data("Mujeres "+lista.ListasPorGenero(li)[1].getnChil(), lista.ListasPorGenero(li)[1].getnChil());
            System.out.println(3);
        graf.getData().addAll(a,b);
//        var sect1 = new HBox();
        TableView tab1 = new TableView();
        tab1.getColumns().addAll(table.getColumns());
        System.out.println("numero de niños"+li.getnChil());
        if (li.getnChil() != 0) {
            
            var aux = li.getCab();
            while (!aux.sig.getId().equals(aux.getId())) {
                tab1.getItems().addAll(aux);
                aux = aux.sig;
            }
            tab1.getItems().addAll(aux);
        }
        canva.getChildren().addAll(graf,tab1);
        var sc = new Scene(canva);
        sg.setScene(sc);
        sg.show();
         System.out.println(4);
    }

}