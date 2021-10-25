package grafika.objekti;

import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;

public class SporoVozilo extends Formula{

    public SporoVozilo() {
        super(12, 3, 0.2);

        Path karoserija = new Path(
                new MoveTo(-20, -40),
                new HLineTo(20),
                new VLineTo(-25),
                new ArcTo(2, 5, 180, 20, -15, true, false),
                new VLineTo(15),
                new ArcTo(2, 5, 180, 20, 25, true, false),
                new VLineTo(35),
                new QuadCurveTo(18, 38, 10, 40),
                new HLineTo(-10),
                new QuadCurveTo(-18, 38, -20, 35),
                new VLineTo(25),
                new ArcTo(2, 5, 180, -20, 15, true, false),
                new VLineTo(-15),
                new ArcTo(2, 5, 180, -20, -25, true, false),
                new ClosePath()
        );

        Rectangle prednjiTockovi = new Rectangle(-23, 15, 46, 10);
        prednjiTockovi.setFill(Color.BLACK);

        Rectangle zadnjiTockovi = new Rectangle(-23, -25, 46, 10);
        zadnjiTockovi.setFill(Color.BLACK);

        Path leviFar = new Path(
                new MoveTo(-20, 35),
                new HLineTo(-10),
                new VLineTo(40),
                new QuadCurveTo(-18, 38, -20, 35),
                new ClosePath()
        );
        leviFar.setFill(Color.LIGHTYELLOW);

        Path desniFar = new Path(
                new MoveTo(20, 35),
                new QuadCurveTo(18, 38, 10, 40),
                new VLineTo(35),
                new HLineTo(20),
                new ClosePath()
        );
        desniFar.setFill(Color.LIGHTYELLOW);

        Rectangle sara = new Rectangle(- 5, 20, 10, 20);
        sara.setFill(Color.BLACK.brighter());

        Rectangle krov = new Rectangle(-15, -15, 30, 30);
        krov.setFill(Color.BLUE);

        Group spojevi = new Group(
                new Line(-19, -20, -15, -15),
                new Line(19, -20, 15, -15),
                new Line(15, 15, 19, 20),
                new Line(-15, 15, -19, 20)
        );

        Rectangle spojler = new Rectangle(-25, -40, 50, 10);
        spojler.setFill(Color.BLACK.brighter());

        karoserija.setFill(Color.DARKORANGE);

        getChildren().addAll(prednjiTockovi, zadnjiTockovi, karoserija, sara, krov, spojevi, leviFar, desniFar, spojler);
    }

    @Override
    protected void postaviPomerajeTockova()
    {
        pomerajTocka[0] = new Point2D(-23, 15);
        pomerajTocka[1] = new Point2D(23, 25);
        pomerajTocka[2] = new Point2D(-23, -25);
        pomerajTocka[3] = new Point2D(23, -15);
    }
}
