![Minecraft](https://img.shields.io/badge/Minecraft-1.21+-62B246?style=for-the-badge&logo=minecraft&logoColor=white)
![Fabric API](https://img.shields.io/badge/Fabric-API-dbd7d2?style=for-the-badge)

# ⚡ Lightning Strike: The Thunderbringer

Developed by **DizzyZ7**.

This mod introduces a high-tier combat artifact logic that overrides standard weapon behavior to create a powerful, balanced elemental weapon. It showcases advanced damage manipulation and server-side entity control.

## ⚔️ Weapon Mechanics & Balance

* **Overpowered Base:** The logic is tuned to provide +1 Attack Damage over the standard Diamond Sword (8.0 Base Damage).
* **Pure Elemental Damage:** On every successful hit, the target is struck by lightning, dealing an additional **20.0 points (10 hearts)** of direct damage.
* **Smart Safety System:** Uses custom `DamageSource` filtering to ensure the player is 100% immune to their own lightning strikes and area-of-effect damage.
* **Anti-Spam Control:** Integrated with the Minecraft Attack Cooldown system to ensure strikes only trigger on full-strength swings.

## 🛠 Technical Implementation

* **Damage Manipulation:** Precise control over health points using `target.damage()` with `lightningBolt()` and `playerAttack()` sources.
* **Cosmetic Entity Spawning:** Utilizing `lightning.setCosmetic(true)` to trigger vanilla visual/audio effects while handling the actual damage logic programmatically for better precision.
* **Server-Side Verification:** All combat calculations are performed strictly on the server to prevent desync and exploit attempts.

## 🚀 Key Skills Showcased
- **Advanced Combat Hooks:** Intercepting and modifying the attack pipeline via `AttackEntityCallback`.
- **Entity Filtering:** Distinguishing between different entity types and owners to prevent friendly fire.
- **Game Balancing:** Implementing complex damage math to create "Legendary" grade equipment.
