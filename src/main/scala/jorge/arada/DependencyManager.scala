package jorge.arada

import zio.{RIO, Task, ZIO}

object dependencyManager {

  trait DependencyManager {
    def dependencyManager: DependencyManager.Service
  }

  object DependencyManager {

    trait Service {
      def getDependencies(processName: String): RIO[QueueService, Seq[String]]
    }

    trait Live extends DependencyManager {
      val service = new Service {
        def getDependencies(processName: String): RIO[QueueService, Seq[String]] = {
          RIO.access {
            queueService => {
              queueService.getFromQueue(processName).map(_.value.mkString(","))
            }
          }
        }
      }
    }

  }

  def getDependencies(processName: String): ZIO[DependencyManager, Throwable, Seq[String]] = {

    ZIO.access (_.dependencyManager.getDependencies(processName))

  }
}







