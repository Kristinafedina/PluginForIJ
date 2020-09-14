import com.intellij.ide.BrowserUtil;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import org.jetbrains.annotations.NotNull;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class TweetAction extends AnAction {

    @Override
    public void actionPerformed(@NotNull AnActionEvent event ) {

        Editor editor = event.getData(PlatformDataKeys.EDITOR);
        String selectedText = editor.getSelectionModel().getSelectedText();
        String encoded = "";

        if(selectedText != null) {
            try {
                encoded = URLEncoder.encode(selectedText, StandardCharsets.UTF_8.toString());
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

            String url = String.format("https://twitter.com/intent/tweet?text=%s", encoded);

            BrowserUtil.browse(url);
        } else{
            Messages.showMessageDialog("Selection is empty", "Tweet Action", Messages.getInformationIcon());
        }
    }

    @Override
    public boolean isDumbAware() {
        return false;
    }
}
