package grafika.objekti;

import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;

public class IndikatorBrzine extends Group {

    public static double MAX_BRZINA = 20.0;
    public static double POLUKRUG_STEPENI = 180.0;

    private Arc crniLuk;
    private Arc crveniLuk;
    private Line kazaljka;

    Rotate rotacijaKazaljke;

    private Formula formula;

    public IndikatorBrzine(Formula f)
    {
        this.formula = f;

        crniLuk = new Arc(0, 0, 50, 50, 180, -150);
        crniLuk.setStrokeWidth(5);
        crniLuk.setType(ArcType.OPEN);
        crniLuk.setFill(null);
        crniLuk.setStroke(Color.BLACK);

        crveniLuk = new Arc(0, 0, 50, 50, 30, -30);
        crveniLuk.setStrokeWidth(5);
        crveniLuk.setType(ArcType.OPEN);
        crveniLuk.setFill(null);
        crveniLuk.setStroke(Color.RED);

        kazaljka = new Line(0, 0, -40, 0);
        kazaljka.setStrokeWidth(5);
        rotacijaKazaljke = new Rotate(0);
        kazaljka.getTransforms().add(rotacijaKazaljke);

        this.getChildren().addAll(crniLuk, crveniLuk, kazaljka);

        getTransforms().addAll(
                new Translate(80, 80)
        );
    }

    public void reagujNaPromenuBrzine()
    {
        double ugao = formula.brzina().intenzitet() * POLUKRUG_STEPENI / MAX_BRZINA;
        rotacijaKazaljke.setAngle(ugao);
    }

}
