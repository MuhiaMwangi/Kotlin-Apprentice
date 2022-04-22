//ANONYMOUS OBJECTS
//In Java,Anonymous classes are used to:
// 1.Override the behaviour of existing classes without the need to subclass
//2.Implement interfaces WITHOUT DEFINING CONCRETE CLASSES
//Anonymous objects/Object Expression is the Kotlin version of the anonymous classes found in Java


//Interface to track the no. of students and scientists in your app
interface Counts{
    //The 2-signature methods that an instance needs to implement
    fun studentCount():Int
    fun scientistCount():Int
}


//creating an Anonymous object using Counts interface
//creating an instance of counter using the OBJECT KEYWORD
val counter=object :Counts{

    //Overriding each of the Count interface methods
        override  fun studentCount():Int{
      return  StudentRegistry.allStudents.size
    }

    override fun scientistCount():Int{
     return  ScientistRepo.allScientists.size
    }
}

fun main(){
println(counter.studentCount())
println(counter.scientistCount())
}