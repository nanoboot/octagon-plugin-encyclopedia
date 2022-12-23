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
import org.nanoboot.octagon.entity.core.ActionType;
import org.nanoboot.octagon.entity.core.Entity;
import org.nanoboot.octagon.entity.core.EntityAttribute;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 *
 * @author <a href="mailto:robertvokac@nanoboot.org">Robert Vokac</a>
 * @since 0.1.0
 */
@Data
@ToString
public class CategoryAssignment implements Entity {
    private static List<EntityAttribute> SCHEMA;
    /**
     * UUID identification of this entity.
     */
    private UUID id;
    private UUID articleId;
    private UUID categoryId;
    private UUID parentCategoryId;

    public String getName() {
        return id == null ? (Double.valueOf(Math.random() * 1000000)).toString() : id.toString();
    }

    public void validate() {
        if ((articleId != null && categoryId != null) || (articleId == null && categoryId == null)) {
            throw new OctagonException("Invalid entity: " + this.toString());
        }
    }

    @Override
    public void loadFromMap(Map<String, String> map) {
        setArticleId(getUuidParam("articleId", map));
        setCategoryId(getUuidParam("categoryId", map));
        setParentCategoryId(getUuidParam("parentCategoryId", map));
    }

    @Override
    public Class getEntityClass() {
        return getClass();
    }

    @Override
    public String[] toStringArray() {
        return new String[]{
                id == null ? "" : id.toString(),
                articleId == null ? "" : articleId.toString(),
                categoryId == null ? "" : categoryId.toString(),
                parentCategoryId == null ? "" : parentCategoryId.toString(),
        };
    }

    @Override
    public List<EntityAttribute> getSchema() {
        if (SCHEMA == null) {
            SCHEMA = new ArrayList<>();

            SCHEMA.add(EntityAttribute.getIdEntityAttribute());
            SCHEMA.add(new EntityAttribute("articleId", "article", "getArticles"));
            SCHEMA.add(new EntityAttribute("categoryId", "category", "getCategories"));
            SCHEMA.add(new EntityAttribute("parentCategoryId", "category", "getCategories").withMandatory(true));
        }
        return SCHEMA;
    }

    public boolean canBeUpdatedTo(CategoryAssignment ca) {
        return false;
    }

    @Override
    public ActionType[] getSupportedActions() {
        return new ActionType[]{
                ActionType.CREATE,
                ActionType.READ,
                ActionType.DELETE,
                ActionType.LIST};
    }
}
