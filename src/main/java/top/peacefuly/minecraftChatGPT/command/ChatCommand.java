package top.peacefuly.minecraftChatGPT.command;

import com.plexpt.chatgpt.ChatGPT;
import com.plexpt.chatgpt.entity.chat.ChatCompletion;
import com.plexpt.chatgpt.entity.chat.ChatCompletionResponse;
import com.plexpt.chatgpt.entity.chat.Message;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import top.peacefuly.minecraftChatGPT.MinecraftChatGPT;

import java.util.Arrays;

public class ChatCommand implements CommandExecutor {
    private final MinecraftChatGPT plugin;
    public ChatCommand(MinecraftChatGPT plugin) {
        this.plugin = plugin;
    }



    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        String apikey = this.plugin.getConfig().getString("apikey");
        String apihost = this.plugin.getConfig().getString("apihost");
        String gptModel = this.plugin.getConfig().getString("gpt-model");
        int maxtokens = this.plugin.getConfig().getInt("max-tokens");
        int timeout = this.plugin.getConfig().getInt("timeout");
        if (apikey == null || apikey.isEmpty() || apikey.equals("sk-xxxxxx")) {
            commandSender.sendMessage("Please set apikey in config.yml");
            return true;
        }

        ChatGPT chatGPT = ChatGPT.builder()
                .apiKey(apikey)
                .apiHost(apihost)
                .timeout(timeout)
                .build()
                .init();


        if (commandSender instanceof Player p){
            String question = String.join(" ", strings);
            Message message = Message.of(question);
            ChatCompletion chatCompletion = ChatCompletion.builder()
                    .model(gptModel)
                    .messages(Arrays.asList(message))
                    .maxTokens(maxtokens)
                    .stream(false)
                    .build();
            ChatCompletionResponse response = chatGPT.chatCompletion(chatCompletion);
            p.sendMessage(response.getChoices().get(0).getMessage().getContent());
        }


        return true;
    }
}
