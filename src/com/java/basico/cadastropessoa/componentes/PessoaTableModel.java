package com.java.basico.cadastropessoa.componentes;

import com.java.basico.cadastropessoa.model.Pessoa;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class PessoaTableModel extends AbstractTableModel {

    private List<Pessoa> pessoas = new ArrayList<Pessoa>();

    private String[] columnNames = {"Id", "Nome", "Idade"};

    private Class<?>[] columnTypes = {Integer.class, String.class, Integer.class};


    public void load(List<Pessoa> pessoas) {
        this.pessoas = pessoas;
        fireTableDataChanged(); /// Tabela redesenhada
    }

    public Pessoa getPessoaAt(int index) {
        if (this.pessoas.size() <= 0) {
            return null;
        }
        return this.pessoas.get(index);
    }

    @Override
    public int getRowCount() {
        return this.pessoas.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (this.pessoas.size() <= 0) {
            return null;
        }
        Pessoa p = this.pessoas.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return p.getId();
            case 1:
                return p.getNome();
            case 2:
                return p.getIdade();
        }
        return null;
    }
}
