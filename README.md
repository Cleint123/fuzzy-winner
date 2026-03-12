# ProxyGuard

This repository contains a small set of example plugins called **ProxyGuard** that enforce joining through a Velocity/Bungee proxy. The goal is to prevent players from connecting directly to backend servers while still allowing proxy‑routed traffic.

---

## Components

1. **proxyguard-server** – a Spigot/Paper plugin placed on each backend server. It kicks players whose IP address is not on an allow list (typically your proxy IPs).
2. **proxyguard-bungee** – optional BungeeCord-side stub; currently just logs startup but can be extended to communicate allowed IPs to backends.
3. **proxyguard-velocity** – optional Velocity-side stub with similar purpose. Note that Velocity itself must usually be configured with `authenticator: none` if you are using an upstream authentication plugin or an offline proxy setup; the ProxyGuard companion does not handle auth.

## Usage

### Building

Run `mvn clean package` in each subdirectory (`proxyguard-server`, `proxyguard-bungee`).

*The `proxyguard-velocity` subproject is optional and may not build automatically because the Velocity API JAR isn’t hosted on a public Maven repository. To compile it you can either install the `velocity-api-<version>.jar` manually into your local Maven repository or skip this step entirely.*

### Installation

* Place the resulting JAR from `proxyguard-server` in the `plugins/` folder of every backend server.
* Configure `proxyguard-server/config.yml` with your proxy’s IP address(es) under `allowed-proxies`.
* Restart the servers. Backend logs should show `ProxyGuard` enabling and will kick direct connections with:

  > `[Proxyguard] You cannot directly join that server. Please go through the proxy`

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
