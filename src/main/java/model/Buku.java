/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import com.ormm.crud;
import com.ormm.database;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ASUS
 */
public class Buku extends database implements crud {
    private int id;
    private String nama;
    private int tahun;
    private String pembuat;
    private ArrayList<Buku> listBuku = new ArrayList<>();

    // Constructor untuk inisialisasi objek buku
    public Buku(int id, String nama, int tahun, String pembuat) {
        super();
        this.id = id;
        this.nama = nama;
        this.tahun = tahun;
        this.pembuat = pembuat;
    }
    
    //loading data
    public Buku() {this.store();}

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public int getTahun() {
        return tahun;
    }

    public void setTahun(int tahun) {
        this.tahun = tahun;
    }

    public String getPembuat() {
        return pembuat;
    }

    public void setPembuat(String pembuat) {
        this.pembuat = pembuat;
    }

    public ArrayList<Buku> getListBuku() {
        return listBuku;
    }

    public int getId() {
        return id;
    }
    
    @Override
    public void create() {
        try {            
            // Query untuk menambahkan data buku baru ke database
            PreparedStatement pstmt = con.prepareStatement("INSERT INTO buku (nama, tahun, pembuat) VALUES (?, ?, ?)");
            pstmt.setString(1, getNama());
            pstmt.setInt(2, getTahun());
            pstmt.setString(3, getPembuat());

            // Eksekusi query
            int rowsInserted = pstmt.executeUpdate();
            
            if (rowsInserted > 0) {
                System.out.println("Data buku berhasil ditambahkan.");
            }

        } catch (SQLException ex) {
            System.out.println("Error saat menambahkan data: " + ex.getMessage());
        }
    }

    @Override
    public void read() {        
        // melakukan perulangan dari Arraylist
        for (Buku object : listBuku) {
            System.out.println("ID : "+object.getId());
            System.out.println("Nama buku : "+object.getNama());
            System.out.println("Tahun Terbit : "+object.getTahun());
            System.out.println("Pembuat : "+object.getPembuat());
            System.out.println("=============================================");
        }
    }

    @Override
    public void update(int id, List<Object> data) {
        
        try {       
            String query = "UPDATE buku SET nama = ?, tahun = ?, pembuat = ? WHERE id = ?";
            PreparedStatement pstmt = con.prepareStatement(query);
            System.out.println("haloooo");
            
            for (Buku object : listBuku) {
                if (object.getId() == id) {
        
                    object.setNama((String) data.get(0));
                    object.setTahun((int) data.get(1));
                    object.setPembuat((String) data.get(2));
                    
                    pstmt.setString(1, object.getNama());
                    pstmt.setInt(2, object.getTahun());
                    pstmt.setString(3, object.getPembuat());
                    pstmt.setInt(4, id);
                    
                    int rowsUpdated = pstmt.executeUpdate();
            
                    if (rowsUpdated > 0) {
                        System.out.println("Data buku berhasil diperbarui.");
                    } else {
                        System.out.println("Data buku dengan ID " + id + " tidak ditemukan.");
                    }

                }
            }
        } catch (SQLException ex) {
            System.out.println("Error : " + ex);
        }

    }

    @Override
    public void delete(int id) {
        try {
            // Query untuk menghapus buku berdasarkan ID
            String query = "DELETE FROM buku WHERE id = ?";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setInt(1, id);

            // Eksekusi query
            int rowsDeleted = pstmt.executeUpdate();
            
            if (rowsDeleted > 0) {
                System.out.println("Data buku berhasil dihapus.");
            } else {
                System.out.println("Data buku dengan ID " + id + " tidak ditemukan.");
            }

        } catch (SQLException ex) {
            System.out.println("Error saat menghapus data: " + ex.getMessage());
        }
    }

    @Override
    public void store() {
        try {
            listBuku.clear();
            
            // Query untuk membaca semua data dari tabel buku
            ResultSet rs = st.executeQuery("SELECT * FROM buku");
            
            // membuat objek dari setiap baris data di database
            while (rs.next()) {
                int id = rs.getInt("id");
                String judul = rs.getString("nama");
                int tahun = rs.getInt("tahun");
                String pembuat = rs.getString("pembuat");
                listBuku.add(new Buku(id, judul, tahun, pembuat));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Buku.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

