package net.charter.orion_pax.OasisCast;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class OasisCastListener implements Listener {

	private OasisCast plugin;
	public OasisCastListener(OasisCast plugin){
		this.plugin = plugin;
	}
	
	@EventHandler
	public void OnPlayerCommand(PlayerCommandPreprocessEvent event){
		String name;
		for(String key:plugin.getConfig().getKeys(false)){
			if(event.getMessage().contains("/"+key.toLowerCase()+" ")){
				String msg = event.getMessage().substring(key.length()+2);
				name=plugin.getConfig().getString(key+".Name");
				if(plugin.getConfig().getString(key+".Name").contains("%random%")){
					name=name.substring(8);
					StringBuffer thismsg = new StringBuffer();
					for(char string: name.toCharArray()){
						thismsg.append(rColor() + "" + string);
					}
					name=thismsg.toString();
				}
				msg = plugin.getConfig().getString(key+".Text").concat(msg);
				if(plugin.getConfig().getString(key+".Text").contains("%random%")){
					msg=msg.substring(8);
					StringBuffer thismsg2 = new StringBuffer();
					for(char string: msg.toCharArray()){
						thismsg2.append(rColor() + "" + string);
					}
					msg=thismsg2.toString();
				}
				if (event.getPlayer().hasPermission("oasiscast.staff." + key)) {
					plugin.getServer().broadcastMessage(
							ChatColor.translateAlternateColorCodes('&', name
									+ " " + msg));
					event.setCancelled(true);
				}
			}
		}
	}
	
	private ChatColor rColor(){
		//RED ORANGE YELLOW GREEN BLUE INDIGO VIOLET
		switch (randomNum(1,7)){
		case 1: return ChatColor.RED;
		case 2: return ChatColor.GOLD;
		case 3: return ChatColor.YELLOW;
		case 4: return ChatColor.GREEN;
		case 5: return ChatColor.BLUE;
		case 6: return ChatColor.DARK_PURPLE;
		case 7: return ChatColor.LIGHT_PURPLE;
		default: return ChatColor.DARK_AQUA;
		}
	}
	
	public int randomNum(Integer lownum, double d) {
		return lownum + (int)(Math.random() * ((d - lownum) + 1));
	}
}
