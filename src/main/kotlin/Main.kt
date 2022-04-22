import java.util.*

fun main(args: Array<String>) {

var john= Person("Johnie","Maina")
println(john.firstName)  //Johnie


    var homeOwner= john
    println(homeOwner.firstName) //Johnie

    //identity operator:
    // Compares memory address of 2 references,checks if the references point to same block of data on the heap
    println(john===homeOwner)//true


    val imposterJohn=Person("Johnie","Maina")
    println(john===imposterJohn)//false
    //In above,john and impostorJohn don't hold the same memory address

    println(john==imposterJohn)//false

    //Many Fake , imposter Johnies
    var imposters=(0..100).map{
        Person(firstName="Johnie",lastName="Maina")
    }


    //using equality operator
    imposters.map{
        it.firstName=="Johnie"  &&  it.lastName=="Maina"
    }.contains(true)
    //Here the equality operator is not sufficient since all imposters have the same FirstName and LastName hence a
    //john can't be identified by his name only thus need to use ===(Identity Operator)

    //checking to ensure the real john ain't found in the imposters
    println(imposters.contains(john))// false


    //let's hide a real john inside the imposters
    val mutableImposters= mutableListOf<Person>()
    mutableImposters.addAll(imposters)
    mutableImposters.contains(john)//returns false

    mutableImposters.add(
        Random().nextInt(5),john
    )

    println(mutableImposters.contains(john))//returns true

//Person is a reference type hence use === to get the real john from the list of imposters and modify the value
    val indexOfJohn=mutableImposters.indexOf(john)
    if(indexOfJohn!=-1){
        mutableImposters[indexOfJohn].lastName="Kitarimbo"
    }
    println(john.fullName)
    }


class Person(var firstName: String,var lastName:String){
    val fullName get() = "$firstName $lastName"
}

