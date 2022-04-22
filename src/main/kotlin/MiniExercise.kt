import java.util.*

class Person1(){
}

fun main() {
    var john= Person1()
    var women = (0..5).map {
        Person1()
    }

    //Array 1
    //Electorate has a john
    val electorate= mutableListOf<Person1>()
    electorate.addAll(women)
    electorate.add(Random().nextInt(5), john)
   // println(electorate.contains(john))//RETURNS TRUE
    var results=memberOf(john,electorate)


    // Array 2
    // Leaders has no Person john
    val leaders= mutableListOf<Person1>()
    leaders.addAll(women)
    //println(leaders.contains(john))//RETURNS FALSE
    results=memberOf(john,leaders)
    }

fun memberOf(person:Person1,group:List<Person1>):Boolean{
    println(group.contains(person))
    return group.contains(person)
}
