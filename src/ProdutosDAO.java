/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class ProdutosDAO {
    
    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    
    public void cadastrarProduto (ProdutosDTO produto){
         
        
        conn = new conectaDAO().connectDB();
        
        try {
            prep = conn.prepareStatement(" INSERT INTO produtos (nome, valor, status) VALUES (?,?,?)");
            prep.setString(1, produto.getNome());
            prep.setInt(2, produto.getValor());
            prep.setString(3, produto.getStatus());
            prep.executeUpdate();
            
        } catch (SQLException ex) {
            System.out.println("Erro ao conectar: " + ex.getMessage());
             ex.getErrorCode();
        }
       
        
        
    }
    
    public ArrayList<ProdutosDTO> listarProdutos(){
        ArrayList<ProdutosDTO> lista = new ArrayList<>();
    String sql = "SELECT * FROM produtos";
    try {
        conn = new conectaDAO().connectDB();
        prep = conn.prepareStatement(sql);
        resultset = prep.executeQuery();

        while (resultset.next()) {
            ProdutosDTO p = new ProdutosDTO();
            p.setId(resultset.getInt("id"));
            p.setNome(resultset.getString("nome"));
            p.setValor(resultset.getInt("valor"));
            p.setStatus(resultset.getString("status"));
            lista.add(p);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
        return lista;
    }
    
    public int venderProduto(int id){
        
        conn = new conectaDAO().connectDB();
        
        try {
            prep = conn.prepareStatement("UPDATE produtos SET status = 'Vendido' WHERE id = ?");
            prep.setInt(1, id);
            return prep.executeUpdate();
            
        } catch (SQLException ex) {
            System.out.println(ex.getErrorCode());
            return ex.getErrorCode();
        }
                
        
    }
            
    public ArrayList<ProdutosDTO> listarProdutosVendidos(){
        ArrayList<ProdutosDTO> listaVendidos = new ArrayList<>();
    String sql = "SELECT * FROM produtos where status = 'Vendido'";
    try {
        conn = new conectaDAO().connectDB();
        prep = conn.prepareStatement(sql);
        resultset = prep.executeQuery();

        while (resultset.next()) {
            ProdutosDTO p = new ProdutosDTO();
            p.setId(resultset.getInt("id"));
            p.setNome(resultset.getString("nome"));
            p.setValor(resultset.getInt("valor"));
            p.setStatus(resultset.getString("status"));
            listaVendidos.add(p);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
        return listaVendidos;
    }
    
}

