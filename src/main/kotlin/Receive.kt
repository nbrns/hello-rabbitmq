import com.rabbitmq.client.ConnectionFactory
import com.rabbitmq.client.DeliverCallback

class Receive {
    private val queueName = "hello"

    private val deliverCallback = DeliverCallback { _, delivery ->
        val message = String(delivery.body, charset("UTF-8"))
        println(" [x] Received '$message'")
    }

    fun execute() {
        var factory = ConnectionFactory().apply {
            host = "localhost"
        }
        val connection = factory.newConnection()

        val channel = connection.createChannel()
        channel.queueDeclare(queueName, false, false, false, null)
        channel.basicConsume(queueName, deliverCallback) { _ -> Unit }

    }
}


fun main(args: Array<String>) {
    println("Hello RabbitMQ!")
    Receive().execute()
}