package ba.unsa.etf.rpr;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class GradController {
    public TextField fieldNaziv;
    public TextField fieldBrojStanovnika;
    public TextField fieldPostanskiBroj;
    public ChoiceBox<Drzava> choiceDrzava;
    public ObservableList<Drzava> listDrzave;
    private Grad grad;

    public GradController(Grad grad, ArrayList<Drzava> drzave) {
        this.grad = grad;
        listDrzave = FXCollections.observableArrayList(drzave);
    }

    @FXML
    public void initialize() {
        choiceDrzava.setItems(listDrzave);
        if (grad != null) {
            fieldNaziv.setText(grad.getNaziv());
            fieldBrojStanovnika.setText(Integer.toString(grad.getBrojStanovnika()));
            fieldPostanskiBroj.setText(Integer.toString(grad.getPostanskiBroj()));
            // choiceDrzava.getSelectionModel().select(grad.getDrzava());
            // ovo ne radi jer grad.getDrzava() nije identički jednak objekat kao član listDrzave
            for (Drzava drzava : listDrzave)
                if (drzava.getId() == grad.getDrzava().getId())
                    choiceDrzava.getSelectionModel().select(drzava);
        } else {
            choiceDrzava.getSelectionModel().selectFirst();
        }
    }

    public Grad getGrad() {
        return grad;
    }

    public void clickCancel(ActionEvent actionEvent) {
        grad = null;
        Stage stage = (Stage) fieldNaziv.getScene().getWindow();
        stage.close();
    }

    public void clickOk(ActionEvent actionEvent) {
        boolean sveOk = true;

        if (fieldNaziv.getText().trim().isEmpty()) {
            fieldNaziv.getStyleClass().removeAll("poljeIspravno");
            fieldNaziv.getStyleClass().add("poljeNijeIspravno");
            sveOk = false;
        } else {
            fieldNaziv.getStyleClass().removeAll("poljeNijeIspravno");
            fieldNaziv.getStyleClass().add("poljeIspravno");
        }


        int brojStanovnika = 0;
        try {
            brojStanovnika = Integer.parseInt(fieldBrojStanovnika.getText());
        } catch (NumberFormatException e) {
            // ...
        }
        if (brojStanovnika <= 0) {
            fieldBrojStanovnika.getStyleClass().removeAll("poljeIspravno");
            fieldBrojStanovnika.getStyleClass().add("poljeNijeIspravno");
            sveOk = false;
        } else {
            fieldBrojStanovnika.getStyleClass().removeAll("poljeNijeIspravno");
            fieldBrojStanovnika.getStyleClass().add("poljeIspravno");
        }

        if (!sveOk) return;



        Thread thread = new Thread(() -> {
            int postBroj = Integer.parseInt(fieldPostanskiBroj.getText());
            try {
                URL provjera = new URL("http://c9.etf.unsa.ba/proba/postanskiBroj.php?postanskiBroj="+postBroj);

                BufferedReader in = new BufferedReader(new InputStreamReader(provjera.openStream(), StandardCharsets.UTF_8));
                String rez = "", line = null;
                while ((line = in.readLine()) != null)
                    rez = rez + line;
                in.close();
                if(rez.equals("OK")) {
                    fieldPostanskiBroj.getStyleClass().removeAll("poljeNijeIspravno");
                    fieldPostanskiBroj.getStyleClass().add("poljeIspravno");
                } else if(rez.equals("NOT OK")){
                    fieldPostanskiBroj.getStyleClass().removeAll("poljeIspravno");
                    fieldPostanskiBroj.getStyleClass().add("poljeNijeIspravno");

                    Platform.runLater(() -> {
                        if (grad == null) grad = new Grad();
                        grad.setNaziv(fieldNaziv.getText());
                        grad.setBrojStanovnika(Integer.parseInt(fieldBrojStanovnika.getText()));
                        grad.setDrzava(choiceDrzava.getValue());
                        grad.setPostanskiBroj(postBroj);
                        Stage stage = (Stage) fieldNaziv.getScene().getWindow();
                        stage.close();
                    });
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        thread.start();
    }


}
