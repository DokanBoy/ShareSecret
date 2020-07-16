package pw.zakharov.vkbot.utils;

import com.petersamokhin.vksdk.core.model.objects.Keyboard;
import lombok.experimental.UtilityClass;

/**
 * Created by: Alexey Zakharov <alexey@zakharov.pw>
 * Date: 16.07.2020 21:50
 */
@UtilityClass
public class KeyboardUtil {

    public Keyboard.Button createButton(String label) {
        return new Keyboard.Button(
            new Keyboard.Button.Action(
                Keyboard.Button.Action.Type.TEXT,
                label,
                null, null, null, null, null),
            Keyboard.Button.Color.SECONDARY
        );
    }

}
