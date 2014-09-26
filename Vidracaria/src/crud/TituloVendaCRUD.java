
package crud;

import domain.TituloVenda;
import database.SQLite;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Adriano
 */
public class TituloVendaCRUD {

    //INSERT
    public void inserirTituloVenda(TituloVenda tituloVenda) {
        PreparedStatement stm;

        try (Connection conn = new SQLite().conectar()) {
            stm = conn.prepareStatement("INSERT INTO Titulovenda(codTitulo, codVenda,vencimentoTitulo, valorTitulo) "
                    + "VALUE (?,?,?,?);");

            stm.setInt(1, tituloVenda.getCodTitulo());
            stm.setInt(2, tituloVenda.getCodVenda());
            stm.setString(3, tituloVenda.getVencimentoTitulo());
            stm.setDouble(4, tituloVenda.getValorTitulo());
            stm.executeUpdate();
            stm.close();

            JOptionPane.showMessageDialog(null, "Cadastro do Titulo da venda Efetuada com Sucesso");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Inserir o Titulo da venda.");
        }
    }
    //UPDATE
    public void atualizarTituloVenda(TituloVenda tituloVenda) {
        PreparedStatement stm;

        try (Connection conn = new SQLite().conectar()) {
            stm = conn.prepareStatement("UPDATE TituloVenda SET codVenda = ? , vencimentoTitulo = ?, valorTitulo = ? "
                    + "WHERE codTitulo = ?;");

            stm.setInt(1, tituloVenda.getCodVenda());
            stm.setString(2, tituloVenda.getVencimentoTitulo());
            stm.setDouble(3, tituloVenda.getValorTitulo());
            stm.setInt(4, tituloVenda.getCodTitulo());
            stm.executeUpdate();
            stm.close();

            JOptionPane.showMessageDialog(null, "Atualização do Titulo da venda Efetuada com Sucesso.");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Atualizar o titulo da venda.");
        }

    }
   //SELECT
    public ArrayList<TituloVenda> consultarTituloVenda() {
        PreparedStatement stm;
        ResultSet rs;
        ArrayList<TituloVenda> listaTituloVenda = new ArrayList<>();

        try (Connection conn = new SQLite().conectar()) {
            stm = conn.prepareStatement("SELECT codTitulo , codVenda, vencimentoTitulo, valorTitulo FROM TituloVenda;");

            rs = stm.executeQuery();

            while (rs.next()) {
                TituloVenda tituloVenda = new TituloVenda();

                tituloVenda.setCodTitulo(rs.getInt("codTitulo"));
                tituloVenda.setCodVenda(rs.getInt("codVenda"));
                tituloVenda.setVencimentoTitulo(rs.getString("vencimentoTitulo"));
                tituloVenda.setValorTitulo(rs.getDouble("valorTitulo"));

                listaTituloVenda.add(tituloVenda);
            }
            stm.close();
            return listaTituloVenda;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Consultar o titulo da venda.");
            return listaTituloVenda;
        }

    }
   //DELETE
    public void deletarTituloVenda(TituloVenda tituloVenda) {
        PreparedStatement stm;
        try (Connection conn = new SQLite().conectar()) {
            stm = conn.prepareStatement("DELETE FROM TituloVenda WHERE codTitulo = ? AND codVenda = ?;");
            
            stm.setInt(1, tituloVenda.getCodTitulo());
            stm.setInt(2, tituloVenda.getCodVenda());
            stm.executeUpdate();
            stm.close();
            
            JOptionPane.showMessageDialog(null, "Titulo da venda Deletado com Sucesso");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao deletar o titulo da venda.");

        }

    }

}
