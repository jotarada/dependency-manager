package jorge.arada

import zio._

import scala.collection.mutable.Queue


object QueueRepository {
  type QueueRepository = Has[Service]

  trait Service {
    def addToQueue(data: String): Task[Unit]

    def getFromQueue(queueName: String): Task[String]
  }

  class InMemoryQueueService() extends QueueRepository.Service {

    val queueMap: Queue[String] = Queue.empty[String]

    override def addToQueue(data: String): Task[Unit] = {
      Task(queueMap.enqueue(data))
    }

    override def getFromQueue(queueName: String): Task[String] = {
      Task(queueMap.dequeue())

    }
  }


  val test: ZLayer[Any, Throwable, QueueRepository] =
    ZLayer.succeed(new InMemoryQueueService())


  def addToQueue(data: String): ZIO[QueueRepository, Throwable, Unit] = {
    ZIO.environment[QueueRepository].flatMap(_.get.addToQueue(data))

  }

  def getFromQueue(queueName: String): ZIO[QueueRepository, Throwable, String] = {
    ZIO.environment[QueueRepository].flatMap(_.get.getFromQueue(queueName))
  }
}


