package com.morpion.morpion;

import javafx.concurrent.Task;
import javafx.fxml.FXML;

import java.util.HashMap;

public class Learn {




    int size = 9 ;
    int l = 2 ;
    int h = 128 ;
    double epochs = 1000 ;
    double lr = 0.1 ;
    boolean verbose = true ;
    HashMap<Integer, Coup> mapTrain = new HashMap<Integer, Coup>() ;
    @FXML
    private MultiLayerPerceptron learn() {
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
        double error = 0.0 ;
        MultiLayerPerceptron net = new MultiLayerPerceptron(layers, lr, new SigmoidalTransferFunction());

        //part 2
        Task<Double> task = new Task<Double>() {

            @Override
            protected Double call() throws Exception {
                for(int i = 0; i < epochs; i++){

                    Coup c = null ;
                    while ( c == null )
                        c = mapTrain.get((int)(Math.round(Math.random() * mapTrain.size())));

                    error += net.backPropagate(c.in, c.out);

                    if ( i % 10 == 0 && verbose) System.out.println("Error at step "+i+" is "+ (error/(double)i));
                }
                if ( verbose )
                    System.out.println("Learning completed!");

                return net ;
                return null;
    }
}
