package grafika.staza;

import grafika.ploce.PlocaSaStartom;
import grafika.ploce.PlocaSaStazomKrivina;
import grafika.ploce.PlocaSaStazomRavna;

public class StazaDefault extends Staza{
    @Override
    public void napraviStazu() {
        this.postaviPlocu(1, 1, new PlocaSaStazomKrivina(PlocaSaStazomKrivina.treca));
        this.postaviPlocu(1, 7, new PlocaSaStazomKrivina(PlocaSaStazomKrivina.cetvrta));
        this.postaviPlocu(5, 1, new PlocaSaStazomKrivina(PlocaSaStazomKrivina.druga));
        this.postaviPlocu(5, 7, new PlocaSaStazomKrivina(PlocaSaStazomKrivina.prva));

        for (int i = 2; i < 7; i++) {
            this.postaviPlocu(1, i, new PlocaSaStazomRavna(PlocaSaStazomRavna.Horizontalna));
            this.postaviPlocu(5, i, new PlocaSaStazomRavna(PlocaSaStazomRavna.Horizontalna));
        }

        for (int i = 2; i < 5; i++) {
            this.postaviPlocu(i, 1, new PlocaSaStazomRavna(PlocaSaStazomRavna.Vertikalna));
            this.postaviPlocu(i, 7, new PlocaSaStazomRavna(PlocaSaStazomRavna.Vertikalna));
        }

        this.postaviPlocu(1, 3, new PlocaSaStartom(PlocaSaStazomRavna.Horizontalna));

        postaviKontrolnePloce();
    }

    @Override
    protected void postaviKontrolnePloce()
    {
        int id = 1;
        postaviKontrolni(1, 5, id++);
        postaviKontrolni(1, 7, id++);
        postaviKontrolni(3, 7, id++);
        postaviKontrolni(5, 7, id++);
        postaviKontrolni(5, 5, id++);
        postaviKontrolni(5, 3, id++);
        postaviKontrolni(5, 1, id++);
        postaviKontrolni(3, 1, id++);
        postaviKontrolni(1, 1, id++);
    }
}
