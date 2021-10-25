package meni;

import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.transform.Translate;

public class MeniIzborStaze extends Meni{

    public static double SCENE_WIDTH = 900;
    public static double SCENE_HEIGHT = 700;
    public static int VELICINA_FONTA = 30;

    private Translate translacijaTrougla;

    public MeniIzborStaze()
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

        Text textStaza1 = new Text("Staza 1");
        Text textStaza2 = new Text("Staza 2");
        Text textStaza3 = new Text("Staza 3");

        textStaza1.setFont(Font.font(VELICINA_FONTA));
        textStaza1.getTransforms().add(
                new Translate(30, VELICINA / 3 - VELICINA_FONTA / 2)
        );

        textStaza2.setFont(Font.font(VELICINA_FONTA));
        textStaza2.getTransforms().add(
                new Translate(30, 2 * VELICINA / 3 - VELICINA_FONTA / 2)
        );

        textStaza3.setFont(Font.font(VELICINA_FONTA));
        textStaza3.getTransforms().add(
                new Translate(30, VELICINA - VELICINA_FONTA / 2)
        );

        koren.getChildren().addAll(pozadina, trougao, textStaza1, textStaza2, textStaza3);

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
