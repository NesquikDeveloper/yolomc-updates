/* Porra de Copyright carai */

package com.minecraft.core.bukkit.event.cooldown;

import com.minecraft.core.bukkit.util.cooldown.type.Cooldown;
import org.bukkit.entity.Player;

public class CooldownFinishEvent extends CooldownStopEvent {

    public CooldownFinishEvent(Player player, Cooldown cooldown) {
        super(player, cooldown);
    }

}