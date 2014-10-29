package crud;

import database.SQLite;
import domain.Parcelamento;
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
public class ParcelamentoCompraCRUD {

    // INSERT 
    public void inserirParcelamento(Parcelamento parcelamento) {

        PreparedStatement stmt;

        try (Connection conn = new SQLite().conectar()) {
            stmt = conn.prepareStatement("INSERT INTO parcelamentoCompra(codParcelamento, descricaoParcelamento, "
                    + "quantidadeParcelas VALUES (?,?,?);");

            stmt.setInt(1, parcelamento.getCodParcelamento());
            stmt.setString(2, parcelamento.getDescricaoParcelamento());
            stmt.setInt(3, parcelamento.getQuantidadeParcelas());

            stmt.executeUpdate();
            stmt.close();

            System.out.println("Parcelamento cadastrado com sucesso!");
        } catch (SQLException erroInserirParcelamento) {
            System.out.println(erroInserirParcelamento.getMessage());
        }
    }

    // UPDATE
    public void atualizarParcelamento(Parcelamento parcelamento) {

        PreparedStatement stmt;
        try (Connection conn = new SQLite().conectar()) {
            stmt = conn.prepareStatement("UPDATE parcelamentoCompra SET descricao = ?, quantidadeParcelas = ? "
                    + "WHERE codParcelamento = ?;");

            stmt.setString(1, parcelamento.getDescricaoParcelamento());
            stmt.setInt(2, parcelamento.getQuantidadeParcelas());
            stmt.setInt(3, parcelamento.getCodParcelamento());

            stmt.executeUpdate();
            stmt.close();
            conn.close();
            System.out.println("Informações atualizadas com sucesso!");
        } catch (SQLException erroAtualizarParcelamento) {
            System.out.println(erroAtualizarParcelamento.getMessage());
        }
    }

    // SELECT
    public ArrayList<Parcelamento> consultarParcelamento() {

        PreparedStatement stmt;
        ResultSet result;

        ArrayList<Parcelamento> listaParcelamento = new ArrayList<>();

        try (Connection conn = new SQLite().conectar()) {

            stmt = conn.prepareStatement("SELECT codParcelamento, descricao, quantidadeParcelas "
                    + "FROM parcelamentoCompra;");

            result = stmt.executeQuery();
            
            while (result.next()) {
                Parcelamento parcelamento = new Parcelamento();

                parcelamento.setCodParcelamento(result.getInt("codParcelamento"));
                parcelamento.setDescricaoParcelamento(result.getString("descricao"));
                parcelamento.setQuantidadeParcelas(result.getInt("quantidadeParcelas"));

                listaParcelamento.add(parcelamento);
            }
            stmt.close();
            
            return listaParcelamento;
        } catch (SQLException erroConsultarParcelamento) {
            System.out.println(erroConsultarParcelamento.getMessage());
            return listaParcelamento;
        }
    }

    // SELECT (Quantidade de parcelas)
    public int consultarQuantidadeParcelas(String descricao) {

        PreparedStatement stmt;
        ResultSet result;

        Parcelamento parcelamento = new Parcelamento();

        try (Connection conn = new SQLite().conectar()) {
            stmt = conn.prepareStatement("SELECT quantidadeParcelas FROM parcelamentoCompra WHERE descricao = '" + descricao + "';");

            result = stmt.executeQuery();
            if (result.next()) {
                parcelamento.setQuantidadeParcelas(result.getInt("quantidadeParcelas"));

                stmt.close();
                conn.close();
            }
        } catch (SQLException erroConsultarQuantidadeParcelas) {
            System.out.println(erroConsultarQuantidadeParcelas.getMessage());
        }
        return parcelamento.getQuantidadeParcelas();
    }
    
     // pesquisa de parcelamento pela descrição
    public Parcelamento ConsultarCodParcelamento(String descricao) {
        PreparedStatement stmt;
        ResultSet result;

        Parcelamento parcelamento = new Parcelamento();

        try (Connection conn = new SQLite().conectar()) {

            stmt = conn.prepareStatement("SELECT codParcelamento, quantidadeParcelas "
                    + "FROM parcelamentoCompra WHERE descricaoParcelamento = '" + descricao + "';");

            result = stmt.executeQuery();
            if (result.next()) {

                parcelamento.setCodParcelamento(result.getInt("codParcelamento"));
                parcelamento.setQuantidadeParcelas(result.getInt("quantidadeParcelas"));

                return parcelamento;
            }

        } catch (SQLException erroConsultarCodigoParcelamento) {
            JOptionPane.showMessageDialog(null, erroConsultarCodigoParcelamento.getSQLState());
        }

        return parcelamento;
    }    
}
