import android.content.Context
import android.os.StrictMode
import android.widget.Toast
import ni.edu.uca.loginconsql.User
import ni.edu.uca.loginconsql.UserDao
import java.lang.Exception
import java.sql.*
import java.sql.Connection

class Connection(context: Context) {

    private val ip = "186.77.197.107:1433"
    private val db = "Autenticacion"
    private val user = "sa"
    private val pw = "Usuario123.#"

    fun dbConn(): Connection? {
        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)

        var conn: Connection? = null
        var connString: String? = null
        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver")
            connString = "jdbc:jtds:sqlserver://$ip;databaseName=$db;user=$user;password=$pw;"
            conn = DriverManager.getConnection(connString)
        } catch (ex : SQLException){
            println(ex.message)
        } catch (ex1 : ClassNotFoundException){
            println(ex1.message)
        } catch (ex2 : Exception){
            println(ex2.message)
        }
        return conn
    }

    fun getUserDao(): UserDao {
        return object : UserDao {
            override fun login(username: String, password: String): User? {
                val statement = connection.prepareStatement(
                    "SELECT * FROM users WHERE username = ? AND password = ?"
                )
                statement.setString(1, username)
                statement.setString(2, password)
                val resultSet = statement.executeQuery()
                return if (resultSet.next()) {
                    User(
                        resultSet.getInt("id"),
                        resultSet.getString("username"),
                        resultSet.getString("password")
                    )
                } else {
                    null
                }
            }
        }
    }
}