/*
 * Copyright 2006 the original author or authors.
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
package org.unitils.database;

import org.unitils.core.Unitils;
import org.unitils.dbmaintainer.DBMaintainer;

import javax.sql.DataSource;

/**
 * Class providing access to the functionality of the database module using static methods. Meant
 * to be used directly in unit tests.
 * 
 * @author Filip Neven
 * @author Tim Ducheyne
 */
public class DatabaseUnitils {
	
	
	/**
     * Returns the DataSource that connects to the test database
     * @return The DataSource that connects to the test database
     */
	public static DataSource getDataSource() {
		return getDatabaseModule().getDataSource();
	}
	
	
	/**
     * Flushes all pending updates to the database. This method is useful when the effect of updates 
     * needs to be checked directly on the database.
     * <p/>
     * A typical usage of this method is, when updates were issues to the database using hibernate,
     * making sure that these updates are flushed, to be able to check the effect of these updates
     * using plain old JDBC.
     */
    public void flushDatabaseUpdates() {
    	Object testObject = Unitils.getInstance().getTestContext().getTestObject();
        getDatabaseModule().flushDatabaseUpdates(testObject);
    }
	
    
	/**
     * Determines whether the test database is outdated and, if that is the case, updates the database with the
     * latest changes. See {@link DBMaintainer} for more information.
     */
	public static void updateDatabase() {
		getDatabaseModule().updateDatabase();
	}
	
	
	/**
     * Updates the database version to the current version, without issuing any other updates to the database.
     * This method can be used for example after you've manually brought the database to the latest version, but
     * the database version is not yet set to the current one. This method can also be useful for example for
     * reinitializing the database after having reorganized the scripts folder.
     */
    public void setDatabaseToCurrentVersion() {
        getDatabaseModule().setDatabaseToCurrentVersion();
    }
	
	
	/**
	 * Clears all configured schema's. I.e. drops all tables, views and other database objects.
	 */
	public static void clearSchemas() {
		getDatabaseModule().clearSchemas();
	}
	
	
	/**
	 * Cleans all configured schema's. I.e. removes all data from its database tables.
	 */
	public static void cleanSchemas() {
		getDatabaseModule().cleanSchemas();
	}
	
	
	/**
	 * Disables all foreigh key and not-null constraints on the configured schema's.
	 */
	public static void disableConstraints() {
		getDatabaseModule().disableConstraints();
	}
	
	
	/**
	 * Updates all sequences that have a value below a certain configurable treshold to become equal 
	 * to this treshold
	 */
	public static void updateSequences() {
		getDatabaseModule().updateSequences();
	}
	
	
	/**
	 * Generates a definition file that defines the structure of dataset's, i.e. a XSD of DTD that
	 * describes the structure of the database.
	 */
	public static void generateDatasetDefinition() {
		getDatabaseModule().generateDatasetDefinition();
	}
	
	
	/**
	 * @return The database module
	 */
	private static DatabaseModule getDatabaseModule() {
		return Unitils.getInstance().getModulesRepository().getModuleOfType(DatabaseModule.class);
	}
}