package com.java.basico.cadastropessoa.forms;

import com.java.basico.cadastropessoa.componentes.PessoaJTable;
import com.java.basico.cadastropessoa.dao.PessoaDao;
import com.java.basico.cadastropessoa.model.Pessoa;

import javax.swing.*;
import java.awt.event.*;
import java.sql.SQLException;

public class FrmPrincipal extends JFrame {

    public FrmPrincipal() {
        this.setSize(600,500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocation(200,150);
        this.setTitle("Treinaweb - Cadastro de pessoas");

        JPanel panel = new JPanel();
        panel.setLayout(null);

        //lblid
        JLabel lblId = new JLabel("Id: ");
        lblId.setBounds(10,10,50, 20);
        panel.add(lblId);

        //txtId
        JTextField txtId = new JTextField("");
        txtId.setBounds(55,10,515,20);
        txtId.enable(false);
        panel.add(txtId);


        //lblNome
        JLabel lblNome = new JLabel("Nome: ");
        lblNome.setBounds(10,35,50,20);
        panel.add(lblNome);

        //txtNome
        JTextField txtNome = new JTextField("");
        txtNome.setBounds(55, 35, 515, 20);
        panel.add(txtNome);

        //lblIdade
        JLabel lblIdade = new JLabel("Idade: ");
        lblIdade.setBounds(10,60,50,20);
        panel.add(lblIdade);

        //txtIdade
        JTextField txtIdade = new JTextField("");
        txtIdade.setBounds(55, 60, 515, 20);
        txtIdade.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                char tecla = e.getKeyChar();

                if (Character.isAlphabetic(tecla)) {
                    e.consume();
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        panel.add(txtIdade);

        //tblPessoas
        PessoaJTable tblPessoas = new PessoaJTable();
        tblPessoas.setBounds(10, 110, 550, 280);
        tblPessoas.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    Pessoa selecionada = tblPessoas.getPessoaSelecionada();
                    txtId.setText(String.valueOf(selecionada.getId()));
                    txtNome.setText(selecionada.getNome());
                    txtIdade.setText(String.valueOf(selecionada.getIdade()));
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        //btnAdicionar
        JButton btnAdicionar = new JButton("Adicionar");
        btnAdicionar.setBounds(55, 85, 100, 20);
        btnAdicionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Pessoa novaPessoa = new Pessoa();
                novaPessoa.setNome(txtNome.getText());
                novaPessoa.setIdade(Integer.parseInt(txtIdade.getText()));
                try {
                    new PessoaDao().insert(novaPessoa);
                    JOptionPane.showMessageDialog(null, "Pessoa inserida com sucesso");
                    carregarTabela(tblPessoas);
                } catch (SQLException | ClassNotFoundException| InstantiationException| IllegalAccessException el) {
                    JOptionPane.showMessageDialog(null, "Erro ao inserir pessoa no banco: " + el.getMessage());
                }
            }
        });
        panel.add(btnAdicionar);

        //btnAlterar
        JButton btnAlterar = new JButton("Alterar");
        btnAlterar.setBounds(160, 85, 100, 20);
        btnAlterar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Pessoa alterada = new Pessoa();
                alterada.setId(Integer.parseInt(txtId.getText()));
                alterada.setNome(txtNome.getText());
                alterada.setIdade(Integer.parseInt(txtIdade.getText()));
                try {
                    new PessoaDao().update(alterada);
                    JOptionPane.showMessageDialog(null, "Pessoa alterada com sucesso.");
                    carregarTabela(tblPessoas);
                } catch (SQLException |ClassNotFoundException|InstantiationException|IllegalAccessException e1) {
                    JOptionPane.showMessageDialog(null, "Houve um erro ao alterar pessoa");
                }
            }
        });
        panel.add(btnAlterar);

        //btnExcluir
        JButton btnExcluir = new JButton("Excluir");
        btnExcluir.setBounds(265, 85, 100, 20);
        btnExcluir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Pessoa deletada = new Pessoa();
                deletada.setId(Integer.parseInt(txtId.getText()));
                try {
                    new PessoaDao().delete(deletada);
                    JOptionPane.showMessageDialog(null, "Pessoa deletada com sucesso.");
                    carregarTabela(tblPessoas);
                } catch (SQLException |ClassNotFoundException|InstantiationException|IllegalAccessException e1) {
                    JOptionPane.showMessageDialog(null, "Houve um erro ao deletar pessoa");
                }
            }
        });
        panel.add(btnExcluir);

        panel.add(tblPessoas);

        carregarTabela(tblPessoas);
        this.add(panel);
        this.setVisible(true);

    }

    private void carregarTabela(PessoaJTable tabela) {
        try {
            tabela.load(new PessoaDao().all());
        } catch (ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException e) {
            JOptionPane.showMessageDialog(null, "Houve um erro ao carregar as pessoas do banco de dados");
        }
    }
}
