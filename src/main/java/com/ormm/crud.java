/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ormm;

import java.util.List;


/**
 *
 * @author ASUS
 */
public interface crud {
    public void create();
    public void read();
    public void store();
    public void update(int id,List<Object> data);
    public void delete(int id);
}
