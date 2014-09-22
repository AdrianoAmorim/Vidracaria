package crud;

import database.SQLite;
import domain.AlteracaoPreco;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author rafael
 */
public class AlteracaoPrecoCRUD {

    public void inserirAlteracaoPreco(AlteracaoPreco alteracaoPreco) {

        PreparedStatement stmt;
        try {
            try (Connection conn = new SQLite().conectar()) {
                stmt = conn.prepareStatement("INSERT INTO alteracaoPreco(codAlteracaoPreco, codProduto, codFuncionario, "
                        + "dataAlteracao, precoVenda) VALUES (?,?,?,?,?);");

                stmt.setInt(1, alteracaoPreco.getCodAlteracaoPreco());
                stmt.setInt(2, alteracaoPreco.getCodProduto());
                stmt.setInt(3, alteracaoPreco.getCodFuncionario());
                stmt.setString(4, alteracaoPreco.getDataAlteracao());
                stmt.setDouble(5, alteracaoPreco.getPrecoVenda());
                stmt.executeUpdate();
                stmt.close();
            }
            JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso!");
        } catch (SQLException erroInserirCliente) {
            System.out.println(erroInserirCliente.getMessage());
        }
    }

    public ArrayList<AlteracaoPreco> consultarTodasAlteracoesPreco() {

        PreparedStatement stmt;
        ResultSet result;
        ArrayList<AlteracaoPreco> listaAlteracaoPreco = new ArrayList<>();
        try (Connection conn = new SQLite().conectar()) {
            stmt = conn.prepareStatement("SELECT codAlteracaoPreco, codProduto, codFuncionario, "
                    + "dataAlteracao, precoVenda;");
            result = stmt.executeQuery();
            while (result.next()) {
                AlteracaoPreco alteracaoPreco = new AlteracaoPreco();
                alteracaoPreco.setCodAlteracaoPreco(result.getInt("codCliente"));
                alteracaoPreco.setCodProduto(result.getInt("codProduto"));
                alteracaoPreco.setCodFuncionario(result.getInt("codFuncionario"));
                alteracaoPreco.setDataAlteracao(result.getString("dataAlteracao"));
                alteracaoPreco.setPrecoVenda(result.getDouble("precoVenda"));

                listaAlteracaoPreco.add(alteracaoPreco);
            }
            stmt.close();

            return listaAlteracaoPreco;
        } catch (SQLException erroConsultarListaAlteracaoPreco) {
            System.out.println(erroConsultarListaAlteracaoPreco.getMessage());
            return listaAlteracaoPreco;
        }
    }

    public void atualizarAlteracaoPreco(AlteracaoPreco alteracaoPreco) {

        PreparedStatement stmt;

        try (Connection conn = new SQLite().conectar()) {
            stmt = conn.prepareStatement("UPDATE alteracaoPreco SET codProduto = ?, codFuncionario = ?, "
                    + "dataAleteracao = ?, precoVenda = ? WHERE codAlteracao = ?;");

            stmt.setInt(1, alteracaoPreco.getCodProduto());
            stmt.setInt(2, alteracaoPreco.getCodFuncionario());
            stmt.setString(3, alteracaoPreco.getDataAlteracao());
            stmt.setDouble(4, alteracaoPreco.getPrecoVenda());
            stmt.setInt(6, alteracaoPreco.getCodAlteracaoPreco());

            stmt.executeUpdate();
            stmt.close();

            JOptionPane.showMessageDialog(null, "Alteracao de preco atualizada com sucesso!");
        } catch (SQLException erroAtualizaAlteracaoPreco) {
            System.out.println(erroAtualizaAlteracaoPreco.getMessage());
        }
    }

    public void deletarAlteracaoPreco(AlteracaoPreco alteracaoPreco) {

        PreparedStatement stmt;

        try (Connection conn = new SQLite().conectar()) {
            stmt = conn.prepareStatement("DELETE FROM alteracaoPreco WHERE codCliente = ?;");
            stmt.setInt(1, alteracaoPreco.getCodAlteracaoPreco());

            stmt.executeUpdate();
            stmt.close();

            JOptionPane.showMessageDialog(null, "Alteracao de preco deletada com sucesso!");
        } catch (SQLException erroDeletarAlteracaoPreco) {
            System.out.println(erroDeletarAlteracaoPreco.getMessage());
        }
    }

}
