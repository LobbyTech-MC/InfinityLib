package io.github.mooy1.infinitylib.players;

import io.github.mooy1.infinitylib.AbstractAddon;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public final class CoolDownMap {
    
    private final Map<UUID, Long> map = new HashMap<>();
    
    public CoolDownMap(AbstractAddon addon) {
        LeaveListener.create(addon, this.map);
    }
    
    public boolean check(@Nonnull UUID uuid, long cd) {
        return System.currentTimeMillis() - this.map.getOrDefault(uuid, 0L) >= cd;
    }

    public void put(@Nonnull UUID uuid) {
        this.map.put(uuid, System.currentTimeMillis());
    }

    public boolean checkAndPut(@Nonnull UUID uuid, long cd) {
        boolean check = check(uuid, cd);
        if (check) {
            put(uuid);
        }
        return check;
    }
    
}