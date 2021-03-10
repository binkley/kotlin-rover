package hm.binkley.rover.mathy

class Path(val instructions: List<Instruction>) :
    List<Instruction> by instructions

fun InputLine.toPath() =
    // TODO: How to avoid string -> chars -> strings -> instructions?
    Path(
        data.map {
            it.toString().toInstruction()
        }
    )

fun Path.execute(startAt: Position, boundary: Boundary) =
    instructions.fold(startAt) { position, instruction ->
        instruction(position, boundary)
    }
