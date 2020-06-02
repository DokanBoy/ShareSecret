package pw.zakharov.vkbot.config;

import com.typesafe.config.Config;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * @author Alexey Zakharov
 * @date 26.04.2020
 */
public interface Configuration {

    Config getHandle();

    @Nullable
    default String getString(@NotNull String path) {
        return getString(path, null);
    }

    String getString(@NotNull String path, String def);

    default int getInt(@NotNull String path) {
        return getInt(path, 0);
    }

    int getInt(@NotNull String path, int def);

    List<String> getStringList(@NotNull String path);

    List<Integer> getIntegerList(@NotNull String path);

}
