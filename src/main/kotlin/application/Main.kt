package application
import com.google.api.core.ApiFuture
import com.google.cloud.firestore.DocumentReference
import com.google.cloud.firestore.Firestore
import com.google.cloud.firestore.QueryDocumentSnapshot
import com.google.cloud.firestore.SetOptions
import com.google.cloud.firestore.WriteResult
import connector.Connection
import core.Person

class Main{
    fun addData(db:Firestore,person: Person): String {
        val data = hashMapOf<String,Any>()
        data.put("fname",person.fname)
        data.put("lname",person.lname)
        data.put("age",person.age)
        data.put("email",person.email)
        val docRef:DocumentReference = db.collection("persons").document()
        val writeResult: ApiFuture<WriteResult> = docRef.set(data)
        while (!writeResult.isDone){
            continue
        }
        return "Data Inserted..."
    }

    fun fetchAllData(db: Firestore): MutableList<QueryDocumentSnapshot> {
        val query = db.collection("persons").get()
        val querySnapshot = query.get()
        return querySnapshot.documents
    }
    fun fetchDataById(db: Firestore, id:String): MutableMap<String, Any>? {
        val docRef=db.collection("persons").document(id)
        return docRef.get().get().data
    }
    fun deleteDataById(db: Firestore, id: String): String {

        val documents = fetchAllData(db)
        var isDeleted=false
        for(document in documents){
            if(document.id == id){
                val writeResult: ApiFuture<WriteResult> =document.reference.delete()
                while (!writeResult.isDone){
                    isDeleted=true
                    continue
                }
                break
            }
        }
        return if(isDeleted){
            "Data Deleted..."
        }
        else{
            "Data not found"
        }
    }
    fun updateAllData(db: Firestore, id:String,person: Person): String {
        val updateddata = hashMapOf<String,Any>()
        updateddata.put("fname",person.fname)
        updateddata.put("lname",person.lname)
        updateddata.put("age",person.age)
        updateddata.put("email",person.email)
        val writeResult: ApiFuture<WriteResult> = db.collection("persons").document(id).update(updateddata)
        while (!writeResult.isDone){
            continue
        }
        return "Data Update..."
    }

    fun updateParticularData(db: Firestore, id: String, key: String,data:String): String {
        val updateddata = hashMapOf<String,Any>()
        updateddata.put(key,data)

        val writeResult: ApiFuture<WriteResult> = db.collection("persons").document(id).set(updateddata,SetOptions.merge())
        while (!writeResult.isDone){
            continue
        }
        return "Data Updated..."
    }
}

fun main() {
    val db: Firestore = Connection().getConnection()
    val main:Main=Main()
    println("1.Add Data")
    println("2.Display All Data")
    println("3.Display Specific Data")
    println("4.Delete Data")
    println("5.Update All Data")
    println("6.Update Particular Data")
    println("Enter Your Choice")
    when(readln().toInt()){
        1-> {
            println("Enter First Name:")
            val fname= readln()
            println("Enter Last Name:")
            val lname= readln()
            println("Enter Age:")
            val age= readln().toInt()
            println("Enter Email:")
            val email= readln()
            val person=Person(fname,lname,age,email)
            println(main.addData(db,person))

        }
        2-> {
            val documents = main.fetchAllData(db)
            println("Total Record : ${documents.size}")
            for(document in documents){
                println("Id : ${document.id}")
                println("First Name : ${document.getString("fname")}")
                println("Last Name : ${document.getString("lname")}")
                println("Age : ${document.get("age")}")
                println("Email : ${document.getString("email")}")
                println()
            }
        }
        3-> {
            println("Enter Doc Id :")
            val id = readln()
            println(main.fetchDataById(db,id))
        }
        4-> {
            println("Enter Doc Id :")
            val id = readln()
            println(main.deleteDataById(db,id))
        }
        5->{
            println("Enter Doc Id :")
            val id = readln()
            if(main.fetchDataById(db,id)==null){
                println("Data not found")
                return
            }
            println("Enter First Name:")
            val fname= readln()
            println("Enter Last Name:")
            val lname= readln()
            println("Enter Age:")
            val age= readln().toInt()
            println("Enter Email:")
            val email= readln()
            val person=Person(fname,lname,age,email)
            println(main.updateAllData(db,id,person))

        }
        6->{
        println("Enter Doc Id :")
        val id = readln()
        if(main.fetchDataById(db,id)==null){
            println("Data not found")
            return
        }
        println("1.Update First Name:")
        println("2.Update Last Name:")
        println("3.Update Age:")
        println("4.Update email:")
        val ch= readln().toInt()
        val key=listOf<String>("fname","lname","age","email")
        println("Enter ${key[ch-1]} to update :")
        val data= readln()

        println(main.updateParticularData(db,id,key[ch-1],data))

    }
        else-> println("Please select in specific range")
    }
}










