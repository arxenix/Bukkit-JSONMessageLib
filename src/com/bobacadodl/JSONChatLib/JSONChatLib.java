package com.bobacadodl.JSONChatLib;

import net.minecraft.server.v1_6_R3.Packet3Chat;
import org.bukkit.craftbukkit.v1_6_R3.entity.CraftPlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;

/**
 * User: bobacadodl
 * Date: 9/28/13
 * Time: 7:55 PM
 */
public class JSONChatLib extends JavaPlugin implements Listener {
    public void onEnable() {
        this.getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Packet3Chat packet = new Packet3Chat("{\"text\":\"Hi\",\"color\":\"white\",\"extra\":[{\"text\":\"<Clik It>\",\"color\":\"yellow\",\"bold\":\"false\",\"italic\":\"true\",\"underlined\":\"false\",\"strikethrough\":\"false\",\"obfuscated\":\"false\",\"clickEvent\":{\"action\":\"suggest_command\",\"value\":\"/achievement give <stat_name> [player]\"}}]}", true);
        ((CraftPlayer) event.getPlayer()).getHandle().playerConnection.sendPacket(packet);
        JSONChatMessage message = new JSONChatMessage("Hey, ", JSONChatColor.AQUA, null);
        JSONChatExtra extra = new JSONChatExtra("<Click This>", JSONChatColor.BLUE, Arrays.asList(JSONChatFormat.BOLD));
        extra.setHoverEvent(JSONChatHoverEventType.SHOW_TEXT, "Hover Textx");
        message.addExtra(extra);
        message.sendToPlayer(event.getPlayer());
    }
}
