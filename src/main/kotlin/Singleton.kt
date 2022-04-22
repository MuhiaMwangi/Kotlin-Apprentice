
//UseCase: An in-memory repository for a set of data
//Student
data class Student1 (val id: Int, val fName: String, val lName: String){
        var fullName="$fName $lName"
         }


//Registry for the student defined above data class
//Using object ensures only one registry can be created
//Had a class been used instead of a singleton object,
// it'd allow for multiple registries to be created leading to inconsistent registries
object StudentRegistry{
    val allStudents= mutableListOf<Student1>()

    fun addStudent(student: Student1){
        allStudents.add(student)
    }

    fun removeStudent(student: Student1){
        allStudents.remove(student)
    }

    fun listAllStudents(){
        allStudents.forEach {
            println(it.fullName)
        }
    }
}

fun main(){
    val osi= Student1(1,"Osi", "Muhia")
    val jnr= Student1(2,"Osi","Jrn" )
    val cdy= Student1(3,"Sydney", "Muhia")
    StudentRegistry.addStudent(osi)
    StudentRegistry.addStudent(jnr)
    StudentRegistry.addStudent(cdy)

    StudentRegistry.listAllStudents()
}