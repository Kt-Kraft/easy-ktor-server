package db.migration

import persist.table.LogTable
import org.flywaydb.core.api.migration.BaseJavaMigration
import org.flywaydb.core.api.migration.Context
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.Table

@Suppress("ClassName", "SpreadOperator")
public class V1__Initial_Tables : BaseJavaMigration() {

  override fun migrate(context: Context?) {
    // Add table to database
    val tables = arrayOf<Table>(
      LogTable
    )
    SchemaUtils.create(*tables)
    createTrigger(context)
  }

  private fun createTrigger(context: Context?) {
    context?.connection
      ?.createStatement()
      .use {
        it?.execute(
          """
            CREATE OR REPLACE FUNCTION update_updated_at_column()
            RETURNS TRIGGER AS $$
            BEGIN
                NEW.updated_at = NOW();
                RETURN NEW;
            END;
            $$ LANGUAGE plpgsql;
          """.trimIndent(),
        )

        // Add trigger to specific table
        val listOfTables = listOf<String>()

        listOfTables.forEach { table ->
          it?.execute(
            """
              CREATE TRIGGER update_users_updated_at
              BEFORE UPDATE ON $table
              FOR EACH ROW
              EXECUTE FUNCTION update_updated_at_column();
            """.trimIndent(),
          )
        }
      }
  }
}
