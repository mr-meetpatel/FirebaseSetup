package connector

import com.google.cloud.firestore.Firestore
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import org.junit.jupiter.api.Assertions.*

internal class ConnectionTest:StringSpec({
    val db:Firestore = Connection().getConnection()
    "Return Firestore object which should not null"{
        db shouldNotBe null
    }
})