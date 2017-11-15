package softfruit.solutions.carehack.calendarevent

import android.app.usage.UsageEvents
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import softfruit.solutions.carehack.R

import kotlinx.android.synthetic.main.activity_calendar.*
import java.util.Arrays.asList
import android.provider.CalendarContract.Reminders
import jdk.nashorn.internal.objects.NativeDate.setMinutes
import java.util.*
import java.util.Arrays.asList
import android.provider.CalendarContract.Reminders
import jdk.nashorn.internal.objects.NativeDate.setMinutes




class CalendarActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendar)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
    }

    fun createEvent(mCredential: GoogleAccountCredential) {

        var event = Event()
                .setSummary("Google I/O 2015")
                .setLocation("800 Howard St., San Francisco, CA 94103")
                .setDescription("A chance to hear more about Google's developer products.")

        val startDateTime = DateTime("2015-05-28T09:00:00-07:00")
        val start = EventDateTime()
                .setDateTime(startDateTime)
                .setTimeZone("America/Los_Angeles")
        event.setStart(start)

        val endDateTime = DateTime("2015-05-28T17:00:00-07:00")
        val end = EventDateTime()
                .setDateTime(endDateTime)
                .setTimeZone("America/Los_Angeles")
        event.setEnd(end)

        val recurrence = arrayOf("RRULE:FREQ=DAILY;COUNT=2")
        event.setRecurrence(Arrays.asList(recurrence))

        val attendees = arrayOf<EventAttendee>(EventAttendee().setEmail("lpage@example.com"), EventAttendee().setEmail("sbrin@example.com"))
        event.setAttendees(Arrays.asList(attendees))

        val reminderOverrides = arrayOf<EventReminder>(EventReminder().setMethod("email").setMinutes(24 * 60), EventReminder().setMethod("popup").setMinutes(10))
        val reminders = Event.Reminders()
                .setUseDefault(false)
                .setOverrides(Arrays.asList(reminderOverrides))
        event.setReminders(reminders)

        val calendarId = "primary"
        event = service.events().insert(calendarId, event).execute()
        System.out.printf("Event created: %s\n", event.getHtmlLink())

    }
}
