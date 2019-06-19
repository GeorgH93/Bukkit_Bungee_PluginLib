/*
 *   Copyright (C) 2019 GeorgH93
 *
 *   This program is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *   This program is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 *   GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License
 *   along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

package at.pcgamingfreaks.Bukkit.Message.Sender;

import at.pcgamingfreaks.Bukkit.MCVersion;
import at.pcgamingfreaks.Bukkit.NMSReflection;

import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Method;

import static at.pcgamingfreaks.Bukkit.MCVersion.MC_1_8;

public enum SendMethod
{
	CHAT_CLASSIC(null, null),
	CHAT(MCVersion.isOlderThan(MC_1_8) ? null : new ChatSender(), null),
	TITLE(MCVersion.isOlderThan(MC_1_8) ? null : new TitleSender(), TitleMetadata.class),
	ACTION_BAR(MCVersion.isOlderThan(MC_1_8) ? null : new ActionBarSender(), null),
	//BOSS_BAR(new BossBarSender(), BossBarMetadata.class), //TODO
	DISABLED(new DisabledSender(), null);

	private final Sender defaultSender;
	private final Class<?> metadataClass;
	private final Method fromJsonMethod;

	SendMethod(Sender defaultSender, @Nullable Class<?> metadataClass)
	{
		this.defaultSender = defaultSender;
		this.metadataClass = metadataClass;
		this.fromJsonMethod = (metadataClass != null) ? NMSReflection.getMethod(this.metadataClass, "fromJson", String.class) : null;
	}

	/**
	 * Gets the default sender instance for the used option.
	 *
	 * @return The default sender instance.
	 */
	public Sender getSender()
	{
		return this.defaultSender;
	}

	/**
	 * Gets the metadata class for the used option.
	 *
	 * @return The metadata class.
	 */
	public @Nullable Class<?> getMetadataClass()
	{
		return this.metadataClass;
	}

	/**
	 * Gets the method to convert a JSON in a metadata object.
	 *
	 * @return The static method to convert a JSON in a metadata object.
	 */
	public @Nullable Method getMetadataFromJsonMethod()
	{
		return this.fromJsonMethod;
	}
}