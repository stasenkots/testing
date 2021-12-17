package driver

sealed class Browser(val name: String)

object Chrome : Browser("chrome")
object Firefox : Browser("firefox")
