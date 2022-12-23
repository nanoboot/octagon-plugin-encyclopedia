///////////////////////////////////////////////////////////////////////////////////////////////
// Octagon Plugin Encyclopedia: Encyclopedia plugin for Octagon application.
// Copyright (C) 2021-2022 the original author or authors.
//
// This program is free software; you can redistribute it and/or
// modify it under the terms of the GNU General Public License
// as published by the Free Software Foundation; version 2
// of the License only.
//
// This program is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
// GNU General Public License for more details.
//
// You should have received a copy of the GNU General Public License
// along with this program; if not, write to the Free Software
// Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
///////////////////////////////////////////////////////////////////////////////////////////////

package org.nanoboot.octagon.plugin.encyclopedia.classes;

import lombok.Getter;

/**
 *
 * @author <a href="mailto:robertvokac@nanoboot.org">Robert Vokac</a>
 * @since 0.1.0
 */
public enum KnowledgeLevel {
    BEGINNER("Knows nothing"),

    ELEMENTARY("Knows the basics"),

    PRE_INTERMEDIATE("Is able to create products"),

    INTERMEDIATE("Medium knowledge"),

    UPPER_MEDIATE("Better knowledge than medium"),

    ADVANCED("Very good knowledge"),

    EXPERT("Best knowledge");
    @Getter
    private final String description;

    KnowledgeLevel(String s) {
        this.description = s;
    }
}
