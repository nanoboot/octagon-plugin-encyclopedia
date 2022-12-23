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

package org.nanoboot.octagon.plugin.encyclopedia.plugin;

import java.util.Properties;
import org.nanoboot.octagon.plugin.api.core.Plugin;
import org.nanoboot.octagon.plugin.api.core.PluginStub;
import org.nanoboot.octagon.plugin.api.core.PluginStubImpl;
import org.nanoboot.octagon.plugin.encyclopedia.classes.Article;
import org.nanoboot.octagon.plugin.encyclopedia.classes.Category;
import org.nanoboot.octagon.plugin.encyclopedia.classes.CategoryAssignment;
import org.nanoboot.octagon.plugin.encyclopedia.persistence.impl.mappers.ArticleMapper;
import org.nanoboot.octagon.plugin.encyclopedia.persistence.impl.mappers.CategoryAssignmentMapper;
import org.nanoboot.octagon.plugin.encyclopedia.persistence.impl.mappers.CategoryMapper;
import org.nanoboot.octagon.plugin.encyclopedia.persistence.impl.repos.ArticleRepositoryImplSQLiteMyBatis;
import org.nanoboot.octagon.plugin.encyclopedia.persistence.impl.repos.CategoryAssignmentRepositoryImplSQLiteMyBatis;
import org.nanoboot.octagon.plugin.encyclopedia.persistence.impl.repos.CategoryRepositoryImplSQLiteMyBatis;
import org.nanoboot.octagon.plugin.encyclopedia.persistence.impl.typehandlers.KnowledgeLevelTypeHandler;
import org.nanoboot.powerframework.json.JsonObject;
import lombok.Getter;

/**
 *
 * @author <a href="mailto:robertvokac@nanoboot.org">Robert Vokac</a>
 * @since 0.1.0
 */
public class EncyclopediaPlugin implements Plugin {
    private static final String ENCYCLOPEDIA = "encyclopedia";
    @Getter
    private PluginStub pluginStub = new PluginStubImpl();

    @Override
    public String getGroup() {
        return ENCYCLOPEDIA;
    }

    @Override
    public String getId() {
        return ENCYCLOPEDIA;
    }

    @Override
    public String getVersion() {
        return "0.0.0";
    }

    @Override
    public String init(Properties propertiesConfiguration) {

        pluginStub.registerEntityGroup(ENCYCLOPEDIA, 40);

        int sortkeyInGroup = 10;
        pluginStub
                .registerEntity(
                        ENCYCLOPEDIA,
                        Article.class,
                        ArticleMapper.class,
                        ArticleRepositoryImplSQLiteMyBatis.class, sortkeyInGroup++);
        pluginStub
                .registerEntity(
                        ENCYCLOPEDIA,
                        Category.class,
                        CategoryMapper.class,
                        CategoryRepositoryImplSQLiteMyBatis.class, sortkeyInGroup++);
        pluginStub
                .registerEntity(
                        ENCYCLOPEDIA,
                        CategoryAssignment.class,
                        CategoryAssignmentMapper.class,
                        CategoryAssignmentRepositoryImplSQLiteMyBatis.class, sortkeyInGroup++);


        pluginStub.registerTypeHandler(KnowledgeLevelTypeHandler.class);

        return null;
    }
    
    @Override
    public boolean hasMigrationSchema() {
        return true;
    }
}
