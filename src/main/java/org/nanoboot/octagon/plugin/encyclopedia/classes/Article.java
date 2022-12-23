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

import org.nanoboot.octagon.core.exceptions.OctagonException;
import org.nanoboot.octagon.entity.core.Entity;
import org.nanoboot.octagon.entity.core.EntityAttribute;
import org.nanoboot.octagon.entity.core.EntityAttributeType;
import lombok.Data;

import java.util.*;
import java.util.stream.Collectors;

/**
 *
 * @author <a href="mailto:robertvokac@nanoboot.org">Robert Vokac</a>
 * @since 0.1.0
 */
@Data
public class Article implements Entity {
    private static List<EntityAttribute> SCHEMA;
    /**
     * UUID identification of this entity.
     */
    private UUID id;
    private String name;
    private Boolean top;
    private String note;
    private Integer score;
    private KnowledgeLevel knowledgeLevel;

    public void validate() {
        if (score != null && (score < 0 || score > 100)) {
            throw new OctagonException("Actual article is not following the required format" + toString());
        }
    }

    @Override
    public void loadFromMap(Map<String, String> map) {
        setName(getStringParam("name", map));
        setTop(getBooleanParam("top", map));
        setNote(getStringParam("note", map));
        setScore(getIntegerParam("score", map));

        String knowledgeLevelFromRequest = getStringParam("knowledgeLevel", map);

        setKnowledgeLevel(knowledgeLevelFromRequest == null ? null : KnowledgeLevel.valueOf(knowledgeLevelFromRequest));
    }

    @Override
    public Class getEntityClass() {
        return getClass();
    }

    @Override
    public String[] toStringArray() {
        return new String[]{
                id == null ? "" : id.toString(),

                name == null ? "" : name,
                top == null ? "" : convertBooleanToString(top),
                note == null ? "" : note,
                score == null ? "" : String.valueOf(score),
                knowledgeLevel == null ? "" : knowledgeLevel.toString()
        };
    }

    @Override
    public List<EntityAttribute> getSchema() {
        if (SCHEMA == null) {
            SCHEMA = new ArrayList<>();

            SCHEMA.add(EntityAttribute.getIdEntityAttribute());
            SCHEMA.add(new EntityAttribute("name").withMandatory(true));
            SCHEMA.add(new EntityAttribute("top", EntityAttributeType.BOOLEAN));
            SCHEMA.add(new EntityAttribute("note"));
            SCHEMA.add(new EntityAttribute("score", EntityAttributeType.INTEGER));
            SCHEMA.add(new EntityAttribute("knowledgeLevel", Arrays.asList(KnowledgeLevel.values()).stream().map(KnowledgeLevel::name).collect(Collectors.toList())));
        }
        return SCHEMA;
    }

    @Override
    public String[] getRelatedListsForEntity() {
        return new String[]{"getParentCategoriesForArticle"};
    }

    public String[] getRelatedActionsForEntity() {
        return new String[]{
                "Assign to parent category:create?className=CategoryAssignment&articleId=",
                "List parent categories:list?className=CategoryAssignment&filter_articleId=",
        };
    }
}
