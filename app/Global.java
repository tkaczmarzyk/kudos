import play.Application;
import play.GlobalSettings;
import play.libs.Akka;
import scala.concurrent.duration.Duration;
import models.*;
import java.util.concurrent.TimeUnit;

public class Global extends GlobalSettings {
    
    private Application application;

    @Override
    public void onStart(Application application) {
        this.application = application;
        Kudos kudos = createKudos("First Kudos","for app creation",createDummyPerson());
        kudos.save();
    }

    private Kudos createKudos(String reason, String details, Person person) {
        Kudos k = new Kudos();
        k.reason = reason;
        k.details = details;
        k.target = person;
        return k;
    }

    private Person createDummyPerson(){
        Person p = new Person();
        p.name = "Dummy";
        p.smallPhotoUrl = "http://cohesiva.com/images/people_thumbs/tomekk_alt.jpg";
        p.bigPhotoUrl = "http://cohesiva.com/images/people_thumbs/tomekk_alt.jpg";
        p.save();
        return p;
    }
}
