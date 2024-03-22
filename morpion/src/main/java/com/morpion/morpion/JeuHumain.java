package com.morpion.morpion;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.ImagePattern;

public class JeuHumain {

    @FXML
    Button case1;
    @FXML
    Button case2;
    @FXML
    Button case3;
    @FXML
    Button case4;
    @FXML
    Button case5;
    @FXML
    Button case6;
    @FXML
    Button case7;
    @FXML
    Button case8;
    @FXML
    Button case9;
    @FXML
    Line V1;
    @FXML
    Line V2;
    @FXML
    Line V3;
    @FXML
    Line H1;
    @FXML
    Line H2;
    @FXML
    Line H3;
    @FXML
    Line I1;
    @FXML
    Line I2;


    @FXML
    Label J1;
    @FXML
    Label J2;

    @FXML
    TextField Joueur1;
    @FXML
    TextField Joueur2;
    @FXML
    Label VictoireTxt;


    Boolean tour = true;
    int partie = -1;
    int nbCoups = 0;

    public enum EtatCase {
        VIDE, CROIX, CERCLE
    }

    private EtatCase[] etatsCases = new EtatCase[9];

    public void initialize() {
        J1.setGraphic(new ImageView(new Image("file:morpion/src/main/resources/image/humain.png")));
        J2.setGraphic(new ImageView(new Image("file:morpion/src/main/resources/image/humain.png")));


    }

    @FXML
    private void rejouer() {
        case1.setGraphic(null);
        case2.setGraphic(null);
        case3.setGraphic(null);
        case4.setGraphic(null);
        case5.setGraphic(null);
        case6.setGraphic(null);
        case7.setGraphic(null);
        case8.setGraphic(null);
        case9.setGraphic(null);
        case1.setOnAction(e -> case1());
        case2.setOnAction(e -> case2());
        case3.setOnAction(e -> case3());
        case4.setOnAction(e -> case4());
        case5.setOnAction(e -> case5());
        case6.setOnAction(e -> case6());
        case7.setOnAction(e -> case7());
        case8.setOnAction(e -> case8());
        case9.setOnAction(e -> case9());
        for(int i = 0; i < 9; i++) {
            etatsCases[i] = EtatCase.VIDE;
        }
        tour = true;
        partie = -1;
        nbCoups = 0;
        J1.getStyleClass().remove("icongris");
        J1.getStyleClass().add("icon");
        J2.getStyleClass().remove("icongris");
        J2.getStyleClass().add("icongris");
        V1.setVisible(false);
        V2.setVisible(false);
        V3.setVisible(false);
        H1.setVisible(false);
        H2.setVisible(false);
        H3.setVisible(false);
        I1.setVisible(false);
        I2.setVisible(false);
        VictoireTxt.setOpacity(0);

    }

    @FXML
    private void jouer(Button b) {
        if(partie == -1) {
            if (tour) {
                b.setGraphic(new ImageView(new Image("file:morpion/src/main/resources/image/croix.png")));
                ImageView imageView = (ImageView) b.getGraphic();
                imageView.setFitHeight(100);
                imageView.setFitWidth(100);
                b.setOnAction(null);
                J1.getStyleClass().add("icongris");
                J2.getStyleClass().remove("icongris");
                J2.getStyleClass().add("icon");
            } else {
                b.setGraphic(new ImageView(new Image("file:morpion/src/main/resources/image/rond.png")));
                ImageView imageView = (ImageView) b.getGraphic();
                imageView.setFitHeight(100);
                imageView.setFitWidth(100);
                b.setOnAction(null);
                J1.getStyleClass().add("icon");
                J2.getStyleClass().add("icongris");
                J1.getStyleClass().remove("icongris");
            }
            tour = !tour;
            nbCoups++;
            if (nbCoups >= 5) {
                if (croixGagne()) {
                    partie = 1;
                    VictoireTxt.setText("Victoire de " + Joueur1.getText());
                    VictoireTxt.setOpacity(1);
                } else if (rondGagne()) {
                    partie = 2;
                    VictoireTxt.setText("Victoire de " + Joueur2.getText());
                    VictoireTxt.setOpacity(1);
                }
            }
            if(nbCoups == 9 && partie == -1) {
                partie = 0;
                VictoireTxt.setText("Match nul");
                VictoireTxt.setOpacity(1);

            }
        }
    }

