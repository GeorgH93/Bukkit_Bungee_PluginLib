/*
 *   Copyright (C) 2020 GeorgH93
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
 *   along with this program. If not, see <https://www.gnu.org/licenses/>.
 */

package at.pcgamingfreaks.Bukkit.Util;

import at.pcgamingfreaks.Bukkit.IPlatformDependent;

import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

interface IPluginChannelUtils extends IPlatformDependent
{
	void registerOutgoingChannelUnchecked(final @NotNull Plugin plugin, final @NotNull String channel);
	void unregisterOutgoingChannelUnchecked(final @NotNull Plugin plugin, final @NotNull String channel);
	void sendPluginMessageUnchecked(final @NotNull Plugin plugin, final @NotNull Player player, final @NotNull String channel, final @NotNull byte[] message);
}