package floschlo.wgmanager;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toolbar;

import floschlo.wgmanager.connection.server.SocketService;

public class OverviewActivity extends AppCompatActivity implements View.OnClickListener {


    //VIEWS
    private Toolbar mAppBar;
    private Toolbar mActionsBar;
    private TabLayout mTabLayout;
    private FloatingActionButton mFloatingActionButton;
    private ViewPager mContentPager;

    //CONNECTION SERVICE STUFF
    private Intent startConnectionServiceIntent = new Intent (this, SocketService.class);
    private boolean mBound = false;
    private SocketService mService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview);

        assignViews();

        mFloatingActionButton.setOnClickListener(this);

        //set Appbar
        setActionBar(mAppBar);
    }

    private void assignViews () {
        mAppBar = (Toolbar) findViewById(R.id.overview_toolbar_appbar);
        mActionsBar = (Toolbar) findViewById(R.id.overview_toolbar_actions);
        mTabLayout = (TabLayout) findViewById(R.id.overview_tablayout_appbartabs);
        mFloatingActionButton = (FloatingActionButton) findViewById(R.id.overview_fab_actions);
        mContentPager = (ViewPager) findViewById(R.id.overview_pager_content);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(mServiceConnection);
    }

    @Override
    public void onClick(View v) {
        if (v == mFloatingActionButton) {



        }
    }

    private ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName className,
                                       IBinder service) {
            // We've bound to LocalService, cast the IBinder and get LocalService instance
            SocketService.LocalBinder binder = (SocketService.LocalBinder) service;
            mService = binder.getService();
            mBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            mBound = false;
        }
    };
}
