package net.charter.orion_pax.OasisCast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.bukkit.plugin.java.JavaPlugin;

public class OasisCast extends JavaPlugin{
	
	@Override
	public void onEnable(){
		this.saveDefaultConfig();
	
		getServer().getPluginManager().registerEvents(new OasisCastListener(this), this);
		getCommand("cast").setExecutor(new OasisCastCommands(this));
	}
	
	@Override
	public void onDisable(){
		saveConfig();
	}
}
