# ProxyGuard

This plugin called **ProxyGuard** enforces joining through a Velocity/Bungee proxy. The goal is to prevent players from connecting directly to backend servers while still allowing proxy‑routed traffic.

---

## Usage
Put the compiled jar in your backend server and configure config.yml

### Building

Run `mvn clean package`

### Installation

* Place the resulting JAR from `proxyguard-server` in the `plugins/` folder of every backend server.
* Configure `proxyguard-server/config.yml` with your proxy’s IP address(es) under `allowed-proxies`.
* Restart the servers. Backend logs should show `ProxyGuard` enabling and will kick direct connections with:

  > `[Proxyguard] You cannot directly join that server. Please connect through the proxy`

* The proxy stubs are optional; drop them in your proxy’s `plugins/` (Bungee or Velocity) if you plan to extend functionality later.

> **Tip:** when running Velocity, set `authenticator: none` in `velocity.toml` if an external system (like a Bungee authenticator or custom login plugin) handles player authentication. ProxyGuard itself does not require or affect the authenticator setting, but mismatched auth can prevent players from logging in.

### Configuration

```yaml
# proxyguard-server/config.yml
allowed-proxies:
  - 192.168.0.5
  - 203.0.113.42
```

List each proxy IP that should be allowed to connect directly to the server.

---

Feel free to modify the code to add commands, dynamic IP syncing, or more sophisticated proxy-server communication. If you encounter problems or want additional features, open an issue or submit a PR.
