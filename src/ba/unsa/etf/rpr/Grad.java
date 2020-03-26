package ba.unsa.etf.rpr;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Grad {
    private int id;
    private String naziv;
    private int brojStanovnika;
    private Drzava drzava;
    private int postanskiBroj;
    public ArrayList<Znamenitost> znamenitosti;

    public Grad(int id, String naziv, int brojStanovnika, Drzava drzava, int postanskiBroj) {
        this.id = id;
        this.naziv = naziv;
        this.brojStanovnika = brojStanovnika;
        this.drzava = drzava;
        this.postanskiBroj = postanskiBroj;
        this.znamenitosti = new ArrayList<>();
    }

    public Grad() {
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

    public int getBrojStanovnika() {
        return brojStanovnika;
    }

    public void setBrojStanovnika(int brojStanovnika) {
        this.brojStanovnika = brojStanovnika;
    }

    public Drzava getDrzava() {
        return drzava;
    }

    public void setDrzava(Drzava drzava) {
        this.drzava = drzava;
    }

    public int getPostanskiBroj() {
        return postanskiBroj;
    }

    public void setPostanskiBroj(int postanskiBroj) {
        this.postanskiBroj = postanskiBroj;
    }

    public ArrayList<Znamenitost> getZnamenitost(){
        return znamenitosti;
    }

    public void setZnamenitosti(ArrayList<Znamenitost> znamenitosti){
        this.znamenitosti = znamenitosti;
    }

    @Override
    public String toString() { return naziv; }
}