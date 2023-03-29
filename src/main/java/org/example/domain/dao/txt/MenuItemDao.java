package org.example.domain.dao.txt;

import org.example.config.file.ConfigProperties;
import org.example.model.txt.MenuItemTXT;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MenuItemDao {

    public MenuItemTXT get(int id) {
        File file = new File(ConfigProperties.getInstance().getProperty("MenuItemFile"));
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String st;
            while ((st = br.readLine()) != null) {
                MenuItemTXT menuItem = new MenuItemTXT(st);
                if (menuItem.getId() == id) {
                    return menuItem;
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(MenuItemDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
