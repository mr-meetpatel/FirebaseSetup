package application

import com.google.cloud.firestore.Firestore
import connector.Connection
import core.Person
import org.junit.jupiter.api.Assertions.*
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNot
import io.kotest.matchers.shouldNotBe
import java.util.Objects

internal class MainTest:StringSpec({
    val db: Firestore = Connection().getConnection()
    val main:Main = Main()
    "Add Person to the database and this will return Data Inserted..."{
    val person= Person("Aamir","Islam",26,"aamir@gmail.com")
    main.addData(db,person) shouldBe "Data Inserted..."
    }
    "Display All persons and this will not return null"{
        main.fetchAllData(db) shouldNotBe null
    }
    "Display particular persons and will not return null if data is stored"{
        main.fetchDataById(db,"d8Win9PwzLUHjxUU2I9p") shouldNotBe null
    }
    "Display particular persons and will  null if data is not stored"{
        main.fetchDataById(db,"d8Win9PwzLUHjxUU2I9") shouldBe null
    }
    "Delete person data and will return Data Deleted..."{
        main.deleteDataById(db,"d8Win9PwzLUHjxUU2I9p") shouldBe "Data Deleted..."
    }
    "Delete person data and will return Data not found... if data is not stored" {
        main.deleteDataById(db, "d8Win9PwzLUHjxUU2I9p") shouldBe "Data not found"
    }
    "Update All data and will return Data Updated..."{
        val person= Person("Aamir","Islam",26,"aamir@gmail.com")
        main.updateAllData(db,"Cm5z25bZCNAJJhkUhKkJ",person) shouldBe "Data Updated..."
    }
    "Update data and will return Data Updated..."{
        main.updateParticularData(db,"Cm5z25bZCNAJJhkUhKkJ","fname","aamir") shouldBe "Data Updated..."
    }
})