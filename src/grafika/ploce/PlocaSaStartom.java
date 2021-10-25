package grafika.ploce;

import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;

public class PlocaSaStartom extends PlocaSaStazomRavna{

    public PlocaSaStartom(int orijentacija) {
        super(orijentacija);

        int DIM_KVADRATA_STARTA = (int) (0.1 * this.sirinaTrake());
        int pomeraj =  (int)((VELICINA - sirinaTrake()) / 2);

        Canvas startOznaka = new Canvas(4 * DIM_KVADRATA_STARTA, pomeraj + this.sirinaTrake());
        GraphicsContext graphicsContext = startOznaka.getGraphicsContext2D();
        graphicsContext.setFill(Color.LIGHTGRAY);

        for(int i = 0; pomeraj + i * DIM_KVADRATA_STARTA <= sirinaTrake(); i++)
        {
            if(i % 2 == 0)
            {
                graphicsContext.fillRect(0 * DIM_KVADRATA_STARTA, pomeraj + i * DIM_KVADRATA_STARTA, DIM_KVADRATA_STARTA, DIM_KVADRATA_STARTA);
                graphicsContext.fillRect(2 * DIM_KVADRATA_STARTA, pomeraj + i * DIM_KVADRATA_STARTA, DIM_KVADRATA_STARTA, DIM_KVADRATA_STARTA);
            }
            else
            {
                graphicsContext.fillRect(1 * DIM_KVADRATA_STARTA, pomeraj  + i * DIM_KVADRATA_STARTA, DIM_KVADRATA_STARTA, DIM_KVADRATA_STARTA);
                graphicsContext.fillRect(3 * DIM_KVADRATA_STARTA, pomeraj + i * DIM_KVADRATA_STARTA, DIM_KVADRATA_STARTA, DIM_KVADRATA_STARTA);
            }
        }

        koren.getChildren().addAll(startOznaka);
    }
}
