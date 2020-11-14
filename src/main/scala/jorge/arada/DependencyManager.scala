package jorge.arada

import jorge.arada.QueueRepository.QueueRepository
import zio.{Has, RIO}


object DependencyManager {

  type DependencyManager = Has[Service]

  trait Service {
    def getDependencies(processName: String): RIO[QueueRepository, Seq[String]]

  }

}

