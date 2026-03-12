package com.example.proxyguard;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.net.InetAddress;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ProxyGuardServer extends JavaPlugin implements Listener {
    private Set<String> allowedProxies = new HashSet<>();

    @Override
    public void onEnable() {
        saveDefaultConfig();
        loadConfig();
        getServer().getPluginManager().registerEvents(this, this);
    }

    private void loadConfig() {
        FileConfiguration cfg = getConfig();
        List<String> list = cfg.getStringList("allowed-proxies");
        allowedProxies.clear();
        for (String entry : list) {
            allowedProxies.add(entry.trim());
        }
        allowedProxies.add("127.0.0.1");
    }

    @EventHandler
    public void onAsyncPreLogin(AsyncPlayerPreLoginEvent event) {
        InetAddress address = event.getAddress();
        String ip = address.getHostAddress();
        if (!allowedProxies.contains(ip)) {
            event.disallow(AsyncPlayerPreLoginEvent.Result.KICK_OTHER,
                    "[Proxyguard] You cannot directly join that server. Please connect through the proxy");
        }
    }
}
