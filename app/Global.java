import play.Application;
import play.GlobalSettings;

public class Global extends GlobalSettings {
    
    private Application application;

    @Override
    public void onStart(Application application) {
        this.application = application;
    }

}