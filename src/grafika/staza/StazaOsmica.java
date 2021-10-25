package grafika.staza;

import grafika.ploce.*;

public class StazaOsmica extends Staza{

    private static int POMERAJ_DONJI_DEO_VERTIKALNI = 4;
    private static int POMERAJ_DONJI_DEO_HORIZONTALNI = 6;

    public StazaOsmica(){
        super(40, 40);
    }

    @Override
    public void napraviStazu() {

        // Gornji deo
        this.postaviPlocu(1, 1, new PlocaSaStazomKrivina(PlocaSaStazomKrivina.treca));
        this.postaviPlocu(1, 7, new PlocaSaStazomKrivina(PlocaSaStazomKrivina.cetvrta));
        this.postaviPlocu(5, 1, new PlocaSaStazomKrivina(PlocaSaStazomKrivina.druga));

        for (int i = 2; i < 7; i++) {
            this.postaviPlocu(1, i, new PlocaSaStazomRavna(PlocaSaStazomRavna.Horizontalna));
            this.postaviPlocu(5, i, new PlocaSaStazomRavna(PlocaSaStazomRavna.Horizontalna));
        }

        for (int i = 2; i < 5; i++) {
            this.postaviPlocu(i, 1, new PlocaSaStazomRavna(PlocaSaStazomRavna.Vertikalna));
            this.postaviPlocu(i, 7, new PlocaSaStazomRavna(PlocaSaStazomRavna.Vertikalna));
        }

        this.postaviPlocu(1, 3, new PlocaSaStartom(PlocaSaStazomRavna.Horizontalna));

        // Centar
        this.postaviPlocu(5, 7, new PlocaUkrstanje(PlocaSaStazomRavna.Vertikalna));

        //Donji deo
        this.postaviPlocu(1 + POMERAJ_DONJI_DEO_VERTIKALNI, 7 + POMERAJ_DONJI_DEO_HORIZONTALNI, new PlocaSaStazomKrivina(PlocaSaStazomKrivina.cetvrta));
        this.postaviPlocu(5 + POMERAJ_DONJI_DEO_VERTIKALNI, 1 + POMERAJ_DONJI_DEO_HORIZONTALNI, new PlocaSaStazomKrivina(PlocaSaStazomKrivina.druga));
        this.postaviPlocu(5 + POMERAJ_DONJI_DEO_VERTIKALNI, 7 + POMERAJ_DONJI_DEO_HORIZONTALNI, new PlocaSaStazomKrivina(PlocaSaStazomKrivina.prva));

        for (int i = 2; i < 7; i++) {
            this.postaviPlocu(1 + POMERAJ_DONJI_DEO_VERTIKALNI, i + POMERAJ_DONJI_DEO_HORIZONTALNI, new PlocaSaStazomRavna(PlocaSaStazomRavna.Horizontalna));
            this.postaviPlocu(5 + POMERAJ_DONJI_DEO_VERTIKALNI, i + POMERAJ_DONJI_DEO_HORIZONTALNI, new PlocaSaStazomRavna(PlocaSaStazomRavna.Horizontalna));
        }

        for (int i = 2; i < 5; i++) {
            this.postaviPlocu(i + POMERAJ_DONJI_DEO_VERTIKALNI, 1 + POMERAJ_DONJI_DEO_HORIZONTALNI, new PlocaSaStazomRavna(PlocaSaStazomRavna.Vertikalna));
            this.postaviPlocu(i + POMERAJ_DONJI_DEO_VERTIKALNI, 7 + POMERAJ_DONJI_DEO_HORIZONTALNI, new PlocaSaStazomRavna(PlocaSaStazomRavna.Vertikalna));
        }

        // Strelice
        this.postaviPlocu(4, 7, new PlocaSaStrelicom(PlocaSaStrelicom.DOLE));
        this.postaviPlocu(6, 7, new PlocaSaStrelicom(PlocaSaStrelicom.DOLE));
        this.postaviPlocu(5, 8, new PlocaSaStrelicom(PlocaSaStrelicom.LEVO));
        this.postaviPlocu(5,6, new PlocaSaStrelicom(PlocaSaStrelicom.LEVO));

        postaviKontrolnePloce();
    }

    @Override
    protected void postaviKontrolnePloce()
    {
        int id = 1;
        postaviKontrolni(1, 5, id++);
        postaviKontrolni(1, 7, id++);
        postaviKontrolni(4, 7, id++);
        postaviKontrolni(6, 7, id++);
        postaviKontrolni(8, 7, id++);
        postaviKontrolni(9, 7, id++);
        postaviKontrolni(9, 9, id++);
        postaviKontrolni(9, 11, id++);
        postaviKontrolni(9, 13, id++);
        postaviKontrolni(7, 13, id++);
        postaviKontrolni(5, 13, id++);
        postaviKontrolni(5, 11, id++);
        postaviKontrolni(5, 9, id++);
        postaviKontrolni(5, 6, id++);
        postaviKontrolni(5, 4, id++);
        postaviKontrolni(5, 1, id++);
        postaviKontrolni(3, 1, id++);
        postaviKontrolni(1, 1, id++);
    }
}
