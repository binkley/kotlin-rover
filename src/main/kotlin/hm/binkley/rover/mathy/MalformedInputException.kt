package hm.binkley.rover.mathy

class MalformedInputException(
    message: String,
    cause: Exception? = null,
) : RuntimeException(message, cause)
