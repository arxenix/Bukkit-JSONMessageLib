package com.bobacadodl.JSONChatLib;

import net.minecraft.server.v1_6_R3.Packet3Chat;
import org.bukkit.craftbukkit.v1_6_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.List;

/**
 * User: bobacadodl
 * Date: 10/27/13
 * Time: 8:03 PM
 */
public class JSONChatMessage {
    private JSONObject chatObject;

    public JSONChatMessage(String text, JSONChatColor color, List<JSONChatFormat> formats) {
        chatObject = new JSONObject();
        chatObject.put("text", text);
        if (color != null) {
            chatObject.put("color", color.getColorString());
        }
        if (formats != null) {
            for (JSONChatFormat format : formats) {
                chatObject.put(format.getFormatString(), true);
            }
        }
    }

    public void addExtra(JSONChatExtra extraObject) {
        if (!chatObject.containsKey("extra")) {
            chatObject.put("extra", new JSONArray());
        }
        JSONArray extra = (JSONArray) chatObject.get("extra");
        extra.add(extraObject.toJSON());
        chatObject.put("extra", extra);
    }

    public void sendToPlayer(Player player) {
        //Bukkit.getLogger().info(chatObject.toJSONString());
        //Packet3Chat packet = new Packet3Chat(chatObject.toJSONString(), true);
        //((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
        
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(new PacketPlayOutChat(ChatSerializer.a(chatObject.toJSONString()), true));

    }

    public String toString() {
        return chatObject.toJSONString();
    }
}
