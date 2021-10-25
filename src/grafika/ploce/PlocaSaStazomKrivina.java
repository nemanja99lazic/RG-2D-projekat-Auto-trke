package grafika.ploce;

import javafx.scene.paint.Color;
import javafx.scene.shape.HLineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.QuadCurveTo;
import javafx.scene.shape.VLineTo;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;


public class PlocaSaStazomKrivina extends PlocaSaStazom 
{
    public static final int prva = 0;
    public static final int druga = 1;
    public static final int treca = 2;
    public static final int cetvrta = 3;

    private static Path getArc(double unutrasnjeR, double spoljasnjeR)
    {
        MoveTo m = new MoveTo(0, spoljasnjeR);
        QuadCurveTo c1 = new QuadCurveTo(spoljasnjeR, spoljasnjeR, spoljasnjeR, 0);
        HLineTo l1 = new HLineTo(unutrasnjeR);
        QuadCurveTo c2 = new QuadCurveTo(unutrasnjeR, unutrasnjeR, 0, unutrasnjeR);
        VLineTo l2 = new VLineTo(spoljasnjeR);

        Path p = new Path();
        p.getElements().addAll(m, c1, l1, c2, l2);

        return p;
    }

    public PlocaSaStazomKrivina(int red )
    {
            for(int i = 0; i < red; i++)
            {
                Translate t = new Translate(VELICINA, 0);
                Rotate r = new Rotate(90);
                koren.getTransforms().addAll(t, r);
            }

            double r = pomerajTrake();
            double R = r + sirinaTrake();
            
            Path p = getArc(r, R);
            p.setFill( bojaStaze );
            p.setStroke(null);

            double trakar = r + (R - r) * 0.45;
            double trakaR = r + (R - r) * 0.55;

            Path traka = getArc(trakar, trakaR);
            traka.setFill(Color.LIGHTGRAY);
            traka.setStroke(null);

            double gornjaIvicar = R;
            double gornjaIvicaR = gornjaIvicar + 0.05 * VELICINA;

            Path gornjaIvica = getArc(gornjaIvicar, gornjaIvicaR);
            gornjaIvica.setFill(Color.WHITE);
            gornjaIvica.setStroke(null);

            double donjaIvicar = r - 0.05 * VELICINA;
            double donjaIvicaR = r;

            Path donjaIvica = getArc(donjaIvicar, donjaIvicaR);
            donjaIvica.setFill(Color.WHITE);
            donjaIvica.setStroke(null);

            koren.getChildren().addAll(p, traka, gornjaIvica, donjaIvica);
    }
}
