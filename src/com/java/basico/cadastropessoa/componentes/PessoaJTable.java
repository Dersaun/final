package com.java.basico.cadastropessoa.componentes;

import com.java.basico.cadastropessoa.model.Pessoa;

import javax.swing.*;
import java.util.List;

public class PessoaJTable extends JTable {
    private PessoaTableModel tableModel;

    public PessoaJTable() {
        this.tableModel = new PessoaTableModel();
        setModel(this.tableModel);
    }

    public void load(List<Pessoa> pessoas) {
        tableModel.load(pessoas); /// Tabela redesenhada
    }

    public Pessoa getPessoaSelecionada() {
        int index = getSelectedRow();
        return this.tableModel.getPessoaAt(index);
    }




}
