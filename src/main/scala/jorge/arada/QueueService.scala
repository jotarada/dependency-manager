package jorge.arada

import jorge.arada.dto.Dependency


trait QueueService {

  def addToQueue(data: Dependency): Unit

  def getFromQueue(queueName: String): Seq[Dependency]

}
