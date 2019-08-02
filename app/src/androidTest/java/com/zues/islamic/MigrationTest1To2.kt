package com.zues.islamic

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.db.framework.FrameworkSQLiteOpenHelperFactory
import android.arch.persistence.room.testing.MigrationTestHelper
import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.zues.islamic.data.db.DB
import com.zues.islamic.data.db.entities.Zone
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

/* ---  Created by akhtarz on 8/2/2019. ---*/

@RunWith(AndroidJUnit4::class)
class MigrationTest1To2 {

    companion object {
        private const val TAG = "MigrationTest"
        private const val TEST_DB = "test-db"
    }

    lateinit var db: SupportSQLiteDatabase

//    var mSqliteTestDbHelper: SqliteTestDbOpenHelper = null

    @Rule
    @JvmField
    val helper: MigrationTestHelper = MigrationTestHelper(
        InstrumentationRegistry.getInstrumentation(),
        DB::class.java.canonicalName,
        FrameworkSQLiteOpenHelperFactory()
    )

    @Before
    @Throws(Exception::class)
    fun setUp() {
        db = helper.createDatabase(TEST_DB, 2)
        // above db object has schema version 2.
    }

    @After
    @Throws(Exception::class)
    fun tearDown() {
        // Clear the database after every test
//        SqliteDatabaseTestHelper.clearDatabase(mSqliteTestDbHelper)
    }

    @Test
    @Throws(IOException::class)
    fun migrate1To2() {

        // insert some data
        insertZones()

        db.close()

        // Re-open the database with version 2 and provide
        // MIGRATION_1_2 as the migration process.
        db = helper.runMigrationsAndValidate(TEST_DB, 2, true, DB.MIGRATION_1_2)

        // MigrationTestHelper automatically verifies the schema changes,
        // but you need to validate that the data was migrated properly

    }

    private fun insertZones() {

        val list : ArrayList<Zone> = ArrayList()
        list.add(Zone(14, "Pacific", "Palau"))
        list.add(Zone(15, "Antarctica", "Macquarie"))

        var values : ContentValues = ContentValues()
//        values.put(id)

        db.insert("Zones",SQLiteDatabase.CONFLICT_REPLACE,values)
    }

}