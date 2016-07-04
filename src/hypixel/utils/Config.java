package hypixel.utils;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Niels on 04-07-16.
 */
public class Config {

    private static List<RConfig> configs = new ArrayList();

    public static boolean registerConfig(String paramString1, String paramString2, JavaPlugin paramJavaPlugin)
    {
        File localFile = new File(paramJavaPlugin.getDataFolder(), paramString2);
        if (!localFile.exists())
        {
            localFile.getParentFile().mkdirs();
            try
            {
                copy(paramJavaPlugin.getResource(paramString2), localFile);
            }
            catch (Exception localException) {}
        }
        RConfig localRConfig1 = new RConfig(paramString1, localFile);
        for (RConfig localRConfig2 : configs) {
            if (localRConfig2.equals(localRConfig1)) {
                return false;
            }
        }
        configs.add(localRConfig1);
        return true;
    }

    public static boolean unregisterConfig(String paramString)
    {
        return configs.remove(getConfig(paramString));
    }

    public static RConfig getConfig(String paramString)
    {
        for (RConfig localRConfig : configs) {
            if (localRConfig.getConfigId().equalsIgnoreCase(paramString)) {
                return localRConfig;
            }
        }
        return null;
    }

    public static boolean save(String paramString)
    {
        RConfig localRConfig = getConfig(paramString);
        if (localRConfig == null) {
            return false;
        }
        try
        {
            localRConfig.save();
        }
        catch (Exception localException)
        {
            print("An error occurred while saving a config with id " + paramString);
            localException.printStackTrace();
            return false;
        }
        return true;
    }

    public static boolean saveAll()
    {
        try
        {
            for (RConfig localRConfig : configs) {
                localRConfig.save();
            }
        }
        catch (Exception localException)
        {
            print("An error occurred while saving all configs");
            localException.printStackTrace();
            return false;
        }
        return true;
    }

    public static boolean load(String paramString)
    {
        RConfig localRConfig = getConfig(paramString);
        if (localRConfig == null) {
            return false;
        }
        try
        {
            localRConfig.load();
        }
        catch (Exception localException)
        {
            print("An error occurred while loading a config with id " + paramString);
            localException.printStackTrace();
            return false;
        }
        return true;
    }

    public static boolean loadAll()
    {
        try
        {
            for (RConfig localRConfig : configs) {
                localRConfig.load();
            }
        }
        catch (Exception localException)
        {
            print("An error occurred while loading all configs");
            localException.printStackTrace();
            return false;
        }
        return true;
    }

    public static void clear(String paramString)
    {
        RConfig localRConfig = getConfig(paramString);
        if (localRConfig == null) {
            return;
        }
        configs.remove(localRConfig);
        configs.add(new RConfig(localRConfig.getConfigId(), localRConfig.getFile()));
    }

    private static void print(String paramString)
    {
        System.out.println("Config: " + paramString);
    }

    public static class RConfig
            extends YamlConfiguration
    {
        private String id;
        private File file;

        public String getConfigId()
        {
            return this.id;
        }

        public File getFile()
        {
            return this.file;
        }

        private RConfig(String paramString, File paramFile)
        {
            this.id = paramString;
            this.file = paramFile;
        }

        public void save()
        {
            try {
                save(this.file);
            }catch (Exception e) {

            }
        }

        public void load()
        {
            try {
                load(this.file);
            }catch (Exception e) {

            }
        }

        public boolean equals(RConfig paramRConfig)
        {
            return paramRConfig.getConfigId().equalsIgnoreCase(this.id);
        }
    }

    private static void copy(InputStream paramInputStream, File paramFile)
    {
        try {
            FileOutputStream localFileOutputStream = new FileOutputStream(paramFile);
            byte[] arrayOfByte = new byte['`'];
            int i;
            while ((i = paramInputStream.read(arrayOfByte)) > 0) {
                localFileOutputStream.write(arrayOfByte, 0, i);
            }
            localFileOutputStream.close();
            paramInputStream.close();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
