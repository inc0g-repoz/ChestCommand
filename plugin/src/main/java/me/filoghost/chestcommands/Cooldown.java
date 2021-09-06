package me.filoghost.chestcommands;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.entity.Player;

import me.filoghost.chestcommands.menu.InternalMenu;

public class Cooldown {

    private HashMap<UUID, Long> cooldowns = new HashMap<>();

    public int timeLeft(UUID uniqueId, InternalMenu menu) {
        int cooldown = menu.getCooldown();
        if (cooldown == 0) {
            return 0;
        }
        long now = System.currentTimeMillis();
        if (!cooldowns.containsKey(uniqueId)) {
            cooldowns.put(uniqueId, now);
            return 0;
        }
        long then = cooldowns.get(uniqueId);
        int timeLeft = menu.getCooldown() - (int) ((now - then) / 1000);
        if (timeLeft < 1) {
            cooldowns.put(uniqueId, now);
        	return 0;
        }
        return timeLeft;
    }

    public int timeLeft(Player player, InternalMenu menu) {
        return timeLeft(player.getUniqueId(), menu);
    }

}
