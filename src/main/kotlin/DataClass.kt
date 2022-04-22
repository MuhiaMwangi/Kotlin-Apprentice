//DATA CLASS
/*

//by using data class, it would have all same functionality
data class Student(
    var firstName:String,
    var lastName:String,
    var id: Int
)
*/

//It's common to have classes with a primary purpose for holding data
//this is what a data class would look like if we were to define it by ourself
//By using data classes you cn avoid declaring all this boilerplate code

class Student(
    var firstName:String,
    var lastName:String,
    var id: Int
){
    override fun hashCode(): Int {
        val prime =31
        var result=1

        result= prime*result + firstName.hashCode()
        result= prime*result + id
        result= prime*result + lastName.hashCode()

        return  result
    }

    override fun equals(other: Any?): Boolean {
        if(this === other)
            return true

        if (other==null)
            return  false

        if (javaClass!=other.javaClass)
            return false

        val obj = other as Student?
        if(firstName!=obj?.firstName)
            return false

        if(id != obj.id )
            return false

        if (lastName != obj.lastName)
            return false

        return true
    }

    override fun toString():String{
        return "Student(firstName=$firstName, lastName=$lastName, id=$id)"
    }

    fun copy(
        firstName:String = this.firstName,
        lastName: String = this.lastName)=  Student (firstName,lastName, id)
    }



//Comparing instances of model classes(used to mode objects),Printing, Copying
fun main(){
    val albert= Student("Joe","Doe", 1)
    val grace= Student("Grace","Wrench", 2)
    val albertCopy=albert.copy()//COPY METHOD
    println(albert)
    println(grace)//PRINTING INSTANCES OF MODEL CLASSES

    //COMPARING INSTANCES OF MODE CLASSES
    println(albert==grace)
    println(albert==albertCopy)//Equality operator
    println(albert===albertCopy)//Identity operator
}
