package hm.binkley.rover.mathy

class Path(private val instructions: List<Instruction>) :
    List<Instruction> by instructions

fun InputLine.toPath() =
    // TODO: How to avoid string -> chars -> strings -> instructions?
    Path(
        data.map {
            it.toString().toInstruction { invalid() }
        }
    )
