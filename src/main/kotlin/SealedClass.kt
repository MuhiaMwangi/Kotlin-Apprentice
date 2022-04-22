//SEALED CLASSES

sealed class AcceptedCurrency {
    class Dollar:AcceptedCurrency(){
        //abstract property is overridden in all the subclasses
        override val valueInDollars=1.0f
    }
    class Euro: AcceptedCurrency(){
        override val valueInDollars=1.25f
    }
    class Crypto: AcceptedCurrency(){
        override val valueInDollars: Float
            get() = 2345.92f
    }

    //non-abstract properties with cutom getter
    //You can add non-abstract properties to a sealed class as long as you provide them with initial values
      val name: String
      get()=when(this){
          is Dollar->"Dollar"
          is Euro->"Euro"
          is Crypto->"mmCoin"
      }

    //abstract property which will be overridden in each of the subclass
abstract val valueInDollars:Float

//non-abstract properties
var amount=0.0f
    fun totalValueInDollars():Float{
      return  amount*valueInDollars
    }

}
fun main(args: Array<String>){
    val currency= AcceptedCurrency.Crypto()
    println("You've got some ${currency.name} !")
 currency.amount= .235434f
    println("${currency.amount} ${currency.name}s is equivalent to: $${currency.totalValueInDollars() }")
}