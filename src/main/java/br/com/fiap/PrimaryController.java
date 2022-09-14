package br.com.fiap;

import java.net.URL;
import java.time.LocalDate;
import br.com.fiap.model.Tarefa;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class PrimaryController implements Initializable {
    @FXML
    TextField txtFieldTitulo;
    @FXML
    TextArea txtAreaDesc;
    @FXML
    TextField txtFieldCategoria;
    @FXML
    DatePicker datePickerData;
    @FXML
    Button btnSalvar;
    @FXML
    TableView<Tarefa> tableViewTarefas;
    @FXML
    TableColumn<Tarefa, String> tableColumnTitulo;
    @FXML
    TableColumn<Tarefa, String> tableColumnDesc;
    @FXML
    TableColumn<Tarefa, String> tableColumnCategoria;
    @FXML
    TableColumn<Tarefa, LocalDate> tableColumnData;
    @FXML
    TableColumn<Tarefa, Boolean> tableColumnStatus;
    private List<Tarefa> listaTarefas = new ArrayList<>();

    @FXML
    public void salvar() {
        Tarefa tarefa = carregaTarefa();

        if (tarefa.getTitulo().length() > 0 && tarefa.getDesc().length() > 0 && tarefa.getCategoria().length() > 0 && tarefa.getData() != null) {
            listaTarefas.add(tarefa);
            System.out.println(listaTarefas);
            tableViewTarefas.getItems().addAll(listaTarefas);
            limpaTxt();
            alertaInfo("A tarefa foi salva com sucesso.");
        } else {
            alertaErro("A tarefa deve conter título, descrição, categoria e data.");
        }
    }

    private Tarefa carregaTarefa() {
        String titulo = txtFieldTitulo.getText();
        String desc = txtAreaDesc.getText();
        String categoria = txtFieldCategoria.getText();
        LocalDate data = datePickerData.getValue();

        return new Tarefa(titulo, desc, categoria, data);
    }

    public void limpaTxt() {
        txtFieldTitulo.setText("");
        txtAreaDesc.setText("");
        txtFieldCategoria.setText("");
    }

    public void alertaInfo(String msg) {
        Alert alerta = new Alert(AlertType.INFORMATION);
        alerta.setContentText(msg);
        alerta.show();
    }

    public void alertaErro(String msg) {
        Alert alerta = new Alert(AlertType.ERROR);
        alerta.setContentText(msg);
        alerta.show();
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        tableColumnTitulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        tableColumnDesc.setCellValueFactory(new PropertyValueFactory<>("desc"));
        tableColumnCategoria.setCellValueFactory(new PropertyValueFactory<>("categoria"));
        tableColumnData.setCellValueFactory(new PropertyValueFactory<>("data"));
        tableColumnStatus.setCellValueFactory(new PropertyValueFactory<>("concluida"));
    }

}
