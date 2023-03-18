package ni.edu.uca.loginconsql
import AppDatabase
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Llamar al LoginActivity
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)

        try {
            // Llamar al AppDatabase
            val userDao = AppDatabase(this).getUserDao()
            // Realizar operaciones en la base de datos...
        } catch (e: Exception) {
            Toast.makeText(this, "Peto", Toast.LENGTH_LONG).show()
        }

    }
}