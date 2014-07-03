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
		
		createconfig();
	
		getServer().getPluginManager().registerEvents(new OasisCastListener(this), this);
		getCommand("cast").setExecutor(new OasisCastCommands(this));
	}
	
	public void createconfig(){
		File file = new File(getDataFolder(), "config.yml");
		if(file.exists()){
			return;
		}
		if(!getDataFolder().exists()){
			if(!getDataFolder().mkdirs()){
				getLogger().severe("Datafolder could not be created!");
				getLogger().severe("Disabling");
				setEnabled(false);
				return;
			}
		}
		InputStream in = getResource("config.yml");
		OutputStream out = null;
		try {
			out = new FileOutputStream(file);
			byte[] buf = new byte[1024];
			int len;
			while ((len = in.read(buf)) > 0) {
				out.write(buf, 0, len);
			}
		} catch (IOException e) {
			getLogger().warning("Failed to copy the default config! (I/O)");
			e.printStackTrace();
		} finally {
			try {
				if (out != null) {
					out.close();
				}
			} catch (IOException e) {
				getLogger().warning("Failed to close the streams! (I/O -> Output)");
				e.printStackTrace();
			}
			try {
				if (in != null) {
					in.close();
				}
			} catch (IOException e) {
				getLogger().warning("Failed to close the streams! (I/O -> Input)");
				e.printStackTrace();
			}
		}
	}
}
