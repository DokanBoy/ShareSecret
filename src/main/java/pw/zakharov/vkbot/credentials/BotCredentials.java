package pw.zakharov.vkbot.credentials;

import lombok.Data;

/**
 * @author Alexey Zakharov
 * @date 31.05.2020
 */

@Data
public class BotCredentials {

    private final int groupId;
    private final String accessToken;

}
