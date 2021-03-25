
package net.mcreator.zombieapocalypse;

import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;

import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.Items;
import net.minecraft.item.ItemStack;

import net.mcreator.zombieapocalypse.item.VolatileiteItem;
import net.mcreator.zombieapocalypse.item.PurevolatileiteItem;

@ZombieApocalypseModElements.ModElement.Tag
public class PurevolatileitecraftBrewingRecipe extends ZombieApocalypseModElements.ModElement {
	public PurevolatileitecraftBrewingRecipe(ZombieApocalypseModElements instance) {
		super(instance, 76);
	}

	@Override
	public void init(FMLCommonSetupEvent event) {
		BrewingRecipeRegistry.addRecipe(Ingredient.fromStacks(new ItemStack(VolatileiteItem.block, (int) (1))),
				Ingredient.fromStacks(new ItemStack(Items.LAVA_BUCKET, (int) (1))), new ItemStack(PurevolatileiteItem.block, (int) (1)));
	}
}
