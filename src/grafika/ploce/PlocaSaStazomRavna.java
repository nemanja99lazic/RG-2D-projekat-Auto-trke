package grafika.ploce;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;

public class PlocaSaStazomRavna extends PlocaSaStazom 
{
    public static final int Horizontalna = 1;
    public static final int Vertikalna = 2;

    protected Canvas napraviIvicu()
    {
        Canvas ivica = new Canvas(VELICINA, 0.05 * VELICINA);
        GraphicsContext contextIvica = ivica.getGraphicsContext2D();
        int brojPravougaonika = 6;
        for(int i = 0; i < brojPravougaonika; i++)
        {

            if(i % 2 == 0) // beli pravougaonik
                contextIvica.setFill(Color.WHITE);
            else
                contextIvica.setFill(Color.RED);
            contextIvica.fillRect(i * VELICINA / 6.0, 0, VELICINA / 6, 0.05 * VELICINA);
        }

        return ivica;

    }

    public PlocaSaStazomRavna(){

    }

    public PlocaSaStazomRavna(int orijentacija )
    {
        nacrtajSivuStazu();

        /*final int n = 4;
        for(int i = 0; i < n; i++)
        {
            if(i % 2 == 0)
            {
                Rectangle traka = new Rectangle(0 + VELICINA / n * i, pomerajTrake() + 0.45 * sirinaTrake(), VELICINA / n, 0.1 * sirinaTrake());
                traka.setFill(Color.LIGHTGRAY);
                koren.getChildren().addAll(traka);
            }
        }*/

        nacrtajTraku();

        Canvas gornjaIvica = napraviIvicu();
        if(gornjaIvica != null) {
            gornjaIvica.getTransforms().add(
                    new Translate(0, (VELICINA - sirinaTrake()) / 2 - 0.05 * VELICINA)
            );
            koren.getChildren().addAll(gornjaIvica);
        }

        Canvas donjaIvica = napraviIvicu();
        if(donjaIvica != null) {
            donjaIvica.getTransforms().add(
                    new Translate(0, (VELICINA - sirinaTrake()) / 2 + sirinaTrake())
            );
            koren.getChildren().addAll(donjaIvica);
        }
        transformisiNaOsnovuOrijentacije(orijentacija);
    }

    protected void nacrtajSivuStazu()
    {
        Rectangle staza = new Rectangle(0, pomerajTrake(), VELICINA, sirinaTrake());
        staza.setFill(bojaStaze);
        staza.setStroke(null);
        koren.getChildren().add(staza);
    }

    protected void nacrtajTraku()
    {
        Rectangle traka = new Rectangle(0, 0.46 * VELICINA, VELICINA, 0.1 * sirinaTrake());
        traka.setFill(Color.LIGHTGRAY);
        koren.getChildren().addAll(traka);
    }

    protected void transformisiNaOsnovuOrijentacije(int orijentacija)
    {
        switch(orijentacija) {
            case Horizontalna:
                break;
            case Vertikalna:
                Translate t = new Translate(VELICINA, 0);
                Rotate r = new Rotate(90);
                koren.getTransforms().addAll(t, r);
                break;
        }
    }
}
