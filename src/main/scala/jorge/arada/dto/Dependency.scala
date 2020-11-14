package jorge.arada.dto

case class Dependency(
                       name: String,
                       value: Map[String, String]
                     )