    private boolean croixGagne() {
        if(etatsCases[1-1]==EtatCase.CROIX && etatsCases[2-1]==EtatCase.CROIX && etatsCases[3-1]==EtatCase.CROIX) {
            V1.setVisible(true);
            return true;
        } else if(etatsCases[4-1]==EtatCase.CROIX && etatsCases[5-1]==EtatCase.CROIX && etatsCases[6-1]==EtatCase.CROIX) {
            V2.setVisible(true);
            return true;
        } else if(etatsCases[7-1]==EtatCase.CROIX && etatsCases[8-1]==EtatCase.CROIX && etatsCases[9-1]==EtatCase.CROIX) {
            V3.setVisible(true);
            return true;
        } else if(etatsCases[1-1]==EtatCase.CROIX && etatsCases[4-1]==EtatCase.CROIX && etatsCases[7-1]==EtatCase.CROIX) {
            H1.setVisible(true);
            return true;
        } else if(etatsCases[2-1]==EtatCase.CROIX && etatsCases[5-1]==EtatCase.CROIX && etatsCases[8-1]==EtatCase.CROIX) {
            H2.setVisible(true);
            return true;
        } else if(etatsCases[3-1]==EtatCase.CROIX && etatsCases[6-1]==EtatCase.CROIX && etatsCases[9-1]==EtatCase.CROIX) {
            H3.setVisible(true);
            return true;
        } else if(etatsCases[1-1]==EtatCase.CROIX && etatsCases[5-1]==EtatCase.CROIX && etatsCases[9-1]==EtatCase.CROIX) {
            I1.setVisible(true);
            return true;
        } else if(etatsCases[3-1]==EtatCase.CROIX && etatsCases[5-1]==EtatCase.CROIX && etatsCases[7-1]==EtatCase.CROIX) {
            I2.setVisible(true);
            return true;
        }
        return false;
    }

    private boolean rondGagne() {
        if(etatsCases[1-1]==EtatCase.CERCLE && etatsCases[2-1]==EtatCase.CERCLE && etatsCases[3-1]==EtatCase.CERCLE) {
            V1.setVisible(true);
            return true;
        } else if(etatsCases[4-1]==EtatCase.CERCLE && etatsCases[5-1]==EtatCase.CERCLE && etatsCases[6-1]==EtatCase.CERCLE) {
            V2.setVisible(true);
            return true;
        } else if(etatsCases[7-1]==EtatCase.CERCLE && etatsCases[8-1]==EtatCase.CERCLE && etatsCases[9-1]==EtatCase.CERCLE) {
            V3.setVisible(true);
            return true;
        } else if(etatsCases[1-1]==EtatCase.CERCLE && etatsCases[4-1]==EtatCase.CERCLE && etatsCases[7-1]==EtatCase.CERCLE) {
            H1.setVisible(true);
            return true;
        } else if(etatsCases[2-1]==EtatCase.CERCLE && etatsCases[5-1]==EtatCase.CERCLE && etatsCases[8-1]==EtatCase.CERCLE) {
            H2.setVisible(true);
            return true;
        } else if(etatsCases[3-1]==EtatCase.CERCLE && etatsCases[6-1]==EtatCase.CERCLE && etatsCases[9-1]==EtatCase.CERCLE) {
            H3.setVisible(true);
            return true;
        } else if(etatsCases[1-1]==EtatCase.CERCLE && etatsCases[5-1]==EtatCase.CERCLE && etatsCases[9-1]==EtatCase.CERCLE) {
            I1.setVisible(true);
            return true;
        } else if(etatsCases[3-1]==EtatCase.CERCLE && etatsCases[5-1]==EtatCase.CERCLE && etatsCases[7-1]==EtatCase.CERCLE) {
            I2.setVisible(true);
            return true;
        }
        return false;
    }

    @FXML
    private void case1() {
        if(tour) {
            etatsCases[0] = EtatCase.CROIX;
        } else {
            etatsCases[0] = EtatCase.CERCLE;
        }
        jouer(case1);


    }

    @FXML
    private void case2() {
        if(tour) {
            etatsCases[1] = EtatCase.CROIX;
        } else {
            etatsCases[1] = EtatCase.CERCLE;
        }
        jouer(case2);

    }

    @FXML
    private void case3() {

        if(tour) {
            etatsCases[2] = EtatCase.CROIX;
        } else {
            etatsCases[2] = EtatCase.CERCLE;
        }
        jouer(case3);

    }

    @FXML
    private void case4() {
        if(tour) {
            etatsCases[3] = EtatCase.CROIX;
        } else {
            etatsCases[3] = EtatCase.CERCLE;
        }
        jouer(case4);

    }

    @FXML
    private void case5() {
        if(tour) {
            etatsCases[4] = EtatCase.CROIX;
        } else {
            etatsCases[4] = EtatCase.CERCLE;
        }
        jouer(case5);

    }

    @FXML
    private void case6() {
        if(tour) {
            etatsCases[5] = EtatCase.CROIX;
        } else {
            etatsCases[5] = EtatCase.CERCLE;
        }
        jouer(case6);
    }

    @FXML
    private void case7() {
        if(tour) {
            etatsCases[6] = EtatCase.CROIX;
        } else {
            etatsCases[6] = EtatCase.CERCLE;
        }
        jouer(case7);


    }

    @FXML
    private void case8() {
        if(tour) {
            etatsCases[7] = EtatCase.CROIX;
        } else {
            etatsCases[7] = EtatCase.CERCLE;
        }

        jouer(case8);
    }

    @FXML
    private void case9() {
        if(tour) {
            etatsCases[8] = EtatCase.CROIX;
        } else {
            etatsCases[8] = EtatCase.CERCLE;
        }
        jouer(case9);

    }

}
