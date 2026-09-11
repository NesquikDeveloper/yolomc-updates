/* Porra de Copyright carai */

package com.minecraft.core.bukkit.event.cooldown;

import com.minecraft.core.bukkit.event.handler.CooldownEvent;
import com.minecraft.core.bukkit.util.cooldown.type.Cooldown;
import org.bukkit.entity.Player;

public class CooldownStopEvent extends CooldownEvent {

    public CooldownStopEvent(Player player, Cooldown cooldown) {
        super(player, cooldown);
    }

}