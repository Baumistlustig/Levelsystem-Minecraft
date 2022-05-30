package eu.baumistlustig.levelsystem.events;

import eu.baumistlustig.levelsystem.utils.HttpRequest;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.json.JSONObject;

import java.io.IOException;
public class messageEvents implements Listener {

    @EventHandler(ignoreCancelled = true)
    public void onAsyncPlayerChat(AsyncPlayerChatEvent e) throws IOException {
        Player p = e.getPlayer();

        JSONObject payload1 = new JSONObject();

        payload1.put("minecraft_id", p.getUniqueId());

        HttpRequest httpRequest = new HttpRequest();

        JSONObject payload = new JSONObject();
        payload.put("author_id", httpRequest.request("GET", "http://localhost:8090/api/getDiscord", payload1));
        payload.put("token", "IS}pYBNAfj[UZ7Y$B<9f}}k6OlDvtDk|:e^6@ee<)ctzObDe_SC:$`uE%uW;:YssJu3ys;rjrXqrd3RsSJP_fmW|sp8LJ>&T>B5:p-HfB;2=_Dj+QN0pDa*Kp$yvd*.FgC,uS[chUbb'sE(es66{Jm,oFk}D>%nnFult=(~\\WL%5'&ou\"9xY01%~)b5RJ>%JpI&;N\\Ntf*a@|F{($^M4sflUX@y}j\\nQbeOd2db52Vjw=T7&Zhy'/0W=]='|3GFt4G");
        payload.put("author", "undefined");

        httpRequest.request("POST", "http://localhost:8090/api/message", payload);
    }
}
