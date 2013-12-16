package com.bobacadodl.JSONChatLib;

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
        Object nmsPlayer = player.getClass().getMethod("getHandle").invoke(player);
        Object playerCon = nmsPlayer.getClass().getField("playerConnection").get(nmsPlayer);
        String nmsPath = "net.minecraft.server." + Bukkit.getServer().getClass().getPackage().getName().replace(".", ",").split(",")[3];
        Class<?> packetClass = Class.forName(nmsPath + ".PacketPlayOutChat");
        Object packet = packetClass.getConstructor(Class.forName(nmsPath + ".IChatBaseComponent"), boolean.class).newInstance("chatObject.toJSONString(), true);
        playerCon.getClass().getMethod("sendPacket", packet.getClass()).invoke(playerCon, packet);

    }

    public String toString() {
        return chatObject.toJSONString();
    }
}
