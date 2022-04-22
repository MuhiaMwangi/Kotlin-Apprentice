//ENUM CLASSES

import java.util.*

enum class DayOfWeek(val isWeekend: Boolean= false) {
    //enum classes can have properties and functions just like other classes
    //Its possible to set them up to be passed in as part of the constructor for each case in the enum class: AS SHOWN ABOVE

    //Each case is an instance of the enum class
    Monday,
    Tuesday,
    Wednesday,
    Thursday,
    Friday,
    Saturday(true),
    Sunday(true);//we add the semicolon at the end of list of cases if we are planning to add ,more functionality to the enum class and
//to ensure that the compiler sees that we have come to the end of the list of cases and some other functionality declaration has begun


    //Fuction belonging to an enum class
    //1.Adding functions directly to enum class which depends on a particular instance
    //i.e CALCULATING HOW MANY days IT IS FROM THE CURRENT INSTANCE until A GIVEN DAY OF THE WEEK
    fun daysUntil(otherDay:DayOfWeek):Int{
        return if(this.ordinal<otherDay.ordinal)
            otherDay.ordinal-this.ordinal
        else//caters for scenarios where the ordinals are equal AND scenario where the otherOrdinal is greater than the currentOrdinal
            otherDay.ordinal-this.ordinal+DayOfWeek.values().count()
    }

//Fuction belonging to an enum class
    //2. Companion objects -To do things that don't depend on a specific instance of a class
    // i.e FINDING OUT WHICH DAY of WEEK today IS

    companion object{
        fun today():DayOfWeek{
            //Using Java Calendar class to access info about the current day
            val calenderDayOfWeek=Calendar.getInstance().get(Calendar.DAY_OF_WEEK)
            //Our week starts on a Monday unlike Calendar class which starts on Sunday
            //Calendar class considers weeks as 1-indexed instead of 0-indexed
            //We subtract 2 to account for those adjustments
            var adjustedDay=calenderDayOfWeek-2

           //Here we are making an adjustment to ensure we don't accidentally get a value which can't exist
            //If adjustedDay is less than 0, we add count of days to get it to wrap back around to a value which does exist
            val days=DayOfWeek.values()//values fn gives a list of all cases declared in the enum
            if(adjustedDay<0){
              adjustedDay+=days.count()
            }

            val today= days.first {it.ordinal==adjustedDay } //ordinal property of each case gives that's case index in the list declared cases
            //using a version of FIRST which takes a PREDICATE LAMBDA to look at the list of all days
            //AND returns the 1st one where the ordinal matches the adjusted day

            return today
        }
    }
}

//By declaring a class as an enum, we get: name and ordinate property as well as valueOf() companion object
//name property- it takes the name of each case in the enum class and gives back the String value of the name
//ordinal property of each case gives that's case index in the list declared cases
//valueOf() companion function gives you a list of all cases declared in the enum class


fun main(args:Array<String>){
for (day in DayOfWeek.values())
    println("Day ${day.ordinal}:${day.name}")

    val dayIndex=5//ArrayIndexOutOfBoundsException :Thrown if we go beyond the length of values()
        //println("There are ${DayOfWeek.values().size} days declared in the enum class")
    val dayAtIndex= DayOfWeek.values()[dayIndex]
    println("Day at index: $dayIndex is $dayAtIndex")

    val tuesday= DayOfWeek.valueOf("Tuesday")
    println("Tuesday is day ${tuesday.ordinal}")//ordinal property of each case gives that's case index in the list declared cases

    //val notADay= DayOfWeek.valueOf("MwangiDAY")//IllegalArgumentException
  // println("MwangiDAY is day ${notADay.ordinal}")

    val today=DayOfWeek.today()
    val isWeekend="It is ${if (today.isWeekend) "" else "not"} the weekend"
    println("It is $today. $isWeekend.")

    val partyDay=DayOfWeek.Friday
    val daysUntil=today.daysUntil(partyDay)
    val msg= "It is $daysUntil days until $partyDay"
    println("It is $today. $isWeekend. $msg")

    when(today) {
        DayOfWeek.Monday -> println("I don't care if $today's blue")
        DayOfWeek.Tuesday -> println("$today's gray")
        DayOfWeek.Wednesday -> println("and $today, too")
        DayOfWeek.Thursday -> println("$today, i don't care 'bout you")
        DayOfWeek.Friday -> println("It's $today, I'm in love")
        DayOfWeek.Saturday -> println("$today, Wait...")
        DayOfWeek.Sunday -> println("$today always comes too late")
    }
}

