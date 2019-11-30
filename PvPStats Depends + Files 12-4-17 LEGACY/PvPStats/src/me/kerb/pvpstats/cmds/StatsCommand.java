package me.kerb.pvpstats.cmds;

import me.kerb.pvpstats.connection.MySQLManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.sql.ResultSet;
import java.sql.SQLException;


public class StatsCommand implements CommandExecutor, Listener {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
        sender.sendMessage("Console wtf.");
        } else {
            if (cmd.getName().equalsIgnoreCase("stats")) {
                if (args.length == 0) {
                    ResultSet rs = MySQLManager.getResult("SELECT * FROM stats WHERE player_name = '" + sender.getName() + "'");
                    try {
                        Player player = (Player) sender;
                        while (rs.next()) {
                            Inventory inv = Bukkit.createInventory(null, 27,
                                    ChatColor.GOLD + player.getName() + ChatColor.YELLOW + "'s Stats");

                            ItemStack blackglasspane = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 15);
                            ItemMeta blackglasspanemeta = blackglasspane.getItemMeta();

                            ItemStack kills = new ItemStack(Material.PAPER);
                            ItemMeta killsmeta = kills.getItemMeta();

                            ItemStack deaths = new ItemStack(Material.PAPER);
                            ItemMeta deathsmeta = kills.getItemMeta();

                            ItemStack offline = new ItemStack(Material.REDSTONE_BLOCK);
                            ItemMeta offlinemeta = offline.getItemMeta();

                            ItemStack online = new ItemStack(Material.EMERALD_BLOCK);
                            ItemMeta onlineMeta = online.getItemMeta();

                            blackglasspanemeta.setDisplayName(" ");
                            blackglasspane.setItemMeta(blackglasspanemeta);

                            killsmeta.setDisplayName(ChatColor.GREEN + "Kills: " + rs.getString("kills"));
                            kills.setItemMeta(killsmeta);

                            deathsmeta.setDisplayName(ChatColor.GREEN + "Deaths: " + rs.getString("deaths"));
                            deaths.setItemMeta(deathsmeta);

                            onlineMeta.setDisplayName(ChatColor.GREEN + "ONLINE");
                            online.setItemMeta(onlineMeta);

                            offlinemeta.setDisplayName(ChatColor.RED + "OFFLINE");
                            offline.setItemMeta(onlineMeta);

                            if (player.isOnline()) {
                                for (int i = 0; i < 27; i++) {
                                    inv.setItem(i, blackglasspane);
                                }
                                inv.setItem(10, kills);
                                inv.setItem(13, online);
                                inv.setItem(16, deaths);
                            } else {
                                inv.setItem(10, kills);
                                inv.setItem(13, offline);
                                inv.setItem(16, deaths);
                            }

                            player.openInventory(inv);
                        }
                    } catch (SQLException e) {
                        Player player = (Player) sender;
                        player.sendMessage("Unable to open GUI");
                        e.printStackTrace();
                    }

                } else if (args.length == 1) {
                    ResultSet rs = MySQLManager.getResult("SELECT * FROM stats WHERE player_name = '" + args[0] + "'");
                    try {
                        Player player = (Player) sender;
                        while (rs.next()) {
                            Inventory inv = Bukkit.createInventory(null, 27,
                                    ChatColor.GOLD + args[0] + ChatColor.YELLOW + "'s Stats");

                            ItemStack blackglasspane = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 15);
                            ItemMeta blackglasspanemeta = blackglasspane.getItemMeta();

                            ItemStack kills = new ItemStack(Material.PAPER);
                            ItemMeta killsmeta = kills.getItemMeta();

                            ItemStack deaths = new ItemStack(Material.PAPER);
                            ItemMeta deathsmeta = kills.getItemMeta();

                            ItemStack offline = new ItemStack(Material.REDSTONE_BLOCK);
                            ItemMeta offlinemeta = offline.getItemMeta();

                            ItemStack online = new ItemStack(Material.EMERALD_BLOCK);
                            ItemMeta onlineMeta = online.getItemMeta();

                            blackglasspanemeta.setDisplayName(" ");
                            blackglasspane.setItemMeta(blackglasspanemeta);

                            killsmeta.setDisplayName(ChatColor.GREEN + "Kills: " + rs.getString("kills"));
                            kills.setItemMeta(killsmeta);

                            deathsmeta.setDisplayName(ChatColor.GREEN + "Deaths: " + rs.getString("deaths"));
                            deaths.setItemMeta(deathsmeta);

                            onlineMeta.setDisplayName(ChatColor.GREEN + "ONLINE");
                            online.setItemMeta(onlineMeta);

                            offlinemeta.setDisplayName(ChatColor.RED + "OFFLINE");
                            offline.setItemMeta(onlineMeta);


                            if (player.isOnline()) {
                                for (int i = 0; i < 27; i++) {
                                    inv.setItem(i, blackglasspane);
                                }
                                inv.setItem(10, kills);
                                inv.setItem(16, deaths);
                            } else {
                                inv.setItem(10, kills);
                                inv.setItem(16, deaths);
                            }

                            player.openInventory(inv);
                        }
                    } catch (SQLException e) {
                        Player player = (Player) sender;
                        player.sendMessage("Unable to open GUI");
                        e.printStackTrace();
                    }
                }



            }


            return false;
        }
        return true;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Inventory inventory = event.getInventory();
        if (inventory.getName().contains(ChatColor.YELLOW + "'s Stats")) {
            event.setCancelled(true);
        }
    }
}