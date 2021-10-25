package grafika.ploce;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;

public class PlocaProsirenje extends PlocaSaStazom{

    public static final int DESNO = 1;
    public static final int DOLE = 2;
    public static final int LEVO = 3;
    public static final int GORE = 4;

    private double sirinaSiregDela = 6*VELICINA/8;
    private double pomerajSiregDela = VELICINA/8;

    private double DEBLJINA_IVICE = 0.05 * VELICINA;

    public PlocaProsirenje(int orijentacija) {

        Polyline gornjaIvica = new Polyline(new double[]{
                0.0, pomerajTrake() - DEBLJINA_IVICE / 2,
                VELICINA / 4, pomerajTrake() - DEBLJINA_IVICE / 2,
                VELICINA / 2, VELICINA / 8 - DEBLJINA_IVICE / 2,
                VELICINA, VELICINA / 8 - DEBLJINA_IVICE / 2
        });
        gornjaIvica.setStroke(Color.WHITE);
        gornjaIvica.setStrokeWidth(DEBLJINA_IVICE);

        Polyline donjaIvica = new Polyline(new double[]{
                0.0, VELICINA - pomerajTrake() + DEBLJINA_IVICE / 2,
                VELICINA / 4, VELICINA - pomerajTrake() + DEBLJINA_IVICE / 2,
                VELICINA / 2, 7 * VELICINA / 8 + DEBLJINA_IVICE / 2,
                VELICINA, 7 * VELICINA / 8 + DEBLJINA_IVICE / 2
        });
        donjaIvica.setStroke(Color.WHITE);
        donjaIvica.setStrokeWidth(DEBLJINA_IVICE);

        koren.getChildren().addAll(gornjaIvica, donjaIvica);

        nacrtajSivuStazu();
        nacrtajTraku();

        transformisiNaOsnovuOrijentacije(orijentacija);
    }

    protected void nacrtajSivuStazu()
    {
        Path staza = new Path(
                new MoveTo(0, pomerajTrake()),
                new HLineTo(VELICINA / 4),
                new LineTo(VELICINA / 2, VELICINA / 8),
                new HLineTo(VELICINA),
                new VLineTo(VELICINA - VELICINA /  8),
                new HLineTo(VELICINA / 2),
                new LineTo(VELICINA / 4, VELICINA - pomerajTrake()),
                new HLineTo(0),
                new ClosePath()
        );
        staza.setFill(Color.GRAY);
        staza.setStroke(null);
        koren.getChildren().add(staza);
    }

    protected void nacrtajTraku()
    {
        Rectangle traka = new Rectangle(VELICINA / 2, 0.46 * VELICINA, VELICINA / 2, 0.1 * 6 * VELICINA / 8);
        traka.setFill(Color.LIGHTGRAY);
        koren.getChildren().addAll(traka);
    }

    protected Polyline napraviIvicu()
    {
        return null;
    }

    protected void transformisiNaOsnovuOrijentacije(int orijentacija) {
        Translate t = null;
        Rotate r = null;

        switch (orijentacija)
        {
            case DESNO:
                break;
            case DOLE:
                t = new Translate(VELICINA, 0);
                r = new Rotate(90);
                koren.getTransforms().addAll(t, r);
                break;
            case LEVO:
                t = new Translate(VELICINA, VELICINA);
                r = new Rotate(180);
                koren.getTransforms().addAll(t, r);
                break;
            case GORE:
                t = new Translate(0, VELICINA);
                r = new Rotate(270);
                koren.getTransforms().addAll(t, r);
                break;
        }
    }

    public double sirinaTrake() {
        return super.sirinaTrake() / 2;
    }

    public double pomerajTrake()
    {
        return VELICINA / 2 - sirinaTrake() / 2;
    }

}
