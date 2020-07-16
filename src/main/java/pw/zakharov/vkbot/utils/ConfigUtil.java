package pw.zakharov.vkbot.utils;

import com.google.common.io.Resources;
import lombok.experimental.UtilityClass;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by: Alexey Zakharov <alexey@zakharov.pw>
 * Date: 14.07.2020 1:34
 */
@UtilityClass
public class ConfigUtil {

    private final Logger log = LoggerFactory.getLogger(ConfigUtil.class);

    public void saveDefaultConfig() throws IOException {
        FileOutputStream outputStream = new FileOutputStream(new File("config.conf"));

        saveResource("config.conf", outputStream);
    }

    public void saveResource(@NotNull final String fileName,
                             @NotNull final OutputStream outputStream) throws IOException {
        Resources.copy(Resources.getResource(fileName), outputStream);
    }

}
