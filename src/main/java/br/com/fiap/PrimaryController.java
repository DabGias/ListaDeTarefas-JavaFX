package br.com.fiap;

import br.com.fiap.model.Tarefa;
import java.net.URL;
import java.time.LocalDate;
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
    TableColumn<Tarefa, String> tableColumnStatus;

    @FXML
    TableView<Tarefa> tableViewTarefasPend;
    @FXML
    TableColumn<Tarefa, String> tableColumnTituloPend;
    @FXML
    TableColumn<Tarefa, String> tableColumnDescPend;
    @FXML
    TableColumn<Tarefa, String> tableColumnCategoriaPend;
    @FXML
    TableColumn<Tarefa, LocalDate> tableColumnDataPend;
    @FXML
    TableColumn<Tarefa, String> tableColumnStatusPend;

    @FXML
    TableView<Tarefa> tableViewTarefasConclu;
    @FXML
    TableColumn<Tarefa, String> tableColumnTituloConclu;
    @FXML
    TableColumn<Tarefa, String> tableColumnDescConclu;
    @FXML
    TableColumn<Tarefa, String> tableColumnCategoriaConclu;
    @FXML
    TableColumn<Tarefa, LocalDate> tableColumnDataConclu;
    @FXML
    TableColumn<Tarefa, String> tableColumnStatusConclu;

    @FXML
    TextField txtFieldMarcarConclu;
    @FXML
    Button btnMarcarConclu;

    private List<Tarefa> listaTarefas = new ArrayList<>();
    private List<Tarefa> listaTarefasPend = new ArrayList<>();
    private List<Tarefa> listaTarefasConclu = new ArrayList<>();

    @FXML
    public void salvar() {
        Tarefa tarefa = carregaTarefa();

        if (verTarefa(tarefa, listaTarefasPend)) {
            alertaErro("Essa tarefa já existe!");
        } else {
            if (tarefa.getTitulo().length() > 0 && tarefa.getDesc().length() > 0 && tarefa.getCategoria().length() > 0 && tarefa.getData() != null) {
                verStatus(tarefa);

                System.out.println(tarefa);
    
                listaTarefas.add(tarefa);
                tableViewTarefas.getItems().add(tarefa);
                listaTarefasPend.add(tarefa);
                tableViewTarefasPend.getItems().add(tarefa);

                limpaTxt();
                alertaInfo("A tarefa foi salva com sucesso.");
            } else {
                alertaErro("A tarefa deve conter título, descrição, categoria e data.");
            }
        }
    }

    @FXML
    public void marcarComoConclu() {
        String nomeTarefa = txtFieldMarcarConclu.getText();
        int procura = 0;

        for (Tarefa tarefaPend : listaTarefasPend) {
            if (nomeTarefa.toUpperCase().trim().equals(tarefaPend.getTitulo().toUpperCase().trim())) {
                procura++;
                tarefaPend.setConcluida(true);
                verStatus(tarefaPend);
                tableViewTarefas.getItems().remove(tarefaPend);
                tableViewTarefas.getItems().add(tarefaPend);
                listaTarefasPend.remove(tarefaPend);
                tableViewTarefasPend.getItems().remove(tarefaPend);
                listaTarefasConclu.add(tarefaPend);
                tableViewTarefasConclu.getItems().add(tarefaPend);

                alertaInfo("A tarefa foi marcada como concluída.");
                txtFieldMarcarConclu.setText("");
            }
        }

        if (procura == 0) {
            alertaErro("Não há tarefas com esse nome.");
            txtFieldMarcarConclu.setText("");
        }
    }

    private Tarefa carregaTarefa() {
        String titulo = txtFieldTitulo.getText().trim();
        String desc = txtAreaDesc.getText().trim();
        String categoria = txtFieldCategoria.getText().trim();
        LocalDate data = datePickerData.getValue();

        return new Tarefa(titulo, desc, categoria, data);
    }

    private boolean verTarefa(Tarefa tarefa, List<Tarefa> listaTarefasPend) {
        for (Tarefa tarefaPend : listaTarefasPend) {
            if (tarefa.getTitulo().toUpperCase().equals(tarefaPend.getTitulo().toUpperCase())) {
                return true;
            }
        }

        return false;
    }

    private void verStatus(Tarefa tarefa) {
        if (tarefa.isConcluida()) {
            tarefa.setStatus("Concluída");
        } else {
            tarefa.setStatus("Pendente");
        }
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
        tableColumnStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

        tableColumnTituloPend.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        tableColumnDescPend.setCellValueFactory(new PropertyValueFactory<>("desc"));
        tableColumnCategoriaPend.setCellValueFactory(new PropertyValueFactory<>("categoria"));
        tableColumnDataPend.setCellValueFactory(new PropertyValueFactory<>("data"));
        tableColumnStatusPend.setCellValueFactory(new PropertyValueFactory<>("status"));

        tableColumnTituloConclu.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        tableColumnDescConclu.setCellValueFactory(new PropertyValueFactory<>("desc"));
        tableColumnCategoriaConclu.setCellValueFactory(new PropertyValueFactory<>("categoria"));
        tableColumnDataConclu.setCellValueFactory(new PropertyValueFactory<>("data"));
        tableColumnStatusConclu.setCellValueFactory(new PropertyValueFactory<>("status"));
    }

}
