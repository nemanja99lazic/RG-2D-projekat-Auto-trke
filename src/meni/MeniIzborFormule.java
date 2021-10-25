package meni;

import grafika.objekti.Formula;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.transform.Translate;

public class MeniIzborFormule extends Meni {
    public static double SCENE_WIDTH = 900;
    public static double SCENE_HEIGHT = 700;
    public static int VELICINA_FONTA = 30;

    private Translate translacijaTrougla;

    public MeniIzborFormule()
    {
        izbor = 1;

        Rectangle pozadina = new Rectangle( - SCENE_WIDTH / 2, - SCENE_HEIGHT / 2, SCENE_WIDTH + VELICINA / 2, SCENE_HEIGHT + VELICINA / 2);
        pozadina.setFill(Color.LIGHTYELLOW);

        Path trougao = new Path(
                new MoveTo(0, 0),
                new LineTo(15, 15),
                new LineTo(0, 30),
                new ClosePath()
        );
        trougao.setFill(Color.BLACK);
        translacijaTrougla = new Translate(0, 10);
        trougao.getTransforms().add(
                translacijaTrougla
        );

        Text textFormula1 = new Text("Formula");
        Text textFormula2 = new Text("Auto");
        Text textFormula3 = new Text("Oldtimer");

        textFormula1.setFont(Font.font(VELICINA_FONTA));
        textFormula1.getTransforms().add(
                new Translate(30, VELICINA / 3 - VELICINA_FONTA / 2)
        );

        textFormula2.setFont(Font.font(VELICINA_FONTA));
        textFormula2.getTransforms().add(
                new Translate(30, 2 * VELICINA / 3 - VELICINA_FONTA / 2)
        );

        textFormula3.setFont(Font.font(VELICINA_FONTA));
        textFormula3.getTransforms().add(
                new Translate(30, VELICINA - VELICINA_FONTA / 2)
        );

        koren.getChildren().addAll(pozadina, trougao, textFormula1, textFormula2, textFormula3);

        this.getChildren().add(koren);
        this.getTransforms().add(
                new Translate(SCENE_WIDTH / 2 - VELICINA / 2, SCENE_HEIGHT / 2 - VELICINA / 2)
        );

    }

    public void pomeri(int smer)
    {
        switch (smer)
        {
            case DOLE:
            {
                if(izbor == 3)
                    return;
                izbor++;
                translacijaTrougla.setY(translacijaTrougla.getY() + 50);
            }
            break;
            case GORE:
            {
                if(izbor == 1)
                    return;
                izbor--;
                translacijaTrougla.setY(translacijaTrougla.getY() - 50);
            }
            break;
            default:
                break;
        }
    }

    public int dohvatiIzbor()
    {
        return izbor;
    }
}
