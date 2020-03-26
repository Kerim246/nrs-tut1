package ba.unsa.etf.rpr;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class ZnamenitostController {
    public TextField fieldNazivZnamenitost;
    public ImageView imageViewZnamenitost;
    private Znamenitost znanemitost;
    private Grad grad;
    private String slika;

    public ZnamenitostController(Grad grad) {
        this.grad = grad;
        this.slika = "";
    }

    public Znamenitost getZnanemitost() {
        return znanemitost;
    }

    public void dodajZnamenitost() {
        if (znanemitost == null)
            znanemitost = new Znamenitost();
        znanemitost.setGrad(grad);
        znanemitost.setSlika(slika);
        znanemitost.setNaziv(fieldNazivZnamenitost.getText());
        Stage stage = (Stage) fieldNazivZnamenitost.getScene().getWindow();
        stage.close();
    }

    public void clickOdaberiSliku() {
        Stage stage = new Stage();
        Parent root;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/pretraga.fxml"));
            PretragaController pretragaController = new PretragaController();
            loader.setController(pretragaController);
            root = loader.load();
            stage.setTitle("Traži");
            stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            stage.setResizable(true);
            stage.show();

            stage.setOnHiding(event -> {
                slika = pretragaController.getSlika();
                try {
                    imageViewZnamenitost.setImage(new Image(new FileInputStream(slika)));
                } catch (FileNotFoundException e) {
                    //..
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
