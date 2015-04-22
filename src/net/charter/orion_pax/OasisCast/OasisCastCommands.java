package net.charter.orion_pax.OasisCast;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class OasisCastCommands implements CommandExecutor{
	
	private OasisCast plugin;
	public OasisCastCommands(OasisCast plugin){
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		int count=0;
		if(args.length==1){
			if(args[0].equalsIgnoreCase("list")){
				for(String key:plugin.getConfig().getKeys(false)){
					count++;
					sender.sendMessage(ChatColor.translateAlternateColorCodes('~',"~6[~1" + count + "~6] - ~2" + key + "~r: ~a" + plugin.getConfig().getString(key + ".Name") + " ~a" + plugin.getConfig().getString(key + ".Text")));
				}
				return true;
			}
			
			if(args[0].equalsIgnoreCase("reload")){
				if (sender.hasPermission("oasiscast.staff.reload")) {
					plugin.reloadConfig();
					sender.sendMessage(ChatColor.GREEN + "Plugin reloaded!");
					return true;
				}
			}
		} else if(args.length>1){
			if(args[0].equalsIgnoreCase("delete")){
				if (sender.hasPermission("oasiscast.staff.delete")) {
					for (String key : plugin.getConfig().getKeys(false)) {
						if (key.equals(args[1])) {
							plugin.getConfig().set(key, null);
							sender.sendMessage(ChatColor.GREEN
									+ "Successfully removed!");
							plugin.saveConfig();
							return true;
						}
					}
					sender.sendMessage(ChatColor.RED + args[1] + " not found!");
					sender.sendMessage(ChatColor.RED + "/cast delete COMMAND");
					return true;
				}
			}
			
			if(args[0].equalsIgnoreCase("help")){
				sender.sendMessage(ChatColor.GREEN + "usage: /cast list");
				sender.sendMessage(ChatColor.GREEN + "usage: /cast add COMMAND PREFIX TEXTCOLOR");
				sender.sendMessage(ChatColor.GREEN + "usage: /cast delete COMMAND");
				sender.sendMessage(ChatColor.GREEN + "usage: /cast reload");
				sender.sendMessage(ChatColor.GREEN + "usage: /cast help (DUH!  HOW DO YOU THINK YOU GOT HERE?");
				return true;
			}
			
			if(args[0].equalsIgnoreCase("add")){
				if (sender.hasPermission("oasiscast.staff.add")) {
					if (args.length == 4) {
						if(args[1].equalsIgnoreCase("cast")){
							sender.sendMessage(ChatColor.RED + "Damnit Cody, stop trying to brea mah shit maaaaan!");
							return true;
						}
						plugin.getConfig().set(args[1] + ".Name", args[2]);
						plugin.getConfig().set(args[1] + ".Text", args[3]);
						plugin.saveConfig();
						sender.sendMessage(ChatColor.GREEN
								+ "Successfully added!");
						sender.sendMessage(ChatColor.GREEN + "Permnode: oasiscast.staff." + args[1]);
						return true;
					} else {
						sender.sendMessage(ChatColor.RED
								+ "/cast add cmd_name displayname textcolor");
						return true;
					}
				}
			}
		}
		sender.sendMessage(ChatColor.GREEN + "usage: /cast help");
		return true;
	}

}
