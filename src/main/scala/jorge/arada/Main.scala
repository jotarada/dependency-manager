package jorge.arada


import jorge.arada.QueueRepository.QueueRepository
import zio.{UIO, ZIO}
import zio.console.{Console, putStr}
import zio._

object Main {

  type ZEnv = QueueRepository with Console

  val env = QueueRepository.test ++ Console.live


  def main(args: Array[String]) {
    val effect = program.provideLayer(env)

    val runtime = Runtime.default

    runtime.unsafeRun(effect)

  }

  val program: ZIO[ZEnv, Throwable, String] = {
   for {
      _ <- putStr("bemvindo ao meu programa")
      _ <- QueueRepository.addToQueue("teste")
      x <- QueueRepository.getFromQueue("")
      _ <- putStr(x)
    } yield (x)

  }


}