package net.mcreator.zombieapocalypse.procedures;

import net.minecraft.util.text.StringTextComponent;
import net.minecraft.item.ItemStack;
import net.minecraft.inventory.container.Slot;
import net.minecraft.inventory.container.Container;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.block.Blocks;

import net.mcreator.zombieapocalypse.ZombieApocalypseModElements;
import net.mcreator.zombieapocalypse.ZombieApocalypseMod;

import java.util.function.Supplier;
import java.util.Map;
import java.util.HashMap;

@ZombieApocalypseModElements.ModElement.Tag
public class HitadminbuttonProcedure extends ZombieApocalypseModElements.ModElement {
	public HitadminbuttonProcedure(ZombieApocalypseModElements instance) {
		super(instance, 89);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				ZombieApocalypseMod.LOGGER.warn("Failed to load dependency entity for procedure Hitadminbutton!");
			return;
		}
		if (dependencies.get("guistate") == null) {
			if (!dependencies.containsKey("guistate"))
				ZombieApocalypseMod.LOGGER.warn("Failed to load dependency guistate for procedure Hitadminbutton!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		HashMap guistate = (HashMap) dependencies.get("guistate");
		String title = "";
		if (((new Object() {
			public ItemStack getItemStack(int sltid) {
				Entity _ent = entity;
				if (_ent instanceof ServerPlayerEntity) {
					Container _current = ((ServerPlayerEntity) _ent).openContainer;
					if (_current instanceof Supplier) {
						Object invobj = ((Supplier) _current).get();
						if (invobj instanceof Map) {
							return ((Slot) ((Map) invobj).get(sltid)).getStack();
						}
					}
				}
				return ItemStack.EMPTY;
			}
		}.getItemStack((int) (1))).getItem() == new ItemStack(Blocks.BEDROCK, (int) (1)).getItem())) {
			if (entity instanceof PlayerEntity && !entity.world.isRemote()) {
				((PlayerEntity) entity).sendStatusMessage(new StringTextComponent((new Object() {
					public String getText() {
						TextFieldWidget textField = (TextFieldWidget) guistate.get("text:title");
						if (textField != null) {
							return textField.getText();
						}
						return "";
					}
				}.getText())), (false));
			}
		}
	}
}
