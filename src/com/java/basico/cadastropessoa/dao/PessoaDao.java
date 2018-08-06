package com.java.basico.cadastropessoa.dao;

import com.java.basico.cadastropessoa.model.Pessoa;
import com.java.basico.cadastropessoa.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PessoaDao implements IAbstractDao<Pessoa>{

    private Connection connection;

    public PessoaDao() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        this.connection = connect();
    }

    protected void finalize() {
        try {
            this.connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public List<Pessoa> all() throws SQLException {
        ResultSet rs = DBUtils.getResultSet(connection, "select * from pessoas");
        List<Pessoa> pessoas  = new ArrayList<Pessoa>();
        while(rs.next()) {
            Pessoa p = new Pessoa();
            p.setId(rs.getInt("id"));
            p.setNome(rs.getString("nome"));
            p.setIdade(rs.getInt("idade"));
            pessoas.add(p);
        }
        return pessoas;

    }

    @Override
    public Pessoa findById(int id) throws SQLException {
        PreparedStatement statement = DBUtils.getPreparedStatment(connection, "select * from pessoas where id = ?");
        statement.setInt(1, id);
        ResultSet rs = statement.executeQuery();
        if (rs.next()) {
            Pessoa p = new Pessoa();
            p.setNome(rs.getString("nome"));
            p.setIdade(rs.getInt("idade"));
            p.setId(rs.getInt("id"));
            return p;
        }
        return null;
    }

    @Override
    public void insert(Pessoa entidade) throws SQLException {
        PreparedStatement statement = DBUtils.getPreparedStatment(connection,
                "insert into pessoas (nome, idade) values (?, ?)");
        statement.setString(1, entidade.getNome());
        statement.setInt(2, entidade.getIdade());
        statement.execute();
    }

    @Override
    public void update(Pessoa entidade) throws SQLException {
        PreparedStatement statement = DBUtils.getPreparedStatment(connection,
                "update pessoas set nome = ?, idade = ? where id = ?");
        statement.setString(1, entidade.getNome());
        statement.setInt(2, entidade.getIdade());
        statement.setInt(3, entidade.getId());
        statement.execute();


    }

    @Override
    public void delete(Pessoa entidade) throws SQLException {
        PreparedStatement stm = DBUtils.getPreparedStatment(connection,
                "delete from pessoas where id = ?");
        stm.setInt(1, entidade.getId());
        stm.execute();
    }

    public Connection connect() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        return DBUtils.getConnection();
    }
}
