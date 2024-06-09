package org.plugin.purpur;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.Bukkit;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public final class Purworld extends JavaPlugin implements Listener, CommandExecutor {
    @Override
    public void onEnable() {
        // Registrieren Sie diese Klasse als Event-Listener
        getServer().getPluginManager().registerEvents(this, this);

        // Registrieren Sie den neuen Befehl
        this.getCommand("whoisonline").setExecutor(this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    // Event-Handler-Methode
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        // Senden Sie eine Nachricht an den Spieler, wenn er dem Server beitritt
        event.getPlayer().sendMessage("Willkommen auf dem Server!");
    }

    @EventHandler
    public void onPlayerAttack(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Player && event.getEntity() instanceof Player) {
            Player attacked = (Player) event.getEntity();
            Player attacker = (Player) event.getDamager();

            attacked.sendMessage("Du wirst von " + attacker.getName() + " angegriffen!");
            attacker.sendMessage("Du greifst " + attacked.getName() + " an!");
        }
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event) {
        for (Player player : Bukkit.getServer().getOnlinePlayers()) {
            // Hier können Sie Aktionen mit jedem Spieler ausführen
            Bukkit.getServer().broadcastMessage(player.getName() + " ist online!");
        }
    }

    @EventHandler
    public void onPlayerSendMessage(AsyncPlayerChatEvent event) {
        String message = event.getMessage();
        Player player = event.getPlayer();

        if (message.equalsIgnoreCase("whoisonline")) {
            StringBuilder onlinePlayers = new StringBuilder("Online Spieler: ");
            for (Player onlinePlayer : Bukkit.getServer().getOnlinePlayers()) {
                onlinePlayers.append(onlinePlayer.getName()).append(", ");
            }

            // Entferne das letzte Komma und Leerzeichen
            if (onlinePlayers.length() > 16) {
                onlinePlayers.setLength(onlinePlayers.length() - 2);
            }

            player.sendMessage(onlinePlayers.toString());
            event.setCancelled(true); // Verhindern, dass die Nachricht im Chat angezeigt wird
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("whoisonline")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                StringBuilder onlinePlayers = new StringBuilder("Online Spieler: ");
                for (Player onlinePlayer : Bukkit.getServer().getOnlinePlayers()) {
                    onlinePlayers.append(onlinePlayer.getName()).append(", ");
                }

                // Entferne das letzte Komma und Leerzeichen
                if (onlinePlayers.length() > 16) {
                    onlinePlayers.setLength(onlinePlayers.length() - 2);
                }

                player.sendMessage(onlinePlayers.toString());
            } else {
                sender.sendMessage("Dieser Befehl kann nur von einem Spieler ausgeführt werden.");
            }
            return true;
        }
        return false;
    }

}
