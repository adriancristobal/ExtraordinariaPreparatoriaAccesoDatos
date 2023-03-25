package org.example.config.file;

import org.yaml.snakeyaml.Yaml;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConfigYaml {

    private String User;
    private String Pass;

    private static ConfigYaml config;

    private ConfigYaml() {

    }

    public static ConfigYaml getInstance() {
        if (config == null) {
            try {
                Yaml yaml = new Yaml();
                InputStream in = Files.newInputStream(Paths.get("propertiesFiles/users.yaml"));
                // Parse the YAML document in a stream and produce the corresponding Java object.
                config = yaml.loadAs(in, ConfigYaml.class);

            } catch (FileNotFoundException ex) {
                Logger.getLogger(ConfigYaml.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(ConfigYaml.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return config;
    }


    public String getUser() {
        return User;
    }

    public void setUser(String User) {
        this.User = User;
    }

    public String getPass() {
        return Pass;
    }

    public void setPass(String Pass) {
        this.Pass = Pass;
    }

}
