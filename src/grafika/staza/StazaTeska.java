package grafika.staza;

import grafika.ploce.*;

public class StazaTeska extends Staza{

    public StazaTeska()
    {
        super();
    }

    @Override
    public void napraviStazu() {

        this.postaviPlocu(1, 1, new PlocaSaStazomKrivina(PlocaSaStazomKrivina.treca));

        for(int i = 2; i < 6; i++ )
            this.postaviPlocu(1, i, new PlocaSaStazomRavna(PlocaSaStazomRavna.Horizontalna));

        this.postaviPlocu(1, 6, new PlocaSaStazomKrivina(PlocaSaStazomKrivina.cetvrta));

        for(int i = 2; i < 4; i++)
            this.postaviPlocu(i, 6, new PlocaSaStazomRavna(PlocaSaStazomRavna.Vertikalna));

        this.postaviPlocu(4, 6, new PlocaSaStazomKrivina(PlocaSaStazomKrivina.druga));

        for(int i = 7; i < 9; i++)
            this.postaviPlocu(4, i, new PlocaSaStazomRavna(PlocaSaStazomRavna.Horizontalna));

        this.postaviPlocu(4, 9, new PlocaSaStazomKrivina(PlocaSaStazomKrivina.prva));

        for(int i = 3; i > 1; i--)
            this.postaviPlocu(i, 9, new PlocaSaStazomRavna(PlocaSaStazomRavna.Vertikalna));

        this.postaviPlocu(1, 9, new PlocaSaStazomKrivina(PlocaSaStazomKrivina.treca));

        this.postaviPlocu(1, 10, new PlocaSaStazomRavna(PlocaSaStazomRavna.Horizontalna));

        this.postaviPlocu(1, 11, new PlocaSaStazomKrivina(PlocaSaStazomKrivina.cetvrta));

        for(int i = 2; i < 6; i++)
            this.postaviPlocu(i, 11, new PlocaSaStazomRavna(PlocaSaStazomRavna.Vertikalna));

        this.postaviPlocu(6, 11, new PlocaSaStazomKrivina(PlocaSaStazomKrivina.prva));

        this.postaviPlocu(6, 10, new PlocaProsirenje(PlocaProsirenje.DESNO));

        for(int i = 9; i > 6; i--)
            this.postaviPlocu(6, i, new PlocaUska(PlocaSaStazomRavna.Horizontalna));

        this.postaviPlocu(6, 6, new PlocaProsirenje(PlocaProsirenje.LEVO));

        this.postaviPlocu(6, 5, new PlocaSaStazomKrivina(PlocaSaStazomKrivina.druga));

        this.postaviPlocu(5, 5, new PlocaSaStazomRavna(PlocaSaStazomRavna.Vertikalna));

        this.postaviPlocu(4, 5, new PlocaSaStazomKrivina(PlocaSaStazomKrivina.cetvrta));

        for(int i = 4; i > 1; i--)
            this.postaviPlocu(4, i, new PlocaSaStazomRavna(PlocaSaStazomRavna.Horizontalna));

        this.postaviPlocu(4,1, new PlocaSaStazomKrivina(PlocaSaStazomKrivina.druga));

        for(int i = 3; i > 1; i--)
            this.postaviPlocu(i, 1, new PlocaSaStazomRavna(PlocaSaStazomRavna.Vertikalna));

        // postavi start
        this.postaviPlocu(1, 3, new PlocaSaStartom(PlocaSaStazomRavna.Horizontalna));

        postaviKontrolnePloce();
    }

    @Override
    protected void postaviKontrolnePloce()
    {
        int id = 1;
        postaviKontrolni(1, 4, id++);
        postaviKontrolni(1, 6, id++);
        postaviKontrolni(4, 6, id++);
        postaviKontrolni(4, 9, id++);
        postaviKontrolni(1, 9, id++);
        postaviKontrolni(1, 11, id++);
        postaviKontrolni(3, 11, id++);
        postaviKontrolni(6, 11, id++);
        postaviKontrolni(6, 5, id++);
        postaviKontrolni(4, 5, id++);
        postaviKontrolni(4, 1, id++);
        postaviKontrolni(1, 1, id++);
    }
}
