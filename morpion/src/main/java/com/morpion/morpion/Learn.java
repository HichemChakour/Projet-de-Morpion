package com.morpion.morpion;

import java.io.IOException;
import java.util.HashMap;

import com.morpion.morpion.ai.Coup;
import com.morpion.morpion.ai.MultiLayerPerceptron;
import com.morpion.morpion.ai.SigmoidalTransferFunction;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Learn {


    @FXML
    private TextField progresstext;
    @FXML
    private ProgressBar progressbar;
    @FXML
    private Button progressbtn;



    @FXML
    void learn() {

        int size = 9;

        HashMap<Integer, Coup> mapTrain = new HashMap<Integer, Coup>();

        int h = 128;

        double lr = 0.1;

        int l = 2;

        boolean verbose = true;

        double epochs = 1000;

        //part 1
        if ( verbose ) {
            System.out.println();
            System.out.println("START TRAINING ...");
            System.out.println();
        }
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
                double error = 0.0 ;
                for(int i = 0; i < epochs; i++){

                    Coup c = null ;
                    while ( c == null )
                        c = mapTrain.get((int)(Math.round(Math.random() * mapTrain.size())));

                    error += net.backPropagate(c.in, c.out);

                    if ( i % 10 == 0 && verbose) updateMessage("Error at step "+i+" is "+ (error/(double)i));
                    updateProgress(i, epochs);
                }

                return error ;
            }
        };
        progressbar.setProgress(task.getProgress());

        progresstext.textProperty().bind(task.messageProperty());
        new Thread(task).start();
    }
}
