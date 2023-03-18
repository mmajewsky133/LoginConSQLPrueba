package ni.edu.uca.loginconsql

interface UserDao {

    fun login(username: String, password: String): User?
}