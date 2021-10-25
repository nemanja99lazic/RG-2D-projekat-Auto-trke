package senzori;

import grafika.objekti.Formula;
import grafika.ploce.Ploca;
import grafika.staza.Staza;

import java.util.ArrayList;

public class Senzor extends Thread{

    //private ArrayList<Integer> idPregazenihKontrolnihBlokova;
    private Staza staza;
    private Formula formula;
    private boolean zavrsioTrku;
    private boolean igracVarao;
    private int ciljaniId = 1;

    public Senzor(Staza staza, Formula formula)
    {
        //idPregazenihKontrolnihBlokova = new ArrayList<Integer>();
        this.staza = staza;
        this.formula = formula;
        this.igracVarao = false;
        this.zavrsioTrku = false;
    }

    @Override
    public void run() {
        int ciljaniId = 1;
        Ploca[][] ploce= staza.dohvatiMatricuPloca();

        while(!Thread.interrupted())
        {
            double xFormule;
            double yFormule;

            xFormule = formula.pozicija().x();
            yFormule = formula.pozicija().y();
            formula.notify();

            // Da li je poceo trku?
            if(!unutarGranica(ploce[staza.START_POLJE_X][staza.START_POLJE_Y], xFormule, yFormule) && !formula.dohvatiPoceoTrku()) {
                formula.postaviPoceoTrku(true);
                System.out.println("POCEO TRKU");
            }

            // Zavrsio trku
            if(unutarGranica(ploce[staza.START_POLJE_X][staza.START_POLJE_Y], xFormule, yFormule) && formula.dohvatiPoceoTrku())
            {
                zavrsioTrku = true;
                return;
            }

            if(!igracVarao)
            {
                for(int i = 0; i < ploce.length; i++)
                    for(int j = 0; j < ploce[0].length; j++)
                    {
                        if(ploce[i][j] != null && ploce[i][j].dohvatiKontrolniId() != 0)
                        {
                            if(unutarGranica(ploce[i][j], xFormule, yFormule))
                            {
                                if(ploce[i][j].dohvatiKontrolniId() == ciljaniId) {
                                    System.out.println("Detektovano i = " + i + ", j = " + j + ", ID = " + ciljaniId);
                                    ciljaniId++;
                                }
                                else
                                    if(ploce[i][j].dohvatiKontrolniId() > ciljaniId) {
                                        igracVarao = true;
                                        i = ploce.length;  // postavi uslov za izlazak iz oba ciklusa
                                        j = ploce[0].length;
                                    }
                            }
                        }
                    }
            }
        }
    }

    public void proveriSenzor()
    {
        //int ciljaniId = 1;
        Ploca[][] ploce= staza.dohvatiMatricuPloca();

        double xFormule;
        double yFormule;

        xFormule = formula.pozicija().x();
        yFormule = formula.pozicija().y();

        // Da li je poceo trku?
        if(!unutarGranica(ploce[staza.START_POLJE_X][staza.START_POLJE_Y], xFormule, yFormule) && !formula.dohvatiPoceoTrku()) {
            formula.postaviPoceoTrku(true);
            System.out.println("POCEO TRKU");
        }

        // Zavrsio trku
        if(unutarGranica(ploce[staza.START_POLJE_X][staza.START_POLJE_Y], xFormule, yFormule) && formula.dohvatiPoceoTrku()) {
            zavrsioTrku = true;
            return;
        }

        if(!igracVarao)
        {
            for(int i = 0; i < ploce.length; i++)
                for(int j = 0; j < ploce[0].length; j++)
                {
                    if(ploce[i][j] != null && ploce[i][j].dohvatiKontrolniId() != 0)
                    {
                        if(unutarGranica(ploce[i][j], xFormule, yFormule))
                        {
                            if(ploce[i][j].dohvatiKontrolniId() == ciljaniId) {
                                System.out.println("Detektovano i = " + i + ", j = " + j + ", ID = " + ciljaniId);
                                ciljaniId++;
                            }
                            else
                            if(ploce[i][j].dohvatiKontrolniId() > ciljaniId) {
                                igracVarao = true;
                                i = ploce.length;  // postavi uslov za izlazak iz oba ciklusa
                                j = ploce[0].length;
                            }
                        }
                    }
                }
        }
    }

    private boolean unutarGranica(Ploca ploca, double x, double y)
    {
        double xPloce = ploca.getBoundsInParent().getMinX();
        double yPloce = ploca.getBoundsInParent().getMinY();

        if(x >= xPloce && x < xPloce + Ploca.VELICINA && y >= yPloce && y < yPloce + Ploca.VELICINA)
            return true;
        return false;
    }

    public boolean dohvatiIgracVarao()
    {
        return igracVarao;
    }

    public boolean dohvatiZavrsioTrku()
    {
        return zavrsioTrku;
    }
}
