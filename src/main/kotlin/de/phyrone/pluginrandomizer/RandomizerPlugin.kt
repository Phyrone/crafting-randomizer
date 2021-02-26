package de.phyrone.pluginrandomizer

import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.enchantments.Enchantment
import org.bukkit.entity.EntityType
import org.bukkit.event.Event
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.CraftItemEvent
import org.bukkit.event.player.PlayerMoveEvent
import org.bukkit.inventory.ItemStack
import org.bukkit.plugin.java.JavaPlugin
import java.util.*
import kotlin.random.Random

class RandomizerPlugin : JavaPlugin(), Listener {

    override fun onEnable() {
        Bukkit.getPluginManager().registerEvents(this, this)
    }

    @EventHandler
    fun onCraft(event: CraftItemEvent) {
        val random = Material.values().random()
        event.currentItem = ItemStack(
            random,
            if (random.maxStackSize > 1) Random.nextInt(1, random.maxStackSize) else 1
        ).also { item ->

            item.itemMeta = item.itemMeta?.also { meta ->
                val amount = Random.nextInt(-2 * Enchantment.values().size, Enchantment.values().size).coerceAtLeast(0)
                repeat(amount) {
                    meta.addEnchant(Enchantment.values().random(), Random.nextInt(1, 10), true)
                }

            }
        }

    }

}
