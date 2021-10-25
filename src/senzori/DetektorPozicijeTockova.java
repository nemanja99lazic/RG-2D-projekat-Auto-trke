package senzori;

import grafika.objekti.Formula;
import grafika.ploce.Ploca;
import grafika.staza.Staza;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.transform.Translate;

public class DetektorPozicijeTockova extends Thread{

    private Staza staza;
    private Formula formula;

    public DetektorPozicijeTockova(Staza staza, Formula formula)
    {
        this.setName("Detektor pozicije tockova");
        this.staza = staza;
        this.formula = formula;
    }

    @Override
    public void run() {
        while(!Thread.interrupted())
            formula.pomeri(0.0000001);
    }

    public void proveri()
    {
        int pronadjenoTockovaNaStazi = 0;
        Point2D pozicijaFormule = new Point2D(formula.pozicija().x(), formula.pozicija().y());
        Point2D[] tackeTockova = new Point2D[Formula.BROJ_TOCKOVA];
        Ploca[][] matricaPloca = staza.dohvatiMatricuPloca();

        for(int i = 0; i < Formula.BROJ_TOCKOVA; i++)
            tackeTockova[i] = pozicijaFormule.add(formula.dohvatiPomerajeTockova()[i]);

        for(int i = 0; i < matricaPloca.length; i++)
            for(int j = 0; j < matricaPloca[0].length; j++)
                if(matricaPloca[i][j] != null)
                {
                    for(int tocakId = 0; tocakId < Formula.BROJ_TOCKOVA; tocakId++)
                        if(matricaPloca[i][j].getBoundsInParent().intersects(tackeTockova[tocakId].getX(), tackeTockova[tocakId].getY(), 1, 1))
                        {
                            pronadjenoTockovaNaStazi++;
                        }
                }
        if(pronadjenoTockovaNaStazi < Formula.BROJ_TOCKOVA && formula.brzina().intenzitet() > 0) {
            double promenaSmera = -0.2 + 4 * Math.random() / 10  ; // -0.1 < promenaSmera < 0.1
            formula.skretanje(promenaSmera);
            formula.postaviMaxBrzinu(formula.dohvatiInicijalnuMaxBrzinu() - 5);
        }

        if(pronadjenoTockovaNaStazi == Formula.BROJ_TOCKOVA && formula.brzina().intenzitet() > 0 && formula.dohvatiInicijalnuMaxBrzinu() != formula.dohvatiTrenutnuMaxBrzinu())
        {
            formula.postaviMaxBrzinu(formula.dohvatiInicijalnuMaxBrzinu());
        }
    }
}
