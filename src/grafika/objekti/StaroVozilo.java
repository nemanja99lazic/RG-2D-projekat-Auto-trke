package grafika.objekti;

import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;

public class StaroVozilo extends Formula {

    public StaroVozilo() {
        super(20, 3, 0.2);

        Ellipse karoserija = new Ellipse(0, 0, 10, 30);
        karoserija.setFill(Color.SADDLEBROWN);

        Circle vozac = new Circle(0, -10, 3);
        vozac.setFill(Color.BLUE);

        Group zadnjiTockovi = new Group(
                new Rectangle(-15, -13, 3, 6),
                new Line(-12, -10, 12, - 10),
                new Rectangle(12, -13, 3, 6)
        );

        Group prednjiTockovi = new Group(
                new Rectangle(-15, 17, 3, 6),
                new Line(-12, 20, 12, 20),
                new Rectangle(12, 17, 3, 6)
        );

        Arc vetrobran = new Arc(0, -7, 5, 3, 0, -180);
        vetrobran.setFill(Color.RED);

        getChildren().addAll(zadnjiTockovi, prednjiTockovi, karoserija, vozac, vetrobran);
    }

    @Override
    protected void postaviPomerajeTockova()
    {
        pomerajTocka[0] = new Point2D(-15, -13);
        pomerajTocka[1] = new Point2D(12, -13);
        pomerajTocka[2] = new Point2D(-15, 17);
        pomerajTocka[3] = new Point2D(12, 17);
    }

}
