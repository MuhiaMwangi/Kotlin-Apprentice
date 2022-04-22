
//COMPANION OBJECT: Used to create static members of a class
//A static member in a class is one which is common to all instances of the class


class Scientist private constructor(val id:Int ,val frName:String,val srName:String) {


    //Kotlin allows you to create static members by creating companion object inside the class
    //Below, we have CUSTOM-NAMED our companion object-: Companion object name can be used to extend the capabilities of the companion object
    //Normally,the implicit name for the companion object is: COMPANION
    companion object Factory{
        //1. Static member
        var currentId = 0

        //2.static members use case-> implementing Factory pattern:for creating new class instances
        //the constructor- (Scientist class) is set to private to ensure that new instances of the Scientist class
        // ...can only be created using this factory method
        //Also ensures value of currentID is correctly incremented when a new instance of Scientist is created/instantiated.
        fun newScientist(id: Int, frName: String, srName: String): Scientist {
            currentId += 1
            return Scientist(currentId, frName, srName)
        }
    }

    var fullName = "$frName $srName"
}


//creating a repo for scientists as a Singleton
object ScientistRepo{
    val allScientists= mutableListOf<Scientist>()

    fun addScientist(newScientist: Scientist){
        allScientists.add(newScientist)
    }
    fun removeScientist(newScientist: Scientist){
        allScientists.remove(newScientist)
    }
    fun listAllScientists(){
        allScientists.forEach {
            println("${it.id} : ${it.fullName}")
        }
    }
}

fun main(){
    val derick=Scientist.newScientist(1,"Derick","Dre")
    val cindu= Scientist.newScientist(2,"Cindu", "Pretty")
    val zawadi = Scientist.newScientist(3,"Zawadi","Brown")

    ScientistRepo.addScientist(derick)
    ScientistRepo.addScientist(cindu)
    ScientistRepo.addScientist(zawadi)

    ScientistRepo.listAllScientists()
}