
package crud;

import database.SQLite;
import domain.TituloCompra;
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
public class TituloCompraCRUD {
 
    //INSERT
    public void inserirTituloCompra(TituloCompra tituloCompra) {
        PreparedStatement stm;

        try (Connection conn = new SQLite().conectar()) {
            stm = conn.prepareStatement("INSERT INTO tituloCompra(codTitulo, codCompra,vencimentoTitulo, valorTitulo) "
                    + "VALUE (?,?,?,?);");

            stm.setInt(1, tituloCompra.getCodTitulo());
            stm.setInt(2, tituloCompra.getCodCompra());
            stm.setString(3, tituloCompra.getVencimentoTitulo());
            stm.setDouble(4, tituloCompra.getValorTitulo());
            stm.executeUpdate();
            stm.close();

            JOptionPane.showMessageDialog(null, "Cadastro do Titulo da compra Efetuada com Sucesso");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Inserir o Titulo da compra.");
        }
    }
    //UPDATE
    public void atualizarTituloCompra(TituloCompra tituloCompra) {
        PreparedStatement stm;

        try (Connection conn = new SQLite().conectar()) {
            stm = conn.prepareStatement("UPDATE TituloCompra SET codCompra = ? , vencimentoTitulo = ?, valorTitulo = ? "
                    + "WHERE codTitulo = ?;");

            stm.setInt(1, tituloCompra.getCodCompra());
            stm.setString(2, tituloCompra.getVencimentoTitulo());
            stm.setDouble(3, tituloCompra.getValorTitulo());
            stm.setInt(4, tituloCompra.getCodTitulo());
            stm.executeUpdate();
            stm.close();

            JOptionPane.showMessageDialog(null, "Atualização do titulo da compra Efetuada com Sucesso.");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Atualizar o titulo da compra.");
        }

    }
   //SELECT
    public ArrayList<TituloCompra> consultarTituloCompra() {
        PreparedStatement stm;
        ResultSet rs;
        ArrayList<TituloCompra> listaTituloCompra = new ArrayList<>();

        try (Connection conn = new SQLite().conectar()) {
            stm = conn.prepareStatement("SELECT codTitulo , codCompra, vencimentoTitulo, valorTitulo FROM TituloCompra;");

            rs = stm.executeQuery();

            while (rs.next()) {
                TituloCompra tituloCompra = new TituloCompra();

                tituloCompra.setCodTitulo(rs.getInt("codTitulo"));
                tituloCompra.setCodCompra(rs.getInt("codCompra"));
                tituloCompra.setVencimentoTitulo(rs.getString("vencimentoTitulo"));
                tituloCompra.setValorTitulo(rs.getDouble("valorTitulo"));

                listaTituloCompra.add(tituloCompra);
            }
            stm.close();
            return listaTituloCompra;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Consultar o titulo da compra.");
            return listaTituloCompra;
        }

    }
   //DELETE
    public void deletarTituloCompra(TituloCompra tituloCompra) {
        PreparedStatement stm;
        try (Connection conn = new SQLite().conectar()) {
            stm = conn.prepareStatement("DELETE FROM TituloCompra WHERE codTitulo = ? AND codCompra = ?;");
            
            stm.setInt(1, tituloCompra.getCodTitulo());
            stm.setInt(2, tituloCompra.getCodCompra());
            stm.executeUpdate();
            stm.close();
            
            JOptionPane.showMessageDialog(null, "Titulo da compra deletado com Sucesso");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao deletar o titulo da compra.");

        }

    }
}
