package pw.zakharov.vkbot.config;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigException;
import com.typesafe.config.ConfigFactory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;

/**
 * @author Alexey Zakharov
 * @date 26.04.2020
 */
public class HoconConfiguration implements Configuration {

    private static final Logger log = LoggerFactory.getLogger(HoconConfiguration.class);

    private final Path path;
    private Config handle;

    public HoconConfiguration(Path dataFolder, String fileName, boolean isResource) {
        boolean notExistsDataFolder = Files.notExists(dataFolder);
        if (notExistsDataFolder) {
            try {
                Files.createDirectory(dataFolder);
            } catch (IOException e) {
                throw new RuntimeException("Exception due to create directory", e);
            }
        }
        path = dataFolder.resolve(fileName);
        if (notExistsDataFolder || Files.notExists(dataFolder)) {
            if (isResource) {
                try {
                    Files.copy(getClass().getResourceAsStream("/config.conf"), path);
                } catch (IOException e) {
                    log.warn("Exception due to copy resource", e);
                }
            } else {
                try {
                    Files.createFile(dataFolder);
                } catch (IOException e) {
                    log.warn("Exception due to create file", e);
                }
            }
        }
        loadFile();
    }

    private void loadFile() {
        try (BufferedReader bufferedReader = Files.newBufferedReader(path)) {
            handle = ConfigFactory.parseReader(bufferedReader).resolve();
        } catch (IOException e) {
            log.error("Exception due to create BufferedReader", e);
        }
    }

    @NotNull
    @Override
    public Config getHandle() {
        return handle;
    }

    @Nullable
    @Override
    public String getString(@NotNull String path, String def) {
        try {
            return handle.getString(path);
        } catch (ConfigException.Missing e) {
            log.info("Path " + path + " not found");
            return def;
        }
    }

    @Override
    public int getInt(@NotNull String path, int def) {
        try {
            return handle.getInt(path);
        } catch (ConfigException.Missing e) {
            log.info("Path " + path + " not found");
            return def;
        }
    }

    @Override
    public List<String> getStringList(@NotNull String path) {
        try {
            return handle.getStringList(path);
        } catch (ConfigException.Missing e) {
            log.info("Path " + path + " not found");
            return Collections.singletonList("Error");
        }
    }

    @Override
    public List<Integer> getIntegerList(@NotNull String path) {
        try {
            return handle.getIntList(path);
        } catch (ConfigException.Missing e) {
            log.info("Path " + path + " not found");
            return Collections.singletonList(-1);
        }
    }

}
