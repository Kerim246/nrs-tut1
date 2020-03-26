package ba.unsa.etf.rpr;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class PretragaController implements Initializable {
    public TextField fieldPretraga;
    public ListView<String> listViewPretraga;
    ObservableList<String> listPutanja;
    ArrayList<String> putanje;
    private String slika;
    private Thread thread;

    public void clickTrazi() {
        putanje.clear();
        listPutanja.setAll(putanje);
        thread = new Thread(() -> pronadji(fieldPretraga.getText(), new File(System.getProperty("user.home"))));
        thread.start();
    }

    public void pronadji(String uzorak, File file) {
        File[] files = file.listFiles();
        if (files != null) {
            for (File f : files) {
                if (f.isDirectory()) {
                    pronadji(uzorak, f);
                } else if (f.getAbsolutePath().toLowerCase().contains(uzorak.toLowerCase())) {
                    Platform.runLater(() -> listPutanja.add(f.getAbsolutePath()));
                }
            }
        }
    }

    public String getSlika() {
        if (slika == null) slika = "";
        return slika;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        thread = new Thread();
        putanje = new ArrayList<>();
        listPutanja = FXCollections.observableArrayList(putanje);
        listViewPretraga.setItems(listPutanja);
    }

    public void izaberi() {
        thread.interrupt();
        if (!listViewPretraga.getSelectionModel().isEmpty())
            slika = listViewPretraga.getSelectionModel().getSelectedItem();
        Stage stage = (Stage) listViewPretraga.getScene().getWindow();
        stage.close();
    }



}
