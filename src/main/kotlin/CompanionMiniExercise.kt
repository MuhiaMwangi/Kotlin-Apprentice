//Companion Object MINI-EXERCISE: pg(207)

//keep track of how many students have been created
//companion object method-numberOfStudents: to get no of students instances
//init block to increment the counter

data class Student3(val id: Int, val fName: String, val lName: String){

    var fullName="$fName $lName"

    companion object {
        var counter = 0

        fun numberOfStudents() = counter
    }

    init {
        counter++
    }
}

fun main(){
    val osi= Student3(1,"Osi", "Muhia")
   // val jnr= Student3(2,"Osi","Jrn" )
    val cdy= Student3(3,"Sydney", "Muhia")

    println(Student3.numberOfStudents())
}


