package kontrolnye

interface Transformable {
    fun transform(data: String): String
}

abstract class DataProcessor(val processorName: String) {
    fun process(data: String): String = "Обработчик: $processorName обработал данные"
}

class Encryptor : DataProcessor("Encryptor"), Transformable {
    val prefix = "encoded_"
    override fun transform(data: String): String = "$processorName преобразовал данные: $prefix$data"
}

class Compressor : DataProcessor("Compressor"), Transformable {
    val prefix = "compressed_"
    override fun transform(data: String): String = "$processorName преобразовал данные: $prefix$data"
}

class Logger : DataProcessor("Logger")
