package ni.edu.uca.loginconsql

import AppDatabase
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.sql.SQLException

class LoginActivity : AppCompatActivity() {

    private lateinit var userDao: UserDao
    private lateinit var loginButton: Button
    private lateinit var usernameEditText: EditText
    private lateinit var passwordEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loginButton = findViewById(R.id.loginButton)
        usernameEditText = findViewById(R.id.usernameEditText)
        passwordEditText = findViewById(R.id.passwordEditText)

        // Intentar conectarse a la base de datos y mostrar un mensaje de éxito o error
        try {
            userDao = AppDatabase(this).getUserDao()
            Toast.makeText(this, "Conexión exitosa", Toast.LENGTH_SHORT).show()
        } catch (e: SQLException) {
            Toast.makeText(this, "Error al conectar: ${e.message}", Toast.LENGTH_SHORT).show()
        }

        loginButton.setOnClickListener {
            val username = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()

            // Realizar la operación de login y mostrar un mensaje de éxito o error
            val user = userDao.login(username, password)
            if (user != null) {
                Toast.makeText(this, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Nombre de usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show()
            }
        }
    }
}