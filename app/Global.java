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
    }

}