package br.com.fiap.model;

import java.time.LocalDate;

public class Tarefa {
    private String titulo;
    private String desc;
    private String categoria;
    private LocalDate data;
    private boolean concluida;
    private String status;

    public Tarefa(String titulo, String desc, String categoria, LocalDate data) {
        this.titulo = titulo;
        this.desc = desc;
        this.categoria = categoria;
        this.data = data;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public boolean isConcluida() {
        return concluida;
    }

    public void setConcluida(boolean concluida) {
        this.concluida = concluida;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String toString() {
        if (this.concluida) return this.titulo + ": " + this.desc + "\nData: " + this.data + "\nSituação: Concluída";

        return this.titulo + ": " + this.desc + "\nData: " + this.data + "\nSituação: Pendente";
    }    
}
