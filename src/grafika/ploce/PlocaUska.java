package grafika.ploce;

public class PlocaUska extends PlocaSaStazomRavna{

    public PlocaUska(int orijentacija) {
        super(orijentacija);
    }

    @Override
    protected void nacrtajTraku()
    {

    }

    @Override
    public double sirinaTrake() {
        return super.sirinaTrake() / 2;
    }

    @Override
    public double pomerajTrake()
    {
        return VELICINA / 2 - sirinaTrake() / 2;
    }
}
