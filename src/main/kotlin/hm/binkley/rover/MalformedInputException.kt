package hm.binkley.rover

class MalformedInputException(
    message: String,
    cause: Exception? = null,
) : RuntimeException(message, cause)
