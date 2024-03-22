
package com.morpion.morpion;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import com.morpion.morpion.ai.Coup;
import com.morpion.morpion.ai.MultiLayerPerceptron;
import com.morpion.morpion.ai.SigmoidalTransferFunction;
import com.morpion.morpion.ai.Test;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Learn {


    @FXML
    private Label progresstext;
    @FXML
    private ProgressBar progressbar;
    @FXML
    private Button progressbtn;


    public void initialize() throws IOException {
        String cheminDuFichier = "./morpion/src/main/resources/com/morpion/morpion/config.txt";
        List<String> list = Files.lines(Paths.get(cheminDuFichier)).collect(Collectors.toList());
        String[] tab;
        String[] tab2;
        String[] tab3;
        tab = list.get(0).split(":");
        tab2 = list.get(1).split(":");
        tab3 = list.get(2).split(":");
        switch (Menu.difficulte){
            case "facile":
                learn(Integer.parseInt(tab[1]), Integer.parseInt(tab[2]), Double.parseDouble(tab[3]));
                break;
            case "moyen":
                learn(Integer.parseInt(tab2[1]), Integer.parseInt(tab2[2]), Double.parseDouble(tab2[3]));
                break;
            case "difficile":
                learn(Integer.parseInt(tab3[1]), Integer.parseInt(tab3[2]), Double.parseDouble(tab3[3]));
                break;
        }

    }

    @FXML
    void learn(int l, int h, double lr) {
        int size = 9;

        HashMap<Integer, Coup> mapTrain = Test.loadCoupsFromFile("./morpion/src/main/resources/com/morpion/morpion/train.txt");

        double epochs = 100000;

        //part 1
        System.out.println();
        System.out.println("START TRAINING ...");
        System.out.println();
        //
        //            int[] layers = new int[]{ size, 128, 128, size };
        int[] layers = new int[l+2];
        layers[0] = size ;
        for (int i = 0; i < l; i++) {
            layers[i+1] = h ;
        }
        layers[layers.length-1] = size ;
        //

        MultiLayerPerceptron net = new MultiLayerPerceptron(layers, lr, new SigmoidalTransferFunction());

        //part 2
        Task<Double> task = new Task<Double>() {

            @Override
            protected Double call() throws Exception {

                System.out.println(epochs);
                double bestError = 1000.0;
                double error = 0.0;
                Coup c = null;
                for (int i = 0; i < epochs; i++) {

                    c = null;
                    while (c == null)
                        c = mapTrain.get((int) (Math.round(Math.random() * mapTrain.size())));

                    System.out.println("while");

                    error += net.backPropagate(c.in, c.out);

                    System.out.println(error);
                    if (bestError > error && i != 0) {
                        bestError = error;

                    } else {
                        updateMessage("Error at step " + i + " is " + (error / (double) i));
                        System.out.println(bestError + " Error at step " + i + " is " + error);
                    }
                    System.out.println(i + "/" + epochs);
                    updateProgress(i, epochs);
                    //Thread.sleep(1);
                }
                System.out.println(c);
                //fermer le stage actuel



                net.save("./morpion/src/main/resources/model/model_"+l+"_"+h+"_"+lr+".srl");


                return error;
            }
        };
        progressbar.progressProperty().bind(task.progressProperty());
        //listener de la tache
        progresstext.textProperty().bind(task.messageProperty());
        /*task.messageProperty().addListener((obs, oldMessage, newMessage) -> {
            progresstext.setText(newMessage);
        });*/

        new Thread(task).start();

        //fermer le stage a la fin de la tache
        progressbar.progressProperty().addListener((obs, oldProgress, newProgress) -> {
            if (newProgress.doubleValue() >=0.99999) {
                Stage stage = (Stage) progressbar.getScene().getWindow();
                stage.close();
            }
        });



    }
}