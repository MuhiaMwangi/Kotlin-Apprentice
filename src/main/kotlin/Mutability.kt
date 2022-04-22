/*
//MUTABLE NATURE OF CLASSES
//We modify jane even when its defined as a val
//This is because reference types are not treated as values,
// they are not protected as a whole from mutation even when instantiated with val

class Grade(val letter:String,
            val points:Double,
            val credits:Double)

class Student(
    val firstName: String,
    val lastName:String,
    val grades:MutableList<Grade> =mutableListOf(),
    var credits:Double=0.0)
{

    fun recordGrade(grade:Grade){
            grades.add(grade)
            credits+= grade.credits
        }
}

fun main(){
    val mary =Student(firstName = "Mercy", lastName = "Claire")
    val history=Grade("A",12.0,4.0)
    var math= Grade("B",9.0,3.0)


    mary.recordGrade(history)
    mary.recordGrade(math)
    println(mary.credits)
     math= Grade("A",20.0,5.0)
    mary.recordGrade(math)
    println(mary.credits)
//Here, we observe that we are able to record for math twice
// since class instances-(math instance):belongs to Grade: are mutable.
// Here arises the need to be careful about unexpected behaviour around shared references

}
*/
