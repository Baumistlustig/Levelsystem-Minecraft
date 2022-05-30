package eu.baumistlustig.levelsystem.events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class messageEvents implements Listener {

    private String getDiscordId(Player p) throws IOException {

        JSONObject payload = new JSONObject();

        payload.put("minecraft_id", p.getUniqueId());

        String url = "http://localhost:8090/api/getDiscord";
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        // Setting basic post request
        con.setRequestMethod("GET");
        con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
        con.setRequestProperty("Content-Type","application/json");

        // String postJsonData = "{\n\"minecraft_id\": " + p.getUniqueId().toString() + "\n}";

        // Send post request
        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(payload.toString());
        wr.flush();
        wr.close();

        int responseCode = con.getResponseCode();

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String output;
        StringBuffer response = new StringBuffer();

        while ((output = in.readLine()) != null) {
            response.append(output);
        }
        in.close();
        Bukkit.getLogger().info(response.toString());

        Bukkit.getLogger().info(con.getResponseCode() + " " + con.getResponseMessage());

        con.disconnect();

        return response.toString();
    }

    private void sendMessage(String discord_id) throws IOException {
        JSONObject payload = new JSONObject();

        String token = "IS}pYBNAfj[UZ7Y$B<9f}}k6OlDvtDk|:e^6@ee<)ctzObDe_SC:$`uE%uW;:YssJu3ys;rjrXqrd3RsSJP_fmW|sp8LJ>&T>B5:p-HfB;2=_Dj+QN0pDa*Kp$yvd*.FgC,uS[chUbb'sE(es66{Jm,oFk}D>%nnFult=(~\\WL%5'&ou\"9xY01%~)b5RJ>%JpI&;N\\Ntf*a@|F{($^M4sflUX@y}j\\nQbeOd2db52Vjw=T7&Zhy'/0W=]='|3GFt4G";

        payload.put("author_id", discord_id);
        payload.put("token", token);
        payload.put("author", "undefined");

        String url = "http://localhost:8090/api/message";
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        // Setting basic post request
        con.setRequestMethod("POST");
        con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
        con.setRequestProperty("Content-Type","application/json");

        // String postJsonData = "{\n\"minecraft_id\": " + p.getUniqueId().toString() + "\n}";

        // Send post request
        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(payload.toString());
        wr.flush();
        wr.close();

        int responseCode = con.getResponseCode();

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String output;
        StringBuffer response = new StringBuffer();

        while ((output = in.readLine()) != null) {
            response.append(output);
        }
        in.close();
        Bukkit.getLogger().info(response.toString());

        Bukkit.getLogger().info(con.getResponseCode() + " " + con.getResponseMessage());

        con.disconnect();
    }

    @EventHandler(ignoreCancelled = true)
    public void onAsyncPlayerChat(AsyncPlayerChatEvent e) throws IOException {
        Player p = e.getPlayer();

        sendMessage(getDiscordId(p));
    }
}
