import scala.io.StdIn.readLine;
import java.time.LocalDateTime;
// import java.time.format.DateTimeFormatter.ofPattern;
import java.time.format.DateTimeFormatter;
import scala.collection.mutable.ListBuffer;



object CalendarBis {

    // Add events to the calendar
    def addEvents(oldList : ListBuffer[String]) : ListBuffer[String] = {
            println("Please enter event name : ")
            var event_name = readLine()

            println("Please enter the date (format : yyyy-MM-dd HH:mm) : ")
            var event_date = readLine()

            println("Please enter the location of the event : ")
            var event_location = readLine()

            // parse date
            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
            val parsedDate: LocalDateTime = LocalDateTime.parse(event_date, formatter)

            var new_id: Int = if (oldList.nonEmpty) oldList.length + 1 else 1
            
            // Create a new event
            val newRecord =  f"Id = $new_id, event name is : $event_name, event date is : $event_date and the location is $event_location"
            oldList += newRecord // add the new event to the list

            println(f"A new event has been successfully added ! ID : $new_id")

            oldList
    }

    def printEvents(events : ListBuffer[String]) : Unit = {
        if(events.isEmpty){
            println("Sorry, the list is empty")
        }
        else {
            println("*******************************************")
            for(event <- events){
                println(event)
            }
            println("*******************************************")
        }
    }

    def modifyEvent(events : ListBuffer[String]) : ListBuffer[String] = {
        if(events.isEmpty){
            println("Sorry, but the list is empty :( )")
        }
        else {
            println("Please choose the event, select 1 for the fisrt one, 2 for the second... : ")
            printEvents(events)
            var id_event = readLine()
            for ((event, idx) <- events.zipWithIndex){
                if(event.contains(f"Id = $id_event,")) {
                    println("Please enter event new name : ")
                    var event_name = readLine()

                    println("Please enter the date (format : yyyy-MM-dd HH:mm) : ")
                    var event_date = readLine()

                    println("Please enter the location of the event : ")
                    var event_location = readLine()

                    val newRecord =  f"Id = $id_event, event name is : $event_name, event date is : $event_date and the location is $event_location"
                    events.update(idx,newRecord)
                }
            }
            println("Here is the new list :")
            println("************************")
            println(events)
            println("************************")

        }
        events
    }

    def deleteEvent(events : ListBuffer[String]) : ListBuffer[String] = {
        if(events.isEmpty){
            println("************************")
            println("Sorry, but the list is empty :( )")
            println("************************")
        }
        else {
            println("Please choose the event, select 1 for the fisrt one, 2 for the second... : ")
            printEvents(events)
            var id_event = readLine()
            for (event <- events.toList){
                if(event.contains(f"Id = $id_event,")) {
                    events -= event
                }
            }
            println("Here is the new list :")
            println("************************")
            println(events)
            println("************************")

        }
        events
    }


    def main(args: Array[String]) : Unit = {
        var calendarStart = true
        var events: ListBuffer[String] = ListBuffer()

        println("Welcome to the Calendar app")
        println("To start please enter your name :)")
        var user = readLine()
        while(calendarStart) {
            println(f"******** Welcome $user, please choose an option ********")
            println("********* Principal menu ********")
            println("1/ Add a new event to my calendar ")
            println("2/ Display all recorded events ")
            println("3/ Delete an event ")
            println("4/ Modify an event ")
            println("5/ Quit ")

            readLine() match {
                case "1" => events = addEvents(events)
                case "2" => printEvents(events)
                case "3" => events = deleteEvent(events)
                case "4" => events = modifyEvent(events)
                case "5" =>
                calendarStart = false
                println("*******************************")
                println(f"Good bye $user, see you soon !")
                println("*******************************")
            }

        }

    }
}