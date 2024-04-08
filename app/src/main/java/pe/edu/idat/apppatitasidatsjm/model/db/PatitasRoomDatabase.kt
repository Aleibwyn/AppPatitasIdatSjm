package pe.edu.idat.apppatitasidatsjm.model.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import pe.edu.idat.apppatitasidatsjm.model.db.dao.PersonaDao
import pe.edu.idat.apppatitasidatsjm.model.db.entity.PersonaEntity

@Database(entities = [PersonaEntity::class], version = 1)
abstract class PatitasRoomDatabase : RoomDatabase() {
    abstract fun personaDao(): PersonaDao

    companion object {
        @Volatile
        private var instancia: PatitasRoomDatabase? = null
        fun getDatabase(context: Context): PatitasRoomDatabase {
            val tempInstancia = instancia
            if (tempInstancia != null) return tempInstancia
            synchronized(this) {
                val instancia = Room.databaseBuilder(
                    context.applicationContext,
                    PatitasRoomDatabase::class.java,
                    "patitasdb"
                ).build()
                this.instancia = instancia
                return instancia
            }
        }
    }
}