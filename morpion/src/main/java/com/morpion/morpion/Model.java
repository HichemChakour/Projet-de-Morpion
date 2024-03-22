package com.morpion.morpion;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.util.Callback;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Model {
    @FXML
    private ListView<FichierModel> list = new ListView<>();

    private Path DossierModel = Paths.get("./morpion/src/main/resources/model");

    public void initialize() {
        File dossier = DossierModel.toFile();
        File[] listeDesFichiers = dossier.listFiles();

        if (listeDesFichiers != null) {


            for (File fichier : listeDesFichiers) {
                if (fichier.isFile()) {
                    // Ici, l'ID pourrait être n'importe quelle chaîne unique. Utilisez peut-être le nom du fichier si c'est suffisant.
                    FichierModel fichierModel = new FichierModel(fichier.getName(), fichier.getName());
                    list.getItems().add(fichierModel);
                }
            }
        }

        list.setCellFactory(new Callback<ListView<FichierModel>, ListCell<FichierModel>>() {
            @Override
            public ListCell<FichierModel> call(ListView<FichierModel> param) {
                return new ListCell<FichierModel>() {
                    @Override
                    protected void updateItem(FichierModel item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty || item == null) {
                            setGraphic(null);
                        } else {
                            HBox hBox = new HBox(10);
                            Label label = new Label(item.getNom());
                            CheckBox checkBox = new CheckBox();
                            checkBox.selectedProperty().bindBidirectional(item.cocheProperty());
                            hBox.getChildren().addAll(label, checkBox);
                            setGraphic(hBox);
                        }
                    }
                };
            }
        });
    }

    public class FichierModel {
        private String nom;
        private String id;
        private BooleanProperty coche = new SimpleBooleanProperty();


        public FichierModel(String nom, String id) {
            this.nom = nom;
            this.id = id;
        }

        public String getNom() {
            return nom;
        }

        public String getId() {
            return id;
        }

        public BooleanProperty cocheProperty() {
            return coche;
        }

    }

    @FXML
    private void delete() {
        System.out.println(list.getItems());
        //suppriemr par rapport au case coché
        for (int i = 0; i < list.getItems().size(); i++) {
            if (list.getItems().get(i).cocheProperty().get()) {
                //supprimer le fichier
                File fichier = new File(DossierModel + "/" + list.getItems().get(i).getNom());
                fichier.delete();
                list.getItems().remove(i);
                i--;
            }
        }
    }
}


