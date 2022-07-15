import com.rabbitmq.client.ConnectionFactory
import javax.sound.sampled.AudioFormat.Encoding

class Send {
    val queueName: String = "hello"

    private val connectionFactory: ConnectionFactory = ConnectionFactory().apply {
        host = "localhost"
    }

    fun execute() {
        connectionFactory.newConnection().use { connection ->
            connection.createChannel().use {
                it.queueDeclare(queueName, false, false, false, null)
                val message = "Hello World!"
                it.basicPublish("", queueName, null, message.toByteArray())
                println("Published message: $message")
            }
        }
    }
}


fun main(args: Array<String>) {
    println("Hello RabbitMQ!")
    Send().execute()
}