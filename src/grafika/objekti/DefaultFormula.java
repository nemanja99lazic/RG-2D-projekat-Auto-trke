package grafika.objekti;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;

public class DefaultFormula extends Formula{

    public DefaultFormula()
    {
        super(15, 2, 0.1);

        Path karoserija = new Path(
                new MoveTo(12, -25),
                new VLineTo(3),
                new LineTo(6,6),
                new VLineTo(18),
                new HLineTo(12),
                new VLineTo(24),
                new HLineTo(-12),
                new VLineTo(18),
                new HLineTo(-6),
                new VLineTo(9),
                new LineTo(-12, 3),
                new VLineTo(-25),
                new ClosePath()
        );
        karoserija.setFill(Color.RED);

        Rectangle zadnjiTockovi = new Rectangle(-20, -25, 40, 12); // gornje levo teme
        zadnjiTockovi.setFill(Color.BLACK);

        Rectangle prednjiTockovi = new Rectangle(-9, 9, 18, 7);
        prednjiTockovi.setFill(Color.BLACK);

        Rectangle spojler = new Rectangle(-17, -27, 34, 10);
        spojler.setFill(Color.YELLOW);

        Circle vozac = new Circle(3);
        vozac.setFill(Color.BLUE);

        Arc vetrobran = new Arc(0, 4, 5, 3, 0, -180);
        vetrobran.setFill(Color.BROWN);

        getChildren().addAll(zadnjiTockovi, prednjiTockovi, karoserija, spojler, vozac, vetrobran);
    }

    @Override
    protected void postaviPomerajeTockova()
    {
        pomerajTocka[0] = new Point2D(-20, -25);
        pomerajTocka[1] = new Point2D(20, -13);
        pomerajTocka[2] = new Point2D(-9, 9);
        pomerajTocka[3] = new Point2D(9, 16);
    }

}
