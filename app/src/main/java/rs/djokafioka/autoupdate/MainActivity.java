package rs.djokafioka.autoupdate;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.github.javiersantos.appupdater.AppUpdater;
import com.github.javiersantos.appupdater.enums.Display;
import com.github.javiersantos.appupdater.enums.UpdateFrom;

public class MainActivity extends AppCompatActivity
{

    TextView mTxtHW;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AppUpdater appUpdater = new AppUpdater(this)
                .setDisplay(Display.DIALOG)
                .setTitleOnUpdateAvailable("Update available")
                .setButtonUpdate("Update now?")
                .setButtonDismiss("Maybe later")
                .setIcon(R.drawable.vd_update_black_24dp)
                .setCancelable(false)
                .setButtonDoNotShowAgain(null)
                .setUpdateFrom(UpdateFrom.XML)
                .setUpdateXML("http://android.omniwms.rs/Test/update.xml");
        appUpdater.start();

        mTxtHW = (TextView) findViewById(R.id.txt_hw);

        mTxtHW.setText("App New Version = " + getAppVersion());

    }

    private String getAppVersion()
    {
        try
        {
            PackageInfo packageInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
            return packageInfo.versionName;
        }
        catch (PackageManager.NameNotFoundException e)
        {
            e.printStackTrace();
        }
        return "N/A";
    }
}
