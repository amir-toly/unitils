/*
 * Copyright 2011,  Unitils.org
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.unitils.io.annotation.handler;

import org.apache.commons.lang.StringUtils;
import org.unitils.core.UnitilsException;
import org.unitils.io.annotation.TempDir;
import org.unitils.io.temp.TempService;
import org.unitilsnew.core.FieldAnnotationListener;
import org.unitilsnew.core.TestField;
import org.unitilsnew.core.TestInstance;
import org.unitilsnew.core.annotation.Property;
import org.unitilsnew.core.reflect.Annotations;

import java.io.File;

/**
 * Implements the behavior of the {@link TempDir} annotation.<br/>
 * See annotation javadoc for more info.
 *
 * @author Jeroen Horemans
 * @author Tim Ducheyne
 * @author Thomas De Rycke
 * @since 3.3
 */
public class TempDirAnnotationHandler extends FieldAnnotationListener<TempDir> {

    /* True if the temp dirs should be deleted after the test */
    protected Boolean cleanupAfterTest;

    /* The file service that will create and delete the temp dirs */
    protected TempService tempService;


    public TempDirAnnotationHandler(TempService tempService, @Property("IOModule.temp.cleanupAfterTest") Boolean cleanupAfterTest) {
        this.tempService = tempService;
        this.cleanupAfterTest = cleanupAfterTest;
    }


    @Override
    public void beforeTestSetUp(TestInstance testInstance, TestField testField, org.unitilsnew.core.reflect.Annotations<TempDir> annotations) {
        createTempDirForField(testInstance, testField);
    }

    @Override
    public void afterTestMethod(TestInstance testInstance, TestField testField, Annotations<TempDir> annotations, Throwable testThrowable) {
        if (!cleanupAfterTest) {
            return;
        }
        deleteTempDirForField(testInstance, testField);
    }


    protected void createTempDirForField(TestInstance testObject, TestField field) {
        TempDir annotation = field.getAnnotation(TempDir.class);
        String fileName = annotation.value();
        if (StringUtils.isEmpty(fileName)) {
            fileName = testObject.getTestObject().getClass().getName() + "-" + testObject.getTestMethod().getName();
        }
        try {
            File f = tempService.createTempDir(fileName);
            field.setValue(f);
        } catch (Exception e) {
            throw new UnitilsException("Error creating temp dir for field " + field.getName(), e);
        }
    }

    protected void deleteTempDirForField(Object testObject, TestField field) {
        try {
            File tempDir = field.getValue();
            tempService.deleteTempFileOrDir(tempDir);
        } catch (Exception e) {
            throw new UnitilsException("Error deleting temp dir for field " + field.getName(), e);
        }
    }
}
