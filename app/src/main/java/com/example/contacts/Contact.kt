
import java.util.Date

class Contact(
    ID:Long,
    FirstName:String,
    LastName:String,
    PhoneNumber:String,
    DOB:String,
)
{
    // Create a new contact
    constructor(){
        ID = 0
        FirstName = ""
        LastName = ""
        PhoneNumber = ""
        DOB = ""
    }
}

