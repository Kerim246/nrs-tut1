package ba.unsa.etf.rpr;

public class Znamenitost {
    private int id;
    private String naziv;
    private String slika;
    private Grad grad;


    public Znamenitost(int id, String naziv, String slika, Grad grad) {
        this.id = id;
        this.naziv = naziv;
        this.slika = slika;
        this.grad = grad;
    }

    public Znamenitost() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getSlika() {
        return slika;
    }

    public void setSlika(String slika) {
        this.slika = slika;
    }

    public Grad getGrad() {
        return grad;
    }

    public void setGrad(Grad grad) {
        this.grad = grad;
    }

    @Override
    public String toString() {
        return naziv;
    }
}
