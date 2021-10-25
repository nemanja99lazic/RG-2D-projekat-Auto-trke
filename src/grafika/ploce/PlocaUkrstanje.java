package grafika.ploce;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class PlocaUkrstanje extends PlocaSaStazomRavna{

    public PlocaUkrstanje(int orijentacija) {
        Rectangle stazaVertikalno = new Rectangle(0, pomerajTrake(), VELICINA, sirinaTrake());
        Rectangle stazaHorizontalno = new Rectangle(pomerajTrake(), 0, sirinaTrake(), VELICINA);
        Shape staza = Shape.union(stazaVertikalno, stazaHorizontalno);
        staza.setFill(bojaStaze);
        staza.setStroke(null);
        koren.getChildren().add(staza);

        Rectangle trakaHorizontalna = new Rectangle(0, 0.46 * VELICINA, VELICINA, 0.1 * sirinaTrake());
        trakaHorizontalna.setFill(Color.LIGHTGRAY);
        koren.getChildren().addAll(trakaHorizontalna);

        Rectangle trakaVertikalna = new Rectangle(0.46 * VELICINA, 0, 0.1 * sirinaTrake(), VELICINA);
        trakaVertikalna.setFill(Color.LIGHTGRAY);
        koren.getChildren().addAll(trakaVertikalna);
    }

}
