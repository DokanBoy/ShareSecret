package pw.zakharov.vkbot.command.sender;

import org.jetbrains.annotations.NotNull;

/**
 * Created by: Alexey Zakharov <alexey@zakharov.pw>
 * Date: 16.07.2020 22:12
 */
public interface Sender {

    @NotNull String getName();

    @NotNull Integer getVkId();

    void reply(@NotNull String message);

}
