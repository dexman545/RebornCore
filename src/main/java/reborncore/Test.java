package reborncore;

import autoswitch.api.AutoSwitchApi;
import autoswitch.api.AutoSwitchMap;
import autoswitch.api.DurabilityGetter;
import net.minecraft.item.Item;
import net.minecraft.tag.Tag;
import org.apache.commons.lang3.tuple.Pair;
import reborncore.common.util.ItemUtils;
import team.reborn.energy.EnergyHolder;

public class Test implements AutoSwitchApi {

	@Override
	public void moddedTargets(AutoSwitchMap<String, Object> targets, AutoSwitchMap<String, String> actionConfig, AutoSwitchMap<String, String> usableConfig) {

	}

	@Override
	public void moddedToolGroups(AutoSwitchMap<String, Pair<Tag<Item>, Class<?>>> toolGroupings) {

	}

	@Override
	public void customDamageSystems(AutoSwitchMap<Class<?>, DurabilityGetter> damageMap) {
		// Multiple by 100 to get percentage out of decimal form
		damageMap.put(EnergyHolder.class, stack -> 100 * ItemUtils.getPowerForDurabilityBar(stack));
	}
}
