package top.dragonmounts.objects.items;

import com.google.common.base.Functions;
import net.minecraft.util.text.TextFormatting;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public enum EnumItemBreedTypes {
	AETHER(TextFormatting.DARK_AQUA), 
	WATER(TextFormatting.BLUE),
	ICE(TextFormatting.AQUA),
	FIRE(TextFormatting.RED), 
	FIRE2(TextFormatting.GOLD), 
	FOREST(TextFormatting.DARK_GREEN),
	SKELETON(TextFormatting.WHITE),
	WITHER(TextFormatting.DARK_GRAY),
	NETHER(TextFormatting.DARK_RED),
	NETHER2(TextFormatting.DARK_RED),
	END(TextFormatting.DARK_PURPLE),
	ENCHANT(TextFormatting.LIGHT_PURPLE),
	SUNLIGHT(TextFormatting.YELLOW),
	SUNLIGHT2(TextFormatting.LIGHT_PURPLE),
	MOONLIGHT(TextFormatting.BLUE),
	MOONLIGHT_FEMALE(TextFormatting.BLUE, "moonlight2"),
	STORM(TextFormatting.BLUE),
	STORM2(TextFormatting.BLUE),
	TERRA(TextFormatting.GOLD),
	TERRA2(TextFormatting.GOLD),
	ZOMBIE(TextFormatting.DARK_GREEN);
	//LIGHT(TextFormatting.GRAY);
	//DARK(TextFormatting.GRAY);
	//Specter(TextFormatting.WHITE);

	private static final Map<String, EnumItemBreedTypes> BY_NAME;

	public final TextFormatting color;
	public final String identifier;
	public final String translationKey;

	EnumItemBreedTypes(TextFormatting color, String identifier) {
		this.color = color;
		if (identifier == null) {
			identifier = this.name().toLowerCase();
		}
		this.identifier = identifier;
		this.translationKey = "dragon." + identifier;
	}

	EnumItemBreedTypes(TextFormatting color) {
		this(color, null);
	}

	static {
		BY_NAME = Arrays.stream(values()).collect(Collectors.toMap(type -> type.identifier, Functions.identity()));
	}

	public static EnumItemBreedTypes byName(String name) {
		return BY_NAME.getOrDefault(name, END);
	}
}
