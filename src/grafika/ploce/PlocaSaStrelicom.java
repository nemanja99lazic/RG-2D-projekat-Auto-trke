package grafika.ploce;

import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;

public class PlocaSaStrelicom extends PlocaSaStazomRavna{

    public static final int DESNO = 1;
    public static final int DOLE = 2;
    public static final int LEVO = 3;
    public static final int GORE = 4;

    private Path strelica = null;

    public PlocaSaStrelicom(int orijentacija ) {
        super(orijentacija);
    }

    @Override
    protected void nacrtajTraku() {
        Path strelica = new Path();
        this.strelica = strelica;
        strelica.getElements().addAll(
                new MoveTo(0, 0.46 * VELICINA),
                new HLineTo(0.8 * VELICINA),
                new VLineTo(0.35 * VELICINA),
                new LineTo(0.99 * VELICINA, 0.5 * VELICINA),
                new LineTo(0.8 * VELICINA, 0.65 * VELICINA),
                new VLineTo(0.46 * VELICINA + 0.1 * sirinaTrake()),
                new HLineTo(0),
                new ClosePath()
        );
        strelica.setFill(Color.LIGHTGRAY);
        strelica.setStroke(null);
        koren.getChildren().addAll(strelica);
    }

    @Override
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
                strelica.setFill(Color.WHITE);
                break;
            case LEVO:
                t = new Translate(VELICINA, VELICINA);
                r = new Rotate(180);
                koren.getTransforms().addAll(t, r);
                strelica.setFill(Color.YELLOW);
                break;
            case GORE:
                t = new Translate(0, VELICINA);
                r = new Rotate(270);
                koren.getTransforms().addAll(t, r);
                break;
        }
    }
}
