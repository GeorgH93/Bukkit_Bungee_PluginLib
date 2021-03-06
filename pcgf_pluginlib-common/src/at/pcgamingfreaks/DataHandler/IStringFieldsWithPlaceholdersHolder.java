/*
 *   Copyright (C) 2021 GeorgH93
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

package at.pcgamingfreaks.DataHandler;

import at.pcgamingfreaks.Reflection;

import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;

public interface IStringFieldsWithPlaceholdersHolder
{
	@NotNull String replacePlaceholders(final @NotNull String stringWithPlaceholders);

	/**
	 * Replace the placeholders in all the annotated ({@link HasPlaceholders} fields in the object.
	 *
	 */
	default void replacePlaceholders()
	{
		for(Field field : Reflection.getFieldsIncludeParents(this.getClass()))
		{
			if(!field.getType().equals(String.class) || field.getAnnotation(HasPlaceholders.class) == null) continue;
			try
			{
				boolean accessible = field.isAccessible();
				field.setAccessible(true);
				String value = (String) field.get(this);
				value = this.replacePlaceholders(value);
				field.set(this, value);
				field.setAccessible(accessible);
			}
			catch(IllegalAccessException e)
			{
				e.printStackTrace();
			}
		}
	}
